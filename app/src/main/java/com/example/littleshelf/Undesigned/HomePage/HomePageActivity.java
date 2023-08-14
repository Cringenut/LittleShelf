package com.example.littleshelf.Undesigned.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListDataBaseHelper;
import com.example.littleshelf.R;

public class HomePageActivity extends AppCompatActivity {

    private GroceriesListDataBaseHelper groceriesListDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        groceriesListDataBaseHelper = new GroceriesListDataBaseHelper(HomePageActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public GroceriesListDataBaseHelper getDataBaseHelper() {
        return groceriesListDataBaseHelper;
    }
}