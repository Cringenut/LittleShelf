package com.example.littleshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.littleshelf.items.GroceryItem;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "items.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ITEM_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(GroceryItem groceryItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEM_NAME, groceryItem.getName());

        db.insert(ITEM_TABLE, null, cv);

        return true;
    }
}
