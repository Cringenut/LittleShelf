package com.example.littleshelf.GroceriesRecyclerView.Filters;

import android.widget.Filter;

import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;


public class AddGroceryItemsListFilter extends Filter {

    protected ArrayList<GroceryItem> allGroceryItems; // Original unfiltered list
    protected ArrayList<GroceryItem> filteredGroceryItems; // Filtered list
    protected FilterResults results = new FilterResults();
    protected List<GroceryItem> suggestions = new ArrayList<>();
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

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
