package com.example.littleshelf.Main.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.littleshelf.Main.Objects.GroceryItem.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceriesNameSuggestionsDataBaseHelper extends SQLiteOpenHelper  {

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public GroceriesNameSuggestionsDataBaseHelper(@Nullable Context context) {
        super(context, "SuggestionList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database or open if exists
        String createTableStatement = "CREATE TABLE " + ITEM_TABLE + " "
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ITEM_NAME + " TEXT)";

        db.execSQL(createTableStatement);
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
