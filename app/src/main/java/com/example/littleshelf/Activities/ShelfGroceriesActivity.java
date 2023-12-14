package com.example.littleshelf.Activities;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Activities.Base.BaseActivity;
import com.example.littleshelf.Databases.LittleShelfDatabase;
import com.example.littleshelf.Objects.Grocery;
import com.example.littleshelf.Objects.ViewUtils;
import com.example.littleshelf.R;
import com.example.littleshelf.Fragments.AddNewGroceryMenuFragment;
import com.example.littleshelf.Fragments.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.databinding.ActivityShelfGroceriesBinding;

import java.util.List;
import java.util.Objects;

public class ShelfGroceriesActivity extends BaseActivity {

    private ActivityShelfGroceriesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShelfGroceriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initRecyclerView();

        LittleShelfDatabase.getInstance(this).groceryDao().getAllGroceries().observe(this, new Observer<List<Grocery>>() {
            @Override
            public void onChanged(List<Grocery> groceries) {

            }
        });

        binding.btnAddGroceryMenu.setOnClickListener(v -> {
            AddNewGroceryMenuFragment addNewGroceryMenuFragment = new AddNewGroceryMenuFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .add(binding.getRoot().getId(), addNewGroceryMenuFragment, "Menu")
                    .addToBackStack("Menu")
                    .commit();

            // Disable all activity children when fragment is created, so activity takes no input
            ViewUtils.disableChildren(binding);
        });
    }

    private void initRecyclerView() {

        // Use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // Create an adapter
        GroceriesRecyclerViewAdapter adapter = new GroceriesRecyclerViewAdapter();
        // Decorator to separate items
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        // Create a line between them
        decoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider_line)));

        // Setting all values to RecyclerView
        binding.groceriesRecyclerView.setLayoutManager(layoutManager);
        binding.groceriesRecyclerView.setAdapter(adapter);
        binding.groceriesRecyclerView.addItemDecoration(decoration);
    }

    @Override
    protected void handleOnBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        // If in activity close the app
        if (count == 0) {
            super.finish();
        } else {
            // When no fragments left enable children inside activity
            if (count == 1) {
                ViewUtils.enableChildren(binding);
            }
            else {
                // Get fragment index from BackStack
                int index = getSupportFragmentManager().getBackStackEntryCount() - 2;
                FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
                // Find fragment tag
                String tag = backEntry.getName();
                // Find fragment by tag
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);

                if (fragment != null) {
                    View fragmentView = fragment.getView();
                    if (fragmentView instanceof ViewGroup) {
                        ViewGroup fragmentViewGroup = (ViewGroup) fragmentView;
                        ViewUtils.enableChildren(fragmentViewGroup);
                    }
                }
                // Print fragment for debug
                System.out.println(fragment);
            }

            // Remove fragment from BackStack
            getSupportFragmentManager().popBackStack();
        }
    }
}