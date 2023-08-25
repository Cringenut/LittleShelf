package com.example.littleshelf.GroceriesRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.R;
import com.example.littleshelf.RecyclerViewOnItemClickInterface;
import com.example.littleshelf.SearchBarFragment;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

/* Adapter */
public class GroceriesListRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesListRecyclerViewAdapter.RecyclerViewHolder> implements Filterable {

    private ArrayList<GroceryItem> allGroceryItems; // Original unfiltered list

    public ArrayList<GroceryItem> getFilteredGroceryItems() {
        return filteredGroceryItems;
    }

    private ArrayList<GroceryItem> filteredGroceryItems; // Filtered list
    private Filter currentFilter;
    private Context context;
    private RecyclerViewOnItemClickInterface recyclerViewOnItemClickInterface;
    private SearchBarFragment searchBarFragment;
    public RecyclerView recyclerView;

    public GroceriesListRecyclerViewAdapter(Context context, SearchBarFragment searchBarFragment, @Nullable ArrayList<GroceryItem> groceryItems, RecyclerViewOnItemClickInterface recyclerViewOnItemClickInterface) {
        this.context = context;
        this.searchBarFragment = searchBarFragment;
        this.allGroceryItems = groceryItems;
        this.filteredGroceryItems = new ArrayList<>(groceryItems);
        this.recyclerViewOnItemClickInterface = recyclerViewOnItemClickInterface;

        setGroceryItemsListFilter();
    }

    @Override
    public Filter getFilter() {
        return currentFilter;
    }

    // Create grocery item fragment for recycler view
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        recyclerView = (RecyclerView) parent;
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.d_fragment_grocery_item, parent, false));
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemExpirationDate;
        public CardView cardView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewItemName);
            itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
            cardView = itemView.findViewById(R.id.containerCardView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.itemName.setText(filteredGroceryItems.get(position).getName());

        if (filteredGroceryItems.get(position).getExpirationDate() == null) {
            createNonExpirableItem(holder);
        }
        else {
            createExpirableItem(holder, position);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewOnItemClickInterface.onItemClicked(filteredGroceryItems.get(position));
            }
        });
    }

    // Set default settings for the item's fragment
    private void createNonExpirableItem(@NonNull RecyclerViewHolder holder) {
        holder.itemExpirationDate.setVisibility(View.GONE);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
    }

    // Add offset so item's name is not centered and doesn't intersect with expiration date
    private void createExpirableItem(@NonNull RecyclerViewHolder holder, int position) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
        layoutParams.setMargins(layoutParams.getMarginStart(), 0, 0, context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._12sdp));
        holder.itemExpirationDate.setText(filteredGroceryItems.get(position).getExpirationDate());
    }

    @Override
    public int getItemCount() {
        return filteredGroceryItems.size();
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
                // Change to lambda later
                String previousConstraint = searchBarFragment.getSearchBarFieldTag();
                String filterPattern = constraint.toString().toLowerCase();
                for (GroceryItem item : filterPattern.startsWith(previousConstraint) ? allGroceryItems : filteredGroceryItems) {
                    if (item.getName().toLowerCase().startsWith(filterPattern))
                        suggestions.add(item);
                }

                if (suggestions.size() == 0) {
                    // Change to lambda later
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

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredGroceryItems.clear(); // Clear the filtered list before adding new filtered items
            filteredGroceryItems.addAll((List<GroceryItem>) results.values); // Add the filtered items to the list
            notifyDataSetChanged();
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
