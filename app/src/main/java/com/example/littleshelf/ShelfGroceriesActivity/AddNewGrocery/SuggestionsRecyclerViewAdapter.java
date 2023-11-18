package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.ViewModels.AddNewGroceryViewModel;
import com.example.littleshelf.databinding.ViewGroceryNameBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuggestionsRecyclerViewAdapter extends RecyclerView.Adapter<SuggestionsRecyclerViewAdapter.SuggestionsRecyclerViewHolder> {

    private final List<String> suggestions;

    public SuggestionsRecyclerViewAdapter(@NonNull AddNewGroceryViewModel addNewGroceryMenuViewModel) {
        this.suggestions = new ArrayList<>(Objects
                .requireNonNull(addNewGroceryMenuViewModel
                .getSuggestions()
                .getValue()));
    }

    @NonNull
    @Override
    public SuggestionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SuggestionsRecyclerViewHolder(ViewGroceryNameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsRecyclerViewHolder holder, int position) {
        ViewGroceryNameBinding binding = holder.binding;
        binding.textViewSuggestion.setText(suggestions.get(position));
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    static class SuggestionsRecyclerViewHolder extends RecyclerView.ViewHolder {
        ViewGroceryNameBinding binding;

        public SuggestionsRecyclerViewHolder(@NonNull ViewGroceryNameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
