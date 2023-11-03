package com.example.littleshelf;

public class Grocery {

    Grocery(GroceryBuilder builder) {

    }

    public static class GroceryBuilder {
        public Grocery build() {
            return new Grocery(this);
        }
    }
}
