package com.example.littleshelf.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Models.AddNewGroceryMenuModel;

import java.util.List;

public class AddNewGroceryMenuViewModel extends ViewModel {
    private final AddNewGroceryMenuModel model;

    public AddNewGroceryMenuViewModel() {
        this.model = new AddNewGroceryMenuModel();
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
