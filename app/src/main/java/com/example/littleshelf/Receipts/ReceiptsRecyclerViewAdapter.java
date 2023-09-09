package com.example.littleshelf.Receipts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.RecyclerViewHolder> {


    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemExpirationDate;
        CardView cardView;
        FrameLayout expirationMarker;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textViewItemName);
            itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
            cardView = itemView.findViewById(R.id.containerCardView);
            expirationMarker = itemView.findViewById(R.id.frameLayoutExpired);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
