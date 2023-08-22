package com.example.littleshelf.GroceriesRecyclerView.Filters;

import com.example.littleshelf.Undesigned.Objects.GroceryItem;

public class GroceryItemsListFilter extends AddGroceryItemsListFilter {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        if (constraint.length() > 0) {
            suggestions.add(new GroceryItem(constraint.toString()));
        }
        super.performFiltering(constraint);
        return null;
    }
}
