package com.example.littleshelf.Models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

import java.util.Date;

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
    private MutableLiveData<Date> groceryExpirationDate = new MutableLiveData<>();

    public LiveData<String> getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String name) {
        groceryName.setValue(name);
    }

    public LiveData<Date> getGroceryExpiration() {
        return groceryExpirationDate;
    }

    public void setGroceryExpirationDate(Date date) {
        groceryExpirationDate.setValue(date);
    }
}
