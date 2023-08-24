package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.Objects;

public class AddItemFragment extends Fragment {

    private GroceryItem groceryItem;
    private Button btnItemName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_add_item, container, false);

        FrameLayout frameLayout = v.findViewById(R.id.frameLayoutRemoveFragment);
        frameLayout.setOnClickListener(frame ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(this)
                        .commit());

        btnItemName = v.findViewById(R.id.btnAddItemName);
        btnItemName.setOnClickListener(btn -> {
            GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();

            AddItemListFragment addItemListFragment = new AddItemListFragment();
            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(groceriesActivity.findViewById(R.id.containerAddItemList).getId(), addItemListFragment)
                    .commit();

            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .hide(this)
                    .commit();
        });
        btnItemName.setText(groceryItem.getName());

        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            btnItemName.setText(groceryItem.getName());
        }
    }

    public void setGroceryItem(GroceryItem groceryItem) {
        this.groceryItem = groceryItem;
    }

    public GroceryItem getGroceryItem() {
        return groceryItem;
    }
}