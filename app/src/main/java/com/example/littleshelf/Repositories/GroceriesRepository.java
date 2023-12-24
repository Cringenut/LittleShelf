package com.example.littleshelf.Repositories;

import android.content.Context;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import com.example.littleshelf.Databases.GroceryDao;
import com.example.littleshelf.Databases.LittleShelfDatabase;
import com.example.littleshelf.Objects.Grocery;

import java.util.List;

public class GroceriesRepository {
    private final GroceryDao groceryDao;
    private final LiveData<List<Grocery>> allGroceries;

    public GroceriesRepository(Context context) {
        LittleShelfDatabase db = LittleShelfDatabase.getInstance(context);
        groceryDao = db.groceryDao();
        this.allGroceries = groceryDao.getAllGroceries();
    }

    @WorkerThread
    public void insertGrocery(Grocery grocery) {
        groceryDao.insertGrocery(grocery);
    }

    @WorkerThread
    public void deleteGrocery(Grocery grocery) {
        groceryDao.deleteGrocery(grocery);
    }

    @WorkerThread
    public void updateGrocery(Grocery grocery) {
        groceryDao.updateGrocery(grocery);
    }

    public LiveData<List<Grocery>> getAllGroceries() {
        return allGroceries;
    }
}
