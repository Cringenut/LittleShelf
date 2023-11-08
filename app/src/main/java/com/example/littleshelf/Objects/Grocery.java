package com.example.littleshelf.Objects;

public class Grocery {

    private String name;

    Grocery(GroceryBuilder builder) {
        this.name = builder.name;
    }

    public static class GroceryBuilder {
        private String name;

        public GroceryBuilder(String name) {
            this.name = name;
        }
        
        public Grocery build() {
            return new Grocery(this);
        }
    }
}
