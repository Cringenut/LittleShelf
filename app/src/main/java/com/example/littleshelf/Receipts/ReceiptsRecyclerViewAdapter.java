package com.example.littleshelf.Receipts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Main.RecyclerView.RecyclerViewFragment;
import com.example.littleshelf.R;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.RecyclerViewHolder> {

    public ArrayList<Receipt> receipts = new ArrayList<>();

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView additionDateItemAmount;
        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            additionDateItemAmount = itemView.findViewById(R.id.textViewAdditionDateItemAmount);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return  (position == receipts.size()) ? 1 : 0;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == 0) {
            return new ReceiptsRecyclerViewAdapter.RecyclerViewHolder(inflater.inflate(R.layout.rec_fragment_receipt, parent, false));
        }
        return new ReceiptsRecyclerViewAdapter.RecyclerViewHolder(inflater.inflate(R.layout.rec_button_add_receipt_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        if (position == receipts.size()) {
            return;
        }
        holder.additionDateItemAmount.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    @Override
    public int getItemCount() {
        return receipts.size() + 1;
    }
}
