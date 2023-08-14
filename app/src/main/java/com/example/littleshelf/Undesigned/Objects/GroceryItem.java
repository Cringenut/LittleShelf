package com.example.littleshelf.Undesigned.Objects;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GroceryItem {

    private long id;
    private String name;
    private String expirationDate;

    public GroceryItem(int id, String name, String expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isFresh(@Nullable Integer additionalDays) {
        if (expirationDate.isEmpty()) {
            return true; // Return true if no expiration date is set
        }

        // If additional days is passed add them to expiration date
        LocalDate localDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("d M yyyy"))
                .plusDays(additionalDays == null ? 0 : additionalDays);

        // Return if date is after tomorrow
        return localDate.isAfter(LocalDate.now());
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
