package com.example.littleshelf.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Repositories.NewGroceryNameSuggestionsRepository;

import java.util.List;

public class ShelfGroceriesViewModel extends ViewModel {
    private final NewGroceryNameSuggestionsRepository repository;

    public ShelfGroceriesViewModel() {
        this.repository = new NewGroceryNameSuggestionsRepository();
    }

    public LiveData<String> getGroceryName() {
        return repository.getName();
    }

    public void setGroceryName(@NonNull String name) {
        repository.setName(name);
    }

    public LiveData<List<String>> getSuggestions() {
        return repository.getSuggestions();
    }

    public void changeSuggestions() {

    }

}
