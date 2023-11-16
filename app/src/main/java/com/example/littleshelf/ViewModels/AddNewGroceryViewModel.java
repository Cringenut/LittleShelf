package com.example.littleshelf.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Models.GroceryModel;
import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.Repositories.NewGroceryNameSuggestionsRepository;

import java.util.List;

public class AddNewGroceryViewModel extends ViewModel {
    private final NewGroceryNameSuggestionsRepository repository;
    private final GroceryModel model;

    public AddNewGroceryViewModel() {
        this.repository = new NewGroceryNameSuggestionsRepository();
        this.model = new GroceryModel(new Grocery(new Grocery.GroceryBuilder("Grocery")));
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

    public void changeSuggestions() {

    }

}
