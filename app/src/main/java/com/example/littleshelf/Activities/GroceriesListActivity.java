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

        /*listView = getSupportFragmentManager().findFragmentById(R.id.listView).getView().findViewById(R.id.idListView);
        showGroceriesItemsOnListView(dataBaseHelper);

        */

        ((FloatingActionButton) findViewById(R.id.buttonCheese)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (getSupportFragmentManager().findFragmentById(R.id.addItem).isVisible()) {
                    fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
                }
                else {
                    fragmentTransaction.show(getSupportFragmentManager().findFragmentById(R.id.addItem));

                    ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonAdd))
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String addGroceryItemName = ((TextInputEditText) getSupportFragmentManager()
                                            .findFragmentById(R.id.addItem).getView()
                                            .findViewById(R.id.textInputField)).getText().toString();

                                    if (addGroceryItemName.length() > 0) {
                                        String date = ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonDate)).getText().toString();
                                        dataBaseHelper.addOne(new GroceryItem(-1, addGroceryItemName, date));
                                        showGroceriesItemsOnListView(dataBaseHelper);

                                        ((TextInputEditText) getSupportFragmentManager()
                                                .findFragmentById(R.id.addItem).getView()
                                                .findViewById(R.id.textInputField)).setText("");

                                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
                                        fragmentTransaction.commit();

                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                                    }
                                }
                            });

                    ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonDate))
                            .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String date = dayOfMonth + " " + month + " " + year;
                                    ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonDate)).setText(date);
                                }
                            };

                            Calendar calendar = Calendar.getInstance();
                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH);
                            int day = calendar.get(Calendar.DAY_OF_MONTH);

                            int style = AlertDialog.THEME_HOLO_LIGHT;
                            DatePickerDialog datePickerDialog = new DatePickerDialog(GroceriesListActivity.this, style, dateSetListener, year, month, day);

                            datePickerDialog.show();

                        }
                    });
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void showGroceriesItemsOnListView(DataBaseHelper dataBaseHelper) {
        if (listView != null) {

        }
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