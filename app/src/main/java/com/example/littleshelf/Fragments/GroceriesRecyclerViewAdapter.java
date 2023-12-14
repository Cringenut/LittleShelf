package com.example.littleshelf.Fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Databases.LittleShelfDatabase;
import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.databinding.ViewGroceryBinding;

import java.util.Arrays;
import java.util.List;



public class GroceriesRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.GroceriesRecyclerViewHolder> {

    // TEST LIST *REMOVE LATER*
    List<Grocery> testList = Arrays.asList(
            new Grocery.GroceryBuilder("Grocery")
                    .build(),
            new Grocery.GroceryBuilder("NewGrocery")
                    .build());

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
        holder.binding.textViewGroceryName.setText(testList
                .get(position)
                .getName()
                .getValue());
    }

    @Override
    public int getItemCount() {
        return testList.size();
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
