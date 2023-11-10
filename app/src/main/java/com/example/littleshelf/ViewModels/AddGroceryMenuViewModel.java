package com.example.littleshelf.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Models.AddGroceryMenuModel;

public class AddGroceryMenuViewModel extends ViewModel {
    private final AddGroceryMenuModel model;

    public AddGroceryMenuViewModel() {
        this.model = new AddGroceryMenuModel();
    }

    public LiveData<String> getGroceryName() {
        return model.getName();
    }

    public void setGroceryName(@NonNull String name) {
        model.setName(name);
    }

}
