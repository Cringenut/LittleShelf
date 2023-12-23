package com.example.littleshelf.ViewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Models.GroceryModel;
import com.example.littleshelf.Repositories.GroceriesRepository;
import com.example.littleshelf.Repositories.GroceryNameSuggestionsRepository;
import com.example.littleshelf.Objects.Grocery;

import java.util.Date;
import java.util.List;

public class AddGroceryViewModel extends ViewModel {
    private final GroceryNameSuggestionsRepository suggestionsRepository;
    private final GroceriesRepository groceriesRepository;
    private final GroceryModel model;

    public AddGroceryViewModel(Context context) {
        // Create model and repository for ViewModel
        this.model = new GroceryModel(new Grocery(new Grocery.GroceryBuilder("Grocery")));
        this.suggestionsRepository = new GroceryNameSuggestionsRepository(model.getGroceryName().getValue());
        this.groceriesRepository = new GroceriesRepository(context);
    }

    public LiveData<String> getGroceryName() {
        return model.getGroceryName();
    }

    public void setGroceryName(@NonNull String name) {
        model.setGroceryName(name);
    }

    public LiveData<Date> getGroceryExpirationDate() {
        return model.getGroceryExpiration();
    }

    public void setGroceryExpirationDate(@NonNull Date date) {
        model.setGroceryExpirationDate(date);
    }

    public LiveData<List<String>> getSuggestions() {
        return suggestionsRepository.getSuggestions();
    }

    // Set suggestions using charSequence from SearchBar
    public void setSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        suggestionsRepository.setSuggestions(charSequence, i, i1, i2);
    }

    // Set
      public void setOriginalName(String name) {
        suggestionsRepository.setOriginalName(name);
    }

    public void addGroceryToDatabase() {
        groceriesRepository.insertGrocery(model.getGrocery());
    }

}
