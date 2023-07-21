package com.example.littleshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.littleshelf.items.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private ListView listView;
    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_EXPIRATION_DATE = "ITEM_EXPIRATION_DATE";

    public DataBaseHelper(@Nullable Context context, @Nullable ListView listView) {
        super(context, "items.db", null, 1);
        this.context = context;
        this.listView = listView;
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

        // Add value to database
        long insert = db.insert(ITEM_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteOne(GroceryItem groceryItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ITEM_TABLE + " WHERE " + COLUMN_ID + " = " + groceryItem.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public List<GroceryItem> getAllItems() {
        // Create empty list to put items inside it
        List<GroceryItem> returnList = new ArrayList<>();
        // Query to get all rows from database
        String queryString = "SELECT * FROM " + ITEM_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // Check if database is not empty
        if (cursor.moveToFirst()) {
            do {
                //
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemExpirationDate = cursor.getString(2);

                // Create item from received data
                GroceryItem newGroceryItem = new GroceryItem(itemID, itemName, itemExpirationDate);
                returnList.add(newGroceryItem);
            } while (cursor.moveToNext());
        }
        else {
            return returnList;
        }

        // Closing cursor and db for optimization
        cursor.close();
        db.close();
        return returnList;
    }

    public void showListViewItems() {
        GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(context, R.layout.fragment_list_item, (ArrayList<GroceryItem>) getAllItems());
        listView.setAdapter(groceriesListViewAdapter);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
