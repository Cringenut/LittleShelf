package com.example.littleshelf.Objects;

import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_table")
public class Grocery {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gid")
    public int gid;

    @ColumnInfo(name = "name")
    private String name = "";

    public Grocery() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grocery(GroceryBuilder builder) {
        this.name = builder.name;
    }

    // Builder to have only one constructor for Grocery
    public static class GroceryBuilder {
        private final String name;

        public GroceryBuilder(String name) {
            this.name = name;
        }

        public Grocery build() {
            return new Grocery(this);
        }
    }
}
