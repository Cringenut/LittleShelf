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
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.R;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

/* Adapter */
public class GroceriesListRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesListRecyclerViewAdapter.RecyclerViewHolder> implements Filterable {

    private ArrayList<GroceryItem> allGroceryItems; // Original unfiltered list
    private ArrayList<GroceryItem> filteredGroceryItems; // Filtered list

    private Filter currentFilter;
    private Context context;

    public GroceriesListRecyclerViewAdapter(Context context, @Nullable ArrayList<GroceryItem> groceryItems) {
        this.context = context;

        this.allGroceryItems = groceryItems;
        this.filteredGroceryItems = new ArrayList<>(groceryItems);

        setGroceryItemsListFilter();
    }

    @Override
    public Filter getFilter() {
        return currentFilter;
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemExpirationDate;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewItemName);
            itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
        }
    }

    // Create grocery item fragment for recycler view
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.d_fragment_grocery_item, parent, false));
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
        protected FilterResults results = new FilterResults();
        protected List<GroceryItem> suggestions = new ArrayList<>();

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.addAll(allGroceryItems);

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
            if (constraint.length() > 0) {
                suggestions.add(new GroceryItem(constraint.toString()));
            }
            super.performFiltering(constraint);
            return results;
        }
    }

    public void setGroceryItemsListFilter() {
        currentFilter = new GroceryItemsListFilter();
    }

    public void setAddGroceryItemsListFilter() {
        currentFilter = new AddGroceryItemsListFilter();
    }

}
