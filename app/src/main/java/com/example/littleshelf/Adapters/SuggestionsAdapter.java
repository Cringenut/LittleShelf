package com.example.littleshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.ViewModels.AddGroceryViewModel;
import com.example.littleshelf.databinding.ViewGroceryNameBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestionsHolder> {

    private final List<String> suggestions;
    private MutableLiveData<String> selectedName;

    public SuggestionsAdapter(LifecycleOwner owner, @NonNull AddGroceryViewModel addNewGroceryMenuViewModel) {
        this.suggestions = new ArrayList<>(Objects
                .requireNonNull(addNewGroceryMenuViewModel
                        .getSuggestions()
                        .getValue()));

        selectedName = new MutableLiveData<>();
        selectedName.setValue(suggestions.get(0));
        addNewGroceryMenuViewModel.getSuggestions().observe(owner, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                suggestions.clear();
                suggestions.addAll(addNewGroceryMenuViewModel
                        .getSuggestions()
                        .getValue());
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public SuggestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SuggestionsHolder(ViewGroceryNameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsHolder holder, int position) {
        ViewGroceryNameBinding binding = holder.binding;
        binding.textViewSuggestion.setText(suggestions.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedName.setValue(suggestions.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public MutableLiveData<String> getSelectedName() {
        return selectedName;
    }

    static class SuggestionsHolder extends RecyclerView.ViewHolder {
        ViewGroceryNameBinding binding;

        public SuggestionsHolder(@NonNull ViewGroceryNameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
