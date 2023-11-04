package com.example.littleshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class ShelfGroceriesViewActivity extends AppCompatActivity {

    Button btnAddGroceryMenu;
    ViewGroup rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_groceries);

        rootView = findViewById(R.id.root);

        RecyclerView recyclerView = findViewById(R.id.GroceriesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GroceriesRecyclerViewAdapter(this));

        btnAddGroceryMenu = findViewById(R.id.btnAddGroceryMenu);
        btnAddGroceryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < rootView.getChildCount(); i++) {
                    rootView.getChildAt(i)
                            .setVisibility(View.GONE);
                }
            }
        });


    }
}