package com.example.littleshelf.Undesigned.ReceiptsList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListActivity;
import com.example.littleshelf.Undesigned.HomePage.HomePageActivity;
import com.example.littleshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReceiptsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts_list);

        loadNavMenu();
    }
    private void loadNavMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.ReceiptsList);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.HomePage)
            {
                startActivity(new Intent(this, HomePageActivity.class));
                finish();
            }
            else if (item.getItemId() == R.id.GroceriesList)
            {
                startActivity(new Intent(this, GroceriesListActivity.class));
                finish();
            }
            else if (item.getItemId() == R.id.ReceiptsList)
            {
                return true;
            }
            return false;
        });
    }
}