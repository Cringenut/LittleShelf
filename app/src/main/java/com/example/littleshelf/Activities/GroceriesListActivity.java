package com.example.littleshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.littleshelf.DataBaseHelper;
import com.example.littleshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroceriesListActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHelper = new DataBaseHelper(GroceriesListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);

        // Hide add item fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
        fragmentTransaction.commit();

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

    public DataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }
}