package com.example.littleshelf;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ShelfGroceriesViewActivity extends BaseActivity {

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
                AddGroceryMenuFragment addGroceryMenuFragment = new AddGroceryMenuFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .replace(rootView.getId(), addGroceryMenuFragment)
                        .addToBackStack("Menu")
                        .commit();

                hideRootElements();
            }
        });
    }

    @Override
    protected void handleOnBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            this.finish();
        } else {
            if (count == 1) {
                showRootElements();
            }
            getSupportFragmentManager().popBackStack();
        }
    }

    private void hideRootElements() {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            rootView.getChildAt(i)
                    .setVisibility(View.GONE);
        }
    }

    private void showRootElements() {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            rootView.getChildAt(i)
                    .setVisibility(View.VISIBLE);
        }
    }
}