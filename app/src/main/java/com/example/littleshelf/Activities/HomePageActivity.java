package com.example.littleshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.littleshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        loadNavMenu();
    }
    private void loadNavMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.HomePage);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.HomePage)
            {
                return true;
            }
            else if (item.getItemId() == R.id.GroceriesList)
            {
                startActivity(new Intent(this, GroceriesListActivity.class));
                finish();
            }
            else if (item.getItemId() == R.id.ReceiptsList)
            {
                startActivity(new Intent(this, ReceiptsListActivity.class));
                finish();
            }
            return false;
        });
    }
}