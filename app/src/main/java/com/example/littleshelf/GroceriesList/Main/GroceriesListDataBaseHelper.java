package com.example.littleshelf.GroceriesList.Main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.littleshelf.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceriesListDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private ListView listView;
    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_EXPIRATION_DATE = "ITEM_EXPIRATION_DATE";

    public GroceriesListDataBaseHelper(@Nullable Context context) {
        super(context, "items.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database or open if exists
        String createTableStatement = "CREATE TABLE " + ITEM_TABLE + " "
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ITEM_NAME + " TEXT,"
                + COLUMN_ITEM_EXPIRATION_DATE + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(GroceryItem groceryItem) {
        // Get writable database, so we can add data to it, and create empty value for data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Place data inside columns
        cv.put(COLUMN_ITEM_NAME, groceryItem.getName());
        cv.put(COLUMN_ITEM_EXPIRATION_DATE, groceryItem.getExpirationDate());

        ArrayAdapter<GroceryItem> adapter = (GroceriesListViewAdapter) listView.getAdapter();
        adapter.add(groceryItem);
        adapter.notifyDataSetChanged();

        long insert = db.insert(ITEM_TABLE, null, cv); // Add value to database
        groceryItem.setId(insert); // Set item id from database

        return insert != -1;
    }

    public boolean deleteOne(GroceryItem groceryItem) {
        // Get database and create delete query
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ITEM_TABLE + " WHERE " + COLUMN_ID + " = " + groceryItem.getId();

        // Call query in cursor
        Cursor cursor = db.rawQuery(queryString, null);

        ArrayAdapter<GroceryItem> adapter = (GroceriesListViewAdapter) listView.getAdapter();
        adapter.remove(groceryItem); // Deleting item from adapter
        adapter.notifyDataSetChanged(); // Calling notify to update list

        return cursor.moveToFirst(); // If we found item from query return true
    }

    public List<GroceryItem> getAllItems() {
        List<GroceryItem> returnList = new ArrayList<>(); // Create empty list for items
        String queryString = "SELECT * FROM " + ITEM_TABLE; // Query to get all rows from database

        // Get database and create cursor for iteration
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // Check if database is not empty
        if (cursor.moveToFirst()) {
            do {
                // Get data from columns at passed numbers from database
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemExpirationDate = cursor.getString(2);

                // Create item from received data
                GroceryItem newGroceryItem = new GroceryItem(itemID, itemName, itemExpirationDate);
                returnList.add(newGroceryItem);
            } while (cursor.moveToNext());
        }
        else {
            return returnList; // Return empty list if database is empty
        }

        // Closing cursor and db for optimization
        cursor.close();
        db.close();
        return returnList;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
