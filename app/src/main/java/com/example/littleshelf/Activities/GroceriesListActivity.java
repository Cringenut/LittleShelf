package com.example.littleshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.littleshelf.GroceriesListViewAdapter;
import com.example.littleshelf.GroceryItem;
import com.example.littleshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class GroceriesListActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);

        loadNavMenu();

        listView = findViewById(R.id.ListView);

        ArrayList<GroceryItem> arrayList = new ArrayList<>();

        arrayList.add(new GroceryItem());
        arrayList.add(new GroceryItem());
        arrayList.add(new GroceryItem());
        arrayList.add(new GroceryItem());

        GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(this, R.layout.list_item, arrayList);
        listView.setAdapter(groceriesListViewAdapter);

    }
    private void loadNavMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.GroceriesList);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.HomePage)
            {
                startActivity(new Intent(this, HomePageActivity.class));
                finish();
            }
            else if (item.getItemId() == R.id.GroceriesList)
            {
                return true;
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