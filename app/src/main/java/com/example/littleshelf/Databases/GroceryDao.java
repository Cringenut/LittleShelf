package com.example.littleshelf.Databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.littleshelf.Objects.Grocery;

import java.util.List;

@Dao
public interface GroceryDao {
    @Query("SELECT * FROM grocery_table")
    LiveData<List<Grocery>> getAllGroceries();

    @Insert
    void insertGrocery(Grocery grocery);
    @Delete
    void deleteGrocery(Grocery grocery);

    @Update
    void updateGrocery(Grocery grocery);
}
