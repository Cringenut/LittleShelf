package com.example.littleshelf;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.littleshelf.databinding.ActivityShelfGroceriesBinding;

public class ShelfGroceriesViewActivity extends BaseActivity {

    private ActivityShelfGroceriesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShelfGroceriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.GroceriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.GroceriesRecyclerView.setAdapter(new GroceriesRecyclerViewAdapter(this));

        binding.btnAddGroceryMenu.setOnClickListener(v -> {
            AddGroceryMenuFragment addGroceryMenuFragment = new AddGroceryMenuFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .replace(binding.rootLayout.getId(), addGroceryMenuFragment)
                    .addToBackStack("Menu")
                    .commit();

            hideRootElements();
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
        for (int i = 0; i < binding.rootLayout.getChildCount(); i++) {
            binding.rootLayout.getChildAt(i)
                    .setVisibility(View.GONE);
        }
    }

    private void showRootElements() {
        for (int i = 0; i < binding.rootLayout.getChildCount(); i++) {
            binding.rootLayout.getChildAt(i)
                    .setVisibility(View.VISIBLE);
        }
    }
}