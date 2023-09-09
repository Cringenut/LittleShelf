package com.example.littleshelf.Groceries.GroceriesRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Groceries.GroceriesActivity;
import com.example.littleshelf.R;
import com.example.littleshelf.Groceries.SearchBar.SearchBarFragment;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.Main.Sort.SortTypesEnum;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Adapter */
public class GroceriesRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.RecyclerViewHolder>
        implements Filterable, RecyclerViewOnGroceryItemClickInterface {

    private RecyclerView recyclerView;
    private final ArrayList<GroceryItem> allGroceryItems; // Original unfiltered list
    private final ArrayList<GroceryItem> filteredGroceryItems; // Filtered list
    private final ArrayList<GroceryItem> sortedGroceryItems; // Sorted list
    private SortTypesEnum currentSort;
    private Filter currentFilter;
    private final Context context;
    private final SearchBarFragment searchBarFragment;
    private GroceryItem selectedGroceryItem;

    public GroceriesRecyclerViewAdapter(Context context, SearchBarFragment searchBarFragment, ArrayList<GroceryItem> groceryItems) {
        this.context = context;
        this.searchBarFragment = searchBarFragment;
        this.allGroceryItems = new ArrayList<>(groceryItems);
        this.filteredGroceryItems = new ArrayList<>(allGroceryItems);
        this.sortedGroceryItems = new ArrayList<>(filteredGroceryItems);
        this.currentSort = SortTypesEnum.unsorted;
        sortGroceryItems();
        setGroceryItemsListFilter();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onItemClicked(GroceryItem groceryItem) {
        selectGroceryItem(groceryItem);
    }

    public void selectGroceryItem(GroceryItem groceryItem) {
        if (groceryItem == selectedGroceryItem) {
            deselectGroceryItem();
            return;
        } else if (selectedGroceryItem != null) {
            deselectGroceryItem();
        }

        int position = getSortedGroceryItems().indexOf(groceryItem);
        View v = recyclerView.getLayoutManager().findViewByPosition(position);
        selectedGroceryItem = groceryItem;

        Button btnRemoveItem = v.findViewById(R.id.btnRemoveItem);
        btnRemoveItem.setOnClickListener(btnRemove -> {
            ((GroceriesActivity) context).getGroceriesListDataBaseHelper().deleteOne(groceryItem);
        });

        btnRemoveItem.setVisibility(View.VISIBLE);
    }

    public void deselectGroceryItem() {
        if (selectedGroceryItem != null) {
            int position = getSortedGroceryItems().indexOf(selectedGroceryItem);
            View v = recyclerView.getLayoutManager().findViewByPosition(position);

            if (v != null) {
                v.findViewById(R.id.btnRemoveItem).setVisibility(View.GONE);
            }

            selectedGroceryItem = null;
        }
    }

    @Override
    public Filter getFilter() {
        return currentFilter;
    }

    // Create grocery item fragment for recycler view
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.main_fragment_grocery_item, parent, false));
    }

    public ArrayList<GroceryItem> getSortedGroceryItems() {
        return sortedGroceryItems;
    }

    public SortTypesEnum getCurrentSort() {
        return currentSort;
    }

    public void setCurrentSort(SortTypesEnum currentSort) {
        this.currentSort = currentSort;
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemExpirationDate;
        CardView cardView;
        FrameLayout expirationMarker;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewItemName);
            itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
            cardView = itemView.findViewById(R.id.containerCardView);
            expirationMarker = itemView.findViewById(R.id.frameLayoutExpired);
        }
    }

    public void sortGroceryItems() {
        sortedGroceryItems.clear();
        sortedGroceryItems.addAll(filteredGroceryItems);
        Collections.reverse(sortedGroceryItems);

        switch (currentSort) {
            case unsorted:
                break;
            case itemNameASC:
                sortedGroceryItems.sort(new GroceryItem.NameComparator());
                break;
            case itemNameDESC:
                sortedGroceryItems.sort(new GroceryItem.NameComparator().reversed());
                break;
        }

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        GroceryItem groceryItem = sortedGroceryItems.get(position);

        holder.itemName.setText(groceryItem.getName());

        if (groceryItem.getExpirationDate() == null) {
            createNonExpirableItem(holder);
        } else {
            createExpirableItem(holder, groceryItem);
        }

        holder.cardView.setOnClickListener(v -> onItemClicked(groceryItem));
    }

    // Set default settings for the item's fragment
    private void createNonExpirableItem(@NonNull RecyclerViewHolder holder) {
        holder.itemExpirationDate.setVisibility(View.GONE);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
    }

    // Add offset so item's name is not centered and doesn't intersect with expiration date
    private void createExpirableItem(@NonNull RecyclerViewHolder holder, GroceryItem groceryItem) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
        layoutParams.setMargins(layoutParams.getMarginStart(), 0, 0, context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._12sdp));
        holder.itemExpirationDate.setVisibility(View.VISIBLE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        holder.itemExpirationDate.setText(groceryItem.getExpirationDate().format(formatter));
    }

    @Override
    public int getItemCount() {
        return sortedGroceryItems.size();
    }

    // Filter used for groceries list items
    private class GroceryItemsListFilter extends Filter {

        FilterResults results;
        List<GroceryItem> suggestions;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            results = new FilterResults();
            suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(allGroceryItems); // Use the original list when the constraint is empty
            } else {
                String previousConstraint = searchBarFragment.getSearchBarFieldTag();
                String filterPattern = constraint.toString().toLowerCase();
                for (GroceryItem item : filterPattern.startsWith(previousConstraint) ? allGroceryItems : filteredGroceryItems) {
                    if (item.getName().toLowerCase().startsWith(filterPattern)) {
                        suggestions.add(item);
                    }
                }

                if (suggestions.size() == 0) {
                    for (GroceryItem item : allGroceryItems) {
                        for (String filterPatternWord : filterPattern.split(" ")) {
                            for (String itemName : item.getName().split(" ")) {
                                if (itemName.toLowerCase().startsWith(filterPatternWord) && !suggestions.contains(item)) {
                                    suggestions.add(item);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            // Set results from suggestions
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredGroceryItems.clear(); // Clear the filtered list before adding new filtered items
            filteredGroceryItems.addAll((List<GroceryItem>) results.values); // Add the filtered items to the list
            sortGroceryItems();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((GroceryItem) resultValue).getName();
        }
    }

    // Filter user for searching existing item names or creating one from search field
    private class AddGroceryItemsListFilter extends GroceryItemsListFilter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            super.performFiltering(constraint);
            if (constraint.length() > 0) {
                suggestions.add(0, new GroceryItem(constraint.toString()));
            }
            return results;
        }
    }

    public void setGroceryItemsListFilter() {
        currentFilter = new GroceryItemsListFilter();
    }

    public void setAddGroceryItemsListFilter() {
        currentFilter = new AddGroceryItemsListFilter();
    }

    public ArrayList<GroceryItem> getAllGroceryItems() {
        return allGroceryItems;
    }
}
