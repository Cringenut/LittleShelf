package com.example.littleshelf.Receipts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.R;

public class AddReceiptItemRecyclerViewAdapter extends RecyclerView.Adapter<AddReceiptItemRecyclerViewAdapter.RecyclerViewHolder> {

    private Receipt receipt;

    public AddReceiptItemRecyclerViewAdapter(Receipt receipt) {
        this.receipt = receipt;
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView additionDateItemAmount;
        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            additionDateItemAmount = itemView.findViewById(R.id.textViewAdditionDateItemAmount);
        }
    }

    @NonNull
    @Override
    public AddReceiptItemRecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                if (viewType == 0) {
            return new AddReceiptItemRecyclerViewAdapter.RecyclerViewHolder(inflater.inflate(R.layout.main_fragment_grocery_item, parent, false));
        }
        return new AddReceiptItemRecyclerViewAdapter.RecyclerViewHolder(inflater.inflate(R.layout.rec_button_add_receipt_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if (position == receipt.getReceiptItems().size()) {
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return  (position == receipt.getReceiptItems().size()) ? 1 : 0;
    }

    @Override
    public int getItemCount() {
        return receipt.getReceiptItems().size();
    }
}
