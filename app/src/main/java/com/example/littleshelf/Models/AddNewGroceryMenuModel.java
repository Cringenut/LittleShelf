package com.example.littleshelf.Models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

import java.util.ArrayList;
import java.util.List;

public class AddNewGroceryMenuModel {

    private Grocery newGrocery;
    private MutableLiveData<String> groceryName;
    private final ArrayList<String> suggestions = new ArrayList<>();

    public AddNewGroceryMenuModel() {
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

    public List<String> makeSuggestions() {
        suggestions.add(newGrocery.getGroceryName());
        return suggestions;
    }


}
