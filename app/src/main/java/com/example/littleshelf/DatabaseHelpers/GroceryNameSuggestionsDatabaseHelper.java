package com.example.littleshelf.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GroceryNameSuggestionsDatabaseHelper extends SQLiteOpenHelper {

    public static final String NAME_SUGGESTIONS_TABLE = "NAME_SUGGESTIONS_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME_SUGGESTION = "NAME_SUGGESTION";
    public GroceryNameSuggestionsDatabaseHelper(@Nullable Context context) {
        super(context, "name_suggestions.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database or open if exists
        String createTableStatement = "CREATE TABLE " + NAME_SUGGESTIONS_TABLE + " "
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_SUGGESTION + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<String> getAllItems() {
        List<String> returnList = new ArrayList<>();
        // Query to get all rows from database
        String queryString = "SELECT * FROM " + NAME_SUGGESTIONS_TABLE;

        // Get database and create cursor for iteration
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        // Check if database is not empty
        if (cursor.moveToFirst()) {
            do {
                // Get data from columns at passe numbers from database
                String nameSuggestion = cursor.getString(1);
                returnList.add(nameSuggestion);
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
