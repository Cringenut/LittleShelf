package com.example.littleshelf.Repositories;

import android.content.Context;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import com.example.littleshelf.Databases.Grocery.GroceryDao;
import com.example.littleshelf.Databases.Grocery.GroceryDao_Impl;
import com.example.littleshelf.Databases.LittleShelfDatabase;
import com.example.littleshelf.Objects.Grocery;

import java.util.List;

public class GroceriesRepository {
    private final GroceryDao groceryDao;

    private final LiveData<List<Grocery>> allGroceries;

    public GroceriesRepository(Context context) {
        groceryDao = new GroceryDao_Impl(LittleShelfDatabase.getInstance(context));
        this.allGroceries = groceryDao.getAllGroceries();
    }


    @WorkerThread
    void insertGrocery(Grocery grocery) {
        groceryDao.insertGrocery(grocery);
    }

    @WorkerThread
    void deleteGrocery(Grocery grocery) {
        groceryDao.deleteGrocery(grocery);
    }

    @WorkerThread
    void updateGrocery(Grocery grocery) {
        groceryDao.updateGrocery(grocery);
    }
}
