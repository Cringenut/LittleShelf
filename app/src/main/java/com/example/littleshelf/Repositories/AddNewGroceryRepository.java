package com.example.littleshelf.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

import java.util.ArrayList;
import java.util.List;

public class AddNewGroceryRepository {
    private final Grocery newGrocery;
    private MutableLiveData<String> groceryName;
    private final MutableLiveData<List<String>>  suggestions = new MutableLiveData<>();

    public AddNewGroceryRepository() {
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

    public LiveData<List<String>> getSuggestions() {
        return suggestions;
    }

    public void changeSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        List<String> newSuggestions = new ArrayList<>();
        newSuggestions.add(0, charSequence.toString());

        suggestions.setValue(newSuggestions);
    }
}
