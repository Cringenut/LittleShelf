package com.example.littleshelf.Objects;

public class Grocery {

    private String name;

    public Grocery(GroceryBuilder builder) {
        this.name = builder.name;
    }

    public String getGroceryName() {
        return name;
    }

    public void setGroceryName(String name) {
        this.name = name;
    }

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
