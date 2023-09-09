package com.example.littleshelf.Receipts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;

import java.util.ArrayList;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.RecyclerViewHolder> {

    public ArrayList<Receipt> receipts = new ArrayList<>();

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReceiptsRecyclerViewAdapter.RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_fragment_receipt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }
}
