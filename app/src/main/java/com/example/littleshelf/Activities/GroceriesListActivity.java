package com.example.littleshelf.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littleshelf.DataBaseHelper;
import com.example.littleshelf.GroceriesListViewAdapter;
import com.example.littleshelf.R;
import com.example.littleshelf.items.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


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

        listView = getSupportFragmentManager().findFragmentById(R.id.listView).getView().findViewById(R.id.idListView);
        showGroceriesItemsOnListView(dataBaseHelper);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroceryItem clickedGroceryItem = (GroceryItem) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedGroceryItem);
                showGroceriesItemsOnListView(dataBaseHelper);
            }
        });

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
                                        dataBaseHelper.addOne(new GroceryItem(-1, addGroceryItemName));
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
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void showGroceriesItemsOnListView(DataBaseHelper dataBaseHelper) {
        if (listView != null) {
            GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(this, R.layout.list_item, (ArrayList<GroceryItem>) dataBaseHelper.getAllItems());
            listView.setAdapter(groceriesListViewAdapter);
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