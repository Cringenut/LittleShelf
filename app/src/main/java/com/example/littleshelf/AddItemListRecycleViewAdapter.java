package com.example.littleshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.List;

public class AddItemListRecycleViewAdapter extends RecyclerView.Adapter<AddItemListRecycleViewAdapter.RecycleViewHolder> {

    Context context;
    List<GroceryItem> groceryItems;

    static class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemExpirationDate;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewItemName);
            itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
        }
    }

    private final Filter itemsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };

    public AddItemListRecycleViewAdapter(Context context, List<GroceryItem> groceryItems) {
        this.context = context;
        this.groceryItems = groceryItems;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.d_fragment_grocery_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.itemName.setText(groceryItems.get(position).getName());

        if (groceryItems.get(position).getExpirationDate() == null) {
            holder.itemExpirationDate.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
        }
        else {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
            layoutParams.setMargins(layoutParams.getMarginStart(), 0, 0, context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._12sdp));
            holder.itemExpirationDate.setText(groceryItems.get(position).getExpirationDate());
        }
    }

    @Override
    public int getItemCount() {
        return groceryItems.size();
    }
}
