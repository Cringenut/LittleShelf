package com.example.littleshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.littleshelf.items.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "items.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ITEM_TABLE + " "
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ITEM_NAME + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(GroceryItem groceryItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEM_NAME, groceryItem.getName());

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
        List<GroceryItem> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + ITEM_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);

                GroceryItem newGroceryItem = new GroceryItem(itemID, itemName, null);
                returnList.add(newGroceryItem);
            } while (cursor.moveToNext());
        }
        else {
            return returnList;
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
