package com.example.littleshelf.Undesigned.GroceriesList.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.littleshelf.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class GroceriesListActivity extends AppCompatActivity {

    private GroceriesListDataBaseHelper groceriesListDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        groceriesListDataBaseHelper = new GroceriesListDataBaseHelper(GroceriesListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);

        // Hide add item fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
        fragmentTransaction.commit();

        ((FloatingActionButton) findViewById(R.id.buttonCheese)).setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().findFragmentById(R.id.addItem).isVisible()) {
                fragmentTransaction1.hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.addItem)));
            }
            else {
                fragmentTransaction1.show(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.addItem)));
            }
            fragmentTransaction1.commit();
        });
    }

    public GroceriesListDataBaseHelper getDataBaseHelper() {
        return groceriesListDataBaseHelper;
    }
}