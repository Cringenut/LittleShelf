package com.example.littleshelf.ShelfGroceriesActivity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.databinding.ViewGroceryBinding;

import java.util.Arrays;
import java.util.List;



public class GroceriesRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesRecyclerViewAdapter.GroceriesRecyclerViewHolder> {

    List<Grocery> testList = Arrays.asList(
            new Grocery.GroceryBuilder("NewGrocery")
            .build());

    @NonNull
    @Override
    public GroceriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroceriesRecyclerViewHolder(ViewGroceryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    static class GroceriesRecyclerViewHolder extends RecyclerView.ViewHolder {
        // Variables from grocery item view
        public GroceriesRecyclerViewHolder(@NonNull ViewGroceryBinding binding) {
            super(binding.getRoot());
        }
    }

}
