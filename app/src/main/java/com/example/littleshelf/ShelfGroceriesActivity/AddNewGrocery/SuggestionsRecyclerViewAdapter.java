package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.ViewModels.AddNewGroceryMenuViewModel;
import com.example.littleshelf.databinding.ViewGroceryNameBinding;

public class SuggestionsRecyclerViewAdapter extends RecyclerView.Adapter<SuggestionsRecyclerViewAdapter.SuggestionsRecyclerViewHolder> {

    private AddNewGroceryMenuViewModel addNewGroceryMenuViewModel;

    public SuggestionsRecyclerViewAdapter(AddNewGroceryMenuViewModel addNewGroceryMenuViewModel) {
        this.addNewGroceryMenuViewModel = addNewGroceryMenuViewModel;
    }

    @NonNull
    @Override
    public SuggestionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SuggestionsRecyclerViewHolder(ViewGroceryNameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsRecyclerViewHolder holder, int position) {
        ViewGroceryNameBinding binding = holder.binding;
        binding.textViewSuggestion.setText(addNewGroceryMenuViewModel.getSuggestions().get(position));
    }

    @Override
    public int getItemCount() {
        return addNewGroceryMenuViewModel.getSuggestions().size();
    }

    static class SuggestionsRecyclerViewHolder extends RecyclerView.ViewHolder {
        ViewGroceryNameBinding binding;

        public SuggestionsRecyclerViewHolder(@NonNull ViewGroceryNameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
