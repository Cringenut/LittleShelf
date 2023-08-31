package com.example.littleshelf.Main.GroceryItem;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.Comparator;

public class GroceryItem {

    private long id;
    private String name;
    private LocalDate expirationDate;

    public GroceryItem(int id, String name, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public GroceryItem(String name) {
        this.name = name;
    }

    public boolean isFresh() {
        if (expirationDate == null) {
            return true; // Return true if no expiration date is set
        }

        // Return if date is after tomorrow, if additional days is passed add them to expiration date
        return expirationDate.isAfter(LocalDate.now());
    }

    public boolean isFresh(Integer additionalDays) {
        if (expirationDate == null) {
            return true; // Return true if no expiration date is set
        }

        // Return if date is after tomorrow, if additional days is passed add them to expiration date
        return expirationDate.plusDays(additionalDays == null ? 0 : additionalDays).isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public static class NameComparator implements Comparator<GroceryItem> {
        @Override
        public int compare(final GroceryItem objectOne, final GroceryItem objectTwo) {
            return objectOne.getName().toLowerCase().compareTo(objectTwo.getName().toLowerCase());
        }
    }

}
