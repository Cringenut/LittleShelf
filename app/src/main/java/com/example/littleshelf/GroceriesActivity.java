package com.example.littleshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListActivity;
import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListDataBaseHelper;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_groceries);

        // Obtain an instance of the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        Button buttonAdd = findViewById(R.id.btnAddItem);

        buttonAdd.setOnClickListener(vAdd -> {
            FragmentTransaction fragmentAddTransaction = fragmentManager.beginTransaction();
            AddItemListFragment addItemListFragment = new AddItemListFragment();
            fragmentAddTransaction
                    .replace(R.id.fragmentAddItemList, addItemListFragment)
                    .commit();
        });

        GroceriesRecyclerViewFragment groceriesRecyclerViewFragment = new GroceriesRecyclerViewFragment();
        groceriesRecyclerViewFragment.setRecyclerViewAdapter(new GroceriesListRecyclerViewAdapter(GroceriesActivity.this, (ArrayList<GroceryItem>) (new GroceriesListDataBaseHelper(GroceriesActivity.this).getAllItems())));
        //RecyclerView recyclerView = findViewById(R.id.containerRecyclerViewGroceries);

        GroceriesRecyclerViewFragment groceriesRecyclerView = new GroceriesRecyclerViewFragment();
        groceriesRecyclerView.setRecyclerViewAdapter(new GroceriesListRecyclerViewAdapter
                (GroceriesActivity.this, (ArrayList<GroceryItem>)(new GroceriesListDataBaseHelper(GroceriesActivity.this).getAllItems())));
        fragmentManager.beginTransaction()
                .replace(findViewById(R.id.containerRecyclerViewGroceries).getId(), groceriesRecyclerViewFragment)
                .commit();

    }
}