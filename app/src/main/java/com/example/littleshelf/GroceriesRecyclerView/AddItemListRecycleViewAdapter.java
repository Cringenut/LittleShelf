package com.example.littleshelf.GroceriesRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.R;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

/* Adapter */
public class AddItemListRecycleViewAdapter extends RecyclerView.Adapter<AddItemListRecycleViewAdapter.RecycleViewHolder> {

    private ArrayList<GroceryItem> allGroceryItems; // Original unfiltered list
    private ArrayList<GroceryItem> filteredGroceryItems; // Filtered list
    private Context context;

    protected class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemExpirationDate;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewItemName);
            itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
        }
    }

    /* Filters */

    // Filter used for groceries list items

    // Filter user for searching existing item names or creating one from search field

    public AddItemListRecycleViewAdapter(Context context, ArrayList<GroceryItem> groceryItems) {
        this.filteredGroceryItems = groceryItems;
    }

    // Create grocery item fragment for recycler view
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.d_fragment_grocery_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.itemName.setText(filteredGroceryItems.get(position).getName());

        if (filteredGroceryItems.get(position).getExpirationDate() == null) {
            createNonExpirableItem(holder);
        }
        else {
            createExpirableItem(holder, position);
        }
    }

    // Set default settings for the item's fragment
    private void createNonExpirableItem(@NonNull RecycleViewHolder holder) {
        holder.itemExpirationDate.setVisibility(View.GONE);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
    }

    // Add offset so item's name is not centered and doesn't intersect with expiration date
    private void createExpirableItem(@NonNull RecycleViewHolder holder, int position) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
        layoutParams.setMargins(layoutParams.getMarginStart(), 0, 0, context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._12sdp));
        holder.itemExpirationDate.setText(filteredGroceryItems.get(position).getExpirationDate());
    }

    @Override
    public int getItemCount() {
        return filteredGroceryItems.size();
    }

    private class AddGroceryItemsListFilter extends Filter {

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

}
