package com.example.littleshelf.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.littleshelf.Databases.Grocery.GroceryDao;
import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.Repositories.GroceriesRepository;

import java.util.List;

public class ShelfGroceriesViewModel extends ViewModel {

    private final GroceriesRepository repository;


    public ShelfGroceriesViewModel(Context context) {
        repository = new GroceriesRepository(context);
    }

    public LiveData<List<Grocery>> getAllGroceries() {
        return repository.getAllGroceries();
    }
}
