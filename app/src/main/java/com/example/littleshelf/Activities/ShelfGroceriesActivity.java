package com.example.littleshelf.Activities;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Activities.Base.BaseActivity;
import com.example.littleshelf.Objects.ViewUtils;
import com.example.littleshelf.R;
import com.example.littleshelf.ShelfGroceriesActivity.AddNewGroceryFragments.AddNewGroceryMenuFragment;
import com.example.littleshelf.ShelfGroceriesActivity.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.databinding.ActivityShelfGroceriesBinding;

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

        binding.btnAddGroceryMenu.setOnClickListener(v -> {
            AddNewGroceryMenuFragment addNewGroceryMenuFragment = new AddNewGroceryMenuFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .add(binding.getRoot().getId(), addNewGroceryMenuFragment, "Menu")
                    .addToBackStack("Menu")
                    .commit();

            ViewUtils.disableChildren(binding);
        });
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GroceriesRecyclerViewAdapter adapter = new GroceriesRecyclerViewAdapter();
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider_line)));

        binding.groceriesRecyclerView.setLayoutManager(layoutManager);
        binding.groceriesRecyclerView.setAdapter(adapter);
        binding.groceriesRecyclerView.addItemDecoration(decoration);
    }

    @Override
    protected void handleOnBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.finish();
        } else {
            if (count == 1) {
                ViewUtils.enableChildren(binding);
            }
            else {
                int index = getSupportFragmentManager().getBackStackEntryCount() - 2;
                FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
                String tag = backEntry.getName();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);

                if (fragment != null) {
                    View fragmentView = fragment.getView();
                    if (fragmentView instanceof ViewGroup) {
                        ViewGroup fragmentViewGroup = (ViewGroup) fragmentView;
                        ViewUtils.enableChildren(fragmentViewGroup);
                    }
                }
                System.out.println(fragment);
            }

            getSupportFragmentManager().popBackStack();
        }
    }
}