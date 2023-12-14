package com.example.littleshelf.Models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

public class GroceryModel {

    private Grocery grocery;

    public GroceryModel(Grocery grocery) {
        this.grocery = grocery;
        setGroceryName(grocery.getName());
    }

    public Grocery getGrocery() {
        return grocery;
    }

    private MutableLiveData<String> groceryName = new MutableLiveData<>();

    public LiveData<String> getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String name) {
        groceryName.setValue(name);
    }
}
