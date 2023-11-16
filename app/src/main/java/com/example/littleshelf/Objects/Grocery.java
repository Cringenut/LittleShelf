package com.example.littleshelf.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Grocery {

    private MutableLiveData<String> name;

    public Grocery(GroceryBuilder builder) {
        this.name.setValue(builder.name);
    }

    public LiveData<String> getGroceryName() {
        return name;
    }


    public void setGroceryName(String name) {
        this.name.setValue(name);
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
