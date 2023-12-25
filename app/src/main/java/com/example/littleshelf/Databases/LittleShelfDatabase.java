package com.example.littleshelf.Databases;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.littleshelf.Objects.Grocery;

@Database(entities = {Grocery.class}, version = 5)
@TypeConverters({Converters.class})
public abstract class LittleShelfDatabase extends RoomDatabase {

    private static LittleShelfDatabase instance;

    public abstract GroceryDao groceryDao();

    public static synchronized LittleShelfDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            LittleShelfDatabase.class,
                            "little_shelf_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
