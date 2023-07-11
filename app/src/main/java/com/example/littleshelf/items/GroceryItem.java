package com.example.littleshelf.items;

public class GroceryItem {

    private int id;
    private String name;

    public GroceryItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
