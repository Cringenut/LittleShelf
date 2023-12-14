package com.example.littleshelf.Objects;

import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_table")
public class Grocery {

    @PrimaryKey
    @ColumnInfo(name = "gid")
    public int gid;

    @ColumnInfo(name = "name")
    private MutableLiveData<String> name = new MutableLiveData<>();

    public Grocery() {
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public void setGroceryName(String name) {
        this.name.setValue(name);
    }

    public Grocery(GroceryBuilder builder) {
        this.name.setValue(builder.name);
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
