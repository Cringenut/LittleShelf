package com.example.littleshelf.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.databinding.ViewGroceryBinding;

import java.util.ArrayList;
import java.util.List;



public class GroceriesAdapter extends RecyclerView.Adapter<GroceriesAdapter.GroceriesHolder> {

    private List<Grocery> allGroceries;

    public GroceriesAdapter() {
        this.allGroceries = new ArrayList<>();
    }

    public void setGroceries(List<Grocery> groceries) {
        this.allGroceries = groceries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroceriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create ViewHolder
        return new GroceriesHolder(ViewGroceryBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesHolder holder, int position) {
        // Set values when created
        holder.binding.textViewGroceryName.setText(allGroceries
                .get(position)
                .getName());
    }

    @Override
    public int getItemCount() {
        return allGroceries.size();
    }

     class GroceriesHolder extends RecyclerView.ViewHolder {
        // Variables from grocery item view
        ViewGroceryBinding binding;
        public GroceriesHolder(@NonNull ViewGroceryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
