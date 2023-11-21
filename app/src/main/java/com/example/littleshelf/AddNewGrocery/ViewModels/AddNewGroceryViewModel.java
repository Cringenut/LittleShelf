package com.example.littleshelf.AddNewGrocery.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.AddNewGrocery.Models.GroceryModel;
import com.example.littleshelf.AddNewGrocery.Repositories.NewGroceryNameSuggestionsRepository;
import com.example.littleshelf.Objects.Grocery;

import java.util.List;

public class AddNewGroceryViewModel extends ViewModel {
    private final NewGroceryNameSuggestionsRepository repository;
    private final GroceryModel model;

    public AddNewGroceryViewModel() {
        this.model = new GroceryModel(new Grocery(new Grocery.GroceryBuilder("Grocery")));
        this.repository = new NewGroceryNameSuggestionsRepository(model.getGroceryName().getValue());
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

    public void setSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        repository.setSuggestions(charSequence, i, i1, i2);
    }

    public void setOriginalName(String name) {
        repository.setOriginalName(name);
    }

}
