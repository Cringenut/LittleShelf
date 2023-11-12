package com.example.littleshelf.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Repositories.AddNewGroceryMenuRepository;

import java.util.List;

public class ShelfGroceriesViewModel extends ViewModel {
    private final AddNewGroceryMenuRepository model;

    public ShelfGroceriesViewModel() {
        this.model = new AddNewGroceryMenuRepository();
    }

    public LiveData<String> getGroceryName() {
        return model.getName();
    }

    public void setGroceryName(@NonNull String name) {
        model.setName(name);
    }

    public List<String> getSuggestions() {
        return model.makeSuggestions();
    }

}
