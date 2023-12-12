package com.example.littleshelf.Models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

public class GroceryModel {

    private Grocery grocery;

    public GroceryModel(Grocery grocery) {
        this.grocery = grocery;
    }

    public LiveData<String> getGroceryName() {
        return grocery.getGroceryName();
    }

    public void setGroceryName(String name) {
        grocery.setGroceryName(name);
    }
}
