package com.example.littleshelf.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Models.GroceryModel;
import com.example.littleshelf.Repositories.GroceryNameSuggestionsRepository;
import com.example.littleshelf.Objects.Grocery;

import java.util.List;

public class AddGroceryViewModel extends ViewModel {
    private final GroceryNameSuggestionsRepository repository;
    private final GroceryModel model;

    public AddGroceryViewModel() {
        // Create model and repository for ViewModel
        this.model = new GroceryModel(new Grocery(new Grocery.GroceryBuilder("Grocery")));
        this.repository = new GroceryNameSuggestionsRepository(model.getGroceryName().getValue());
    }

    public LiveData<String> getGroceryName() {
        return model.getGroceryName();
    }

    public void setGroceryName(@NonNull String name) {
        model.setGroceryName(name);
    }

    public LiveData<List<String>> getSuggestions() {
        return repository.getSuggestions();
    }

    // Set suggestions using charSequence from SearchBar
    public void setSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        repository.setSuggestions(charSequence, i, i1, i2);
    }

    // Set
      public void setOriginalName(String name) {
        repository.setOriginalName(name);
    }

}
