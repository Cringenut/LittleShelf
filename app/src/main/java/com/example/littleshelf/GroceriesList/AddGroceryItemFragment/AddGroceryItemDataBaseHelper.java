package com.example.littleshelf.GroceriesList.AddGroceryItemFragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.littleshelf.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class AddGroceryItemDataBaseHelper extends SQLiteOpenHelper  {

    public static final String ITEM_TABLE = "ITEM_TABLE";

    public AddGroceryItemDataBaseHelper(@Nullable Context context) {
        super(context, "ItemsListDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<GroceryItem> getAllItems() {
        // Create empty list to put items inside it
        List<GroceryItem> returnList = new ArrayList<>();
        // Query to get all rows from database
        String queryString = "SELECT * FROM " + ITEM_TABLE;

        // Get database and create cursor for iteration
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // Check if database is not empty
        if (cursor.moveToFirst()) {
            do {
                // Get data from columns at passe numbers from database
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);

                // Create item from received data
                GroceryItem newGroceryItem = new GroceryItem(itemID, itemName, null);
                returnList.add(newGroceryItem);
            } while (cursor.moveToNext());
        } else {
            // Return empty list
            return returnList;
        }

        // Closing cursor and db for optimization
        cursor.close();
        db.close();
        return returnList;
    }
}
