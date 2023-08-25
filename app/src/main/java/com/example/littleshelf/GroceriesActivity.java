package com.example.littleshelf;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListDataBaseHelper;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;
import java.util.ArrayList;

public class GroceriesActivity extends AppCompatActivity implements RecyclerViewOnItemClickInterface {

    private GroceriesListDataBaseHelper groceriesListDataBaseHelper;
    private SearchBarFragment searchBar;
    private GroceriesListRecyclerViewAdapter groceriesListRecyclerViewAdapter;
    private GroceriesRecyclerViewFragment groceriesRecyclerView;
    private GroceryItemFragment selectedGroceryItem;
    public SearchBarFragment getSearchBar() {
        return searchBar;
    }

    public GroceriesListDataBaseHelper getGroceriesListDataBaseHelper() {
        return groceriesListDataBaseHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_groceries);

        groceriesListDataBaseHelper = new GroceriesListDataBaseHelper(this);

        // Obtain an instance of the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        Button buttonAdd = findViewById(R.id.btnAddItem);

        buttonAdd.setOnClickListener(btn -> {
            AddItemListFragment addItemListFragment = new AddItemListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerAddItemList, addItemListFragment)
                    .commit();

            AddItemFragment addItemFragment = new AddItemFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerAddItem, addItemFragment)
                    .hide(addItemFragment)
                    .commit();
            addItemFragment.setGroceryItem(new GroceryItem(""));

        });

        searchBar = new SearchBarFragment();
        fragmentManager.beginTransaction()
                .replace(findViewById(R.id.containerSearchBar).getId(), searchBar)
                .commit();

        groceriesRecyclerView = new GroceriesRecyclerViewFragment();
        fragmentManager.beginTransaction()
                .replace(findViewById(R.id.containerRecyclerViewGroceries).getId(), groceriesRecyclerView)
                .commit();
        groceriesListRecyclerViewAdapter = new GroceriesListRecyclerViewAdapter(this, searchBar, (ArrayList<GroceryItem>)(groceriesListDataBaseHelper.getAllItems()), this);
        groceriesRecyclerView.setRecyclerViewAdapter(groceriesListRecyclerViewAdapter);
        groceriesRecyclerView.getRecyclerViewAdapter().setGroceryItemsListFilter();
        searchBar.setGroceriesRecyclerView(groceriesRecyclerView);
        groceriesListDataBaseHelper.setRecyclerView(groceriesRecyclerView);

    }

    @Override
    public void onItemClicked(GroceryItem groceryItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        int position = groceriesRecyclerView.getRecyclerViewAdapter().getFilteredGroceryItems().indexOf(groceryItem);
        View v = groceriesListRecyclerViewAdapter.recyclerView.getLayoutManager().findViewByPosition(position);
        ((TextView) v.findViewById(R.id.textViewItemName)).setText("Test");

    }

    public void selectGroceryItem() {

    }

    public void deselectGroceryItem() {

    }

}