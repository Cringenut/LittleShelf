package com.example.littleshelf.Receipts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.R;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.RecyclerViewHolder> {

    public ArrayList<Receipt> receipts = new ArrayList<>();

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView additionDateItemAmount;
        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            additionDateItemAmount = itemView.findViewById(R.id.textViewAdditionDateItemAmount);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReceiptsRecyclerViewAdapter.RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_fragment_receipt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        holder.additionDateItemAmount.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }
}
