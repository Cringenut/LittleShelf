package com.example.littleshelf.items;

import java.text.SimpleDateFormat;

public class GroceryItem {

    private int id;
    private String name;
    private SimpleDateFormat expirationDate;

    public GroceryItem(int id, String name, SimpleDateFormat expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleDateFormat getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(SimpleDateFormat expirationDate) {
        this.expirationDate = expirationDate;
    }
}
