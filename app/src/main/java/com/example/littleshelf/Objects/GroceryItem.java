package com.example.littleshelf.Objects;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GroceryItem {

    private int id;
    private String name;
    private String expirationDate;

    public GroceryItem(int id, String name, String expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isFresh(@Nullable Integer additionalDays) {
        // Return true if no expiration date
        if (expirationDate.isEmpty()) {
            return true;
        }

        // Create local date variable
        LocalDate localDate;
        // If additional days is passed add them to expiration date
        /*if (additionalDays == null) {
            localDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("dd M yyyy"));
        }
        else {
            localDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("dd M yyyy")).plusDays(additionalDays);
        }*/

        // Return if date is after tomorrow
        //return localDate.isAfter(LocalDate.now());
        return true;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
