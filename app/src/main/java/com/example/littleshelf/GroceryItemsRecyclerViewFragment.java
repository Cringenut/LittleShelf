package com.example.littleshelf;

import android.content.Context;
import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceryItemsRecyclerViewFragment extends RecyclerView {

    private final AddItemListRecycleViewAdapter recycleViewAdapter;
    private final Context context;
    private Filter filter;
    public GroceryItemsRecyclerViewFragment(@NonNull Context context, Filter filter) {
        super(context);
        this.context = context;
        addItemDecoration(new RecycleViewGroceryItemsDecorator());
        setLayoutManager(new LinearLayoutManager(getContext()));
        recycleViewAdapter = new AddItemListRecycleViewAdapter(getContext(), null);
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /* Decorator */
    // Decorator to create space between recycler view items
    private class RecycleViewGroceryItemsDecorator extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._4sdp);
        }
    }

    /* Adapter */
    private class AddItemListRecycleViewAdapter extends RecyclerView.Adapter<AddItemListRecycleViewAdapter.RecycleViewHolder> {
        private ArrayList<GroceryItem> allGroceryItems; // Original unfiltered list
        private ArrayList<GroceryItem> filteredGroceryItems; // Filtered list

        private class RecycleViewHolder extends RecyclerView.ViewHolder {
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
        private class ItemListFilter extends Filter {
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

        // Filter user for searching existing item names or creating one from search field
        private final class AddItemListFilter extends ItemListFilter {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint.length() > 0) {
                    suggestions.add(new GroceryItem(constraint.toString()));
                }
                super.performFiltering(constraint);
                return null;
            }
        }

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
    }

    public AddItemListRecycleViewAdapter getRecycleViewAdapter() {
        return recycleViewAdapter;
    }

}