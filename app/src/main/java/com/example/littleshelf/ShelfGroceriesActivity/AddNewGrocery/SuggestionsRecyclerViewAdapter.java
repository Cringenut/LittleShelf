package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Models.AddNewGroceryMenuModel;
import com.example.littleshelf.ViewModels.AddNewGroceryMenuViewModel;
import com.example.littleshelf.databinding.ViewGroceryNameBinding;

public class SuggestionsRecyclerViewAdapter extends RecyclerView.Adapter<SuggestionsRecyclerViewAdapter.GroceriesRecyclerViewHolder> {

    private AddNewGroceryMenuViewModel addNewGroceryMenuViewModel;

    public SuggestionsRecyclerViewAdapter(AddNewGroceryMenuViewModel addNewGroceryMenuViewModel) {
        this.addNewGroceryMenuViewModel = addNewGroceryMenuViewModel;
    }

    @NonNull
    @Override
    public GroceriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroceriesRecyclerViewHolder(ViewGroceryNameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesRecyclerViewHolder holder, int position) {
        ViewGroceryNameBinding binding = holder.binding;
        binding.textViewSuggestion.setText(addNewGroceryMenuViewModel.getSuggestions().get(position));
    }

    @Override
    public int getItemCount() {
        return addNewGroceryMenuViewModel.getSuggestions().size();
    }

    static class GroceriesRecyclerViewHolder extends RecyclerView.ViewHolder {
        // Variables from grocery item view
        ViewGroceryNameBinding binding;

        public GroceriesRecyclerViewHolder(@NonNull ViewGroceryNameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
