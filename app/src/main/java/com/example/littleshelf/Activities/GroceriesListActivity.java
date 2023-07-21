package com.example.littleshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.littleshelf.DataBaseHelper;
import com.example.littleshelf.GroceriesListViewAdapter;
import com.example.littleshelf.R;
import com.example.littleshelf.items.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class GroceriesListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);

        loadNavMenu();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
        fragmentTransaction.commit();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(GroceriesListActivity.this);

        ((FloatingActionButton) findViewById(R.id.buttonCheese)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (getSupportFragmentManager().findFragmentById(R.id.addItem).isVisible()) {
                    fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
                }
                else {
                    fragmentTransaction.show(getSupportFragmentManager().findFragmentById(R.id.addItem));
                }
                fragmentTransaction.commit();
            }
        });
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