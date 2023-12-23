package com.example.littleshelf.Objects;

import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.littleshelf.Databases.Converters;

import java.util.Date;

@Entity(tableName = "grocery_table")
public class Grocery {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gid")
    public int gid;

    @ColumnInfo(name = "name")
    private String name = "";

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "expiration_date")
    private Date expirationDate;

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Builder to have only one constructor for Grocery
    public static class GroceryBuilder {
        private final String name;
        private Date expirationDate;

        public GroceryBuilder(String name) {
            this.name = name;
        }

        public GroceryBuilder setExpirationDate(Date date) {
            this.expirationDate = date;
            return this;
        }

        public Grocery build() {
            return new Grocery(this);
        }
    }
}
