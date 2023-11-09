package com.example.littleshelf;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

public class AddGroceryMenuModel {

    private Grocery newGrocery;
    private MutableLiveData<String> groceryName;

    private void AddGroceryMenuViewModel() {
        this.newGrocery = new Grocery.GroceryBuilder("Test").build();
        this.groceryName = new MutableLiveData<>(newGrocery.getGroceryName());
    }

    public void setName(@NonNull String name) {
        newGrocery.setGroceryName(name);
        groceryName.setValue(name);
    }

    public LiveData<String> getName() {
        return groceryName;
    }
}
