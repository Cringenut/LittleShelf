package com.example.littleshelf.Groceries.AddGroceryItem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.littleshelf.Groceries.GroceriesActivity;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;

public class AddItemMenuFragment extends Fragment {

    private GroceryItem groceryItem;
    private Button btnItemName;

    public Button getBtnItemExpirationDate() {
        return btnItemExpirationDate;
    }

    private Button btnItemExpirationDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.g_fragment_add_item_menu, container, false);
        GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();

        FrameLayout frameLayout = v.findViewById(R.id.frameLayoutRemoveFragment);
        frameLayout.setOnClickListener(frame ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(this)
                        .commit());

        btnItemName = v.findViewById(R.id.btnAddItemName);

        btnItemName.setText(groceryItem.getName());
        // Show item names
        btnItemName.setOnClickListener(btnName -> {
            SuggestionListFragment addItemListFragment = new SuggestionListFragment();
            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(groceriesActivity.findViewById(R.id.containerWholeScreenFragment).getId(), addItemListFragment)
                    .commit();

            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .hide(this)
                    .commit();
        });

        // Show date picker
        btnItemExpirationDate = v.findViewById(R.id.btnItemExpirationDate);
        btnItemExpirationDate.setOnClickListener(btnExpirationDate -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment(groceryItem);
            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(groceriesActivity.findViewById(R.id.containerWholeScreenFragment).getId(), datePickerFragment)
                    .commit();

            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .hide(this)
                    .commit();
        });

        // Add item after clicking confirming button
        Button btnAddItem = v.findViewById(R.id.btnAddNewItem);
        btnAddItem.setOnClickListener(btnAdd -> {
            groceriesActivity.getGroceriesListDataBaseHelper().addOne(groceryItem);
            groceriesActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .hide(this)
                    .commit();
        });

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