package com.example.littleshelf.Models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

import java.util.Objects;

public class NewGroceryModel {

    private Grocery newGrocery;

    public NewGroceryModel() {
        this.newGrocery = new Grocery.GroceryBuilder("Test").build();
    }

    public MutableLiveData<Grocery> getNewGrocery() {
        return newGrocery;
    }

    public void setNewGroceryName(String name) {
        Objects.requireNonNull(newGrocery.getValue()).setGroceryName(name);
    }

    public LiveData<String> getNewGroceryName() {
        return Objects.requireNonNull(newGrocery.getValue()).getGroceryName();
    }


}
