package com.example.littleshelf;

public class Grocery {

    private String name;

    Grocery(GroceryBuilder builder) {

    }

    public static class GroceryBuilder {
        private String name;

        GroceryBuilder(String name) {
            this.name = name;
        }
        
        public Grocery build() {
            return new Grocery(this);
        }
    }
}
