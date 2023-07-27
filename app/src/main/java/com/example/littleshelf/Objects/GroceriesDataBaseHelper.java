package com.example.littleshelf.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.littleshelf.GroceriesList.GroceriesListViewAdapter;
import com.example.littleshelf.R;

import java.util.ArrayList;
import java.util.List;

public class GroceriesDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private ListView listView;
    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_EXPIRATION_DATE = "ITEM_EXPIRATION_DATE";

    public GroceriesDataBaseHelper(@Nullable Context context) {
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

        ArrayAdapter<GroceryItem> adapter = (GroceriesListViewAdapter) listView.getAdapter();
        adapter.add(groceryItem);
        adapter.notifyDataSetChanged();

        // Place data inside columns
        cv.put(COLUMN_ITEM_NAME, groceryItem.getName());
        cv.put(COLUMN_ITEM_EXPIRATION_DATE, groceryItem.getExpirationDate());

        // Add value to database
        long insert = db.insert(ITEM_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteOne(GroceryItem groceryItem) {
        // Get database and create delete query
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ITEM_TABLE + " WHERE " + COLUMN_ID + " = " + groceryItem.getId();

        ArrayAdapter<GroceryItem> adapter = (GroceriesListViewAdapter) listView.getAdapter();
        adapter.remove(groceryItem);
        adapter.notifyDataSetChanged();

        // Call query in cursor, if found delete and return true, otherwise if empty return false
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.moveToFirst();
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
                String itemExpirationDate = cursor.getString(2);

                // Create item from received data
                GroceryItem newGroceryItem = new GroceryItem(itemID, itemName, itemExpirationDate);
                returnList.add(newGroceryItem);
            } while (cursor.moveToNext());
        }
        else {
            // Return empty list
            return returnList;
        }

        // Closing cursor and db for optimization
        cursor.close();
        db.close();
        return returnList;
    }

    /*public void showListViewItems() {
        // Creating new adapter and passing it to the list view
        GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(context, R.layout.fragment_list_item, (ArrayList<GroceryItem>) getAllItems());
        listView.setAdapter(groceriesListViewAdapter);
    }*/

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
