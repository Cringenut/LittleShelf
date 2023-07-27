package com.example.littleshelf.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.littleshelf.GroceriesList.GroceriesListActivity;
import com.example.littleshelf.Objects.GroceriesDataBaseHelper;
import com.example.littleshelf.R;

public class HomePageActivity extends AppCompatActivity {

    private GroceriesDataBaseHelper groceriesDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        groceriesDataBaseHelper = new GroceriesDataBaseHelper(HomePageActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public GroceriesDataBaseHelper getDataBaseHelper() {
        return groceriesDataBaseHelper;
    }
}