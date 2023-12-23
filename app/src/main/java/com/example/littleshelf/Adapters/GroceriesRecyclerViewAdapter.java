package com.example.littleshelf.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.databinding.ViewGroceryBinding;

import java.util.ArrayList;
import java.util.List;



public class GroceriesRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.GroceriesRecyclerViewHolder> {

    private List<Grocery> allGroceries;

    public GroceriesRecyclerViewAdapter() {
        this.allGroceries = new ArrayList<>();
    }

    public void setGroceries(List<Grocery> groceries) {
        this.allGroceries = groceries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroceriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create ViewHolder
        return new GroceriesRecyclerViewHolder(ViewGroceryBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesRecyclerViewHolder holder, int position) {
        // Set values when created
        holder.binding.textViewGroceryName.setText(allGroceries
                .get(position)
                .getName());
    }

    @Override
    public int getItemCount() {
        return allGroceries.size();
    }

    static class GroceriesRecyclerViewHolder extends RecyclerView.ViewHolder {
        // Variables from grocery item view
        ViewGroceryBinding binding;
        public GroceriesRecyclerViewHolder(@NonNull ViewGroceryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
