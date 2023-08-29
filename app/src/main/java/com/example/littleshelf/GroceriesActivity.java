package com.example.littleshelf;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.littleshelf.GroceriesRecyclerView.SortByRecyclerViewInterface;
import com.example.littleshelf.GroceriesRecyclerView.SortRecyclerViewInterface;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesListDataBaseHelper;
import com.example.littleshelf.Objects.GroceryItem;
import java.util.ArrayList;

public class GroceriesActivity extends AppCompatActivity implements RecyclerViewOnGroceryItemClickInterface {

    private GroceriesListDataBaseHelper groceriesListDataBaseHelper;
    private SearchBarFragment searchBar;
    private GroceriesListRecyclerViewAdapter groceriesListRecyclerViewAdapter;
    private GroceriesRecyclerViewFragment groceriesRecyclerView;
    private GroceryItem selectedGroceryItem;
    public SearchBarFragment getSearchBar() {
        return searchBar;
    }

    public GroceriesListDataBaseHelper getGroceriesListDataBaseHelper() {
        return groceriesListDataBaseHelper;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_groceries);

        groceriesListDataBaseHelper = new GroceriesListDataBaseHelper(this);

        // Obtain an instance of the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        Button buttonAdd = findViewById(R.id.btnConfirmDate);

        buttonAdd.setOnClickListener(btn -> {
            AddGroceryItemListFragment addItemListFragment = new AddGroceryItemListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerWholeScreenFragment, addItemListFragment)
                    .commit();

            AddItemFragment addItemFragment = new AddItemFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerBottomFragment, addItemFragment)
                    .hide(addItemFragment)
                    .commit();
            deselectGroceryItem();
            addItemFragment.setGroceryItem(new GroceryItem(""));


        });

        searchBar = new SearchBarFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.containerSearchBar, searchBar)
                .commit();

        SortGroceriesListFragment sortGroceriesListFragment = new SortGroceriesListFragment();
        searchBar.setBtnFilter(new SortButtonFragment(new SortRecyclerViewInterface() {
            @Override
            public void onSortButtonClicked() {
                fragmentManager.beginTransaction()
                        .replace(R.id.containerBottomFragment, sortGroceriesListFragment)
                        .commit();
            }
        }));

        sortGroceriesListFragment.setSortByRecyclerViewInterface(new SortByRecyclerViewInterface() {
            @Override
            public void onSortByButtonClicked(SortTypesEnum sortType) {
                if (groceriesListRecyclerViewAdapter.getCurrentSort() != sortType) {
                    groceriesListRecyclerViewAdapter.setCurrentSort(sortType);
                    groceriesListRecyclerViewAdapter.sortGroceryItems();
                }

                fragmentManager.beginTransaction()
                        .remove(sortGroceriesListFragment)
                        .commit();
            }
        });


        groceriesRecyclerView = new GroceriesRecyclerViewFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.containerRecyclerViewGroceries, groceriesRecyclerView)
                .commit();
        groceriesListRecyclerViewAdapter = new GroceriesListRecyclerViewAdapter(this, searchBar, (ArrayList<GroceryItem>)(groceriesListDataBaseHelper.getAllItems()), this);
        groceriesRecyclerView.setRecyclerViewAdapter(groceriesListRecyclerViewAdapter);
        groceriesRecyclerView.getRecyclerViewAdapter().setGroceryItemsListFilter();
        searchBar.setGroceriesRecyclerView(groceriesRecyclerView);
        groceriesListDataBaseHelper.setRecyclerView(groceriesRecyclerView);

    }

    @Override
    public void onItemClicked(GroceryItem groceryItem) {
        selectGroceryItem(groceryItem);
    }

    public void selectGroceryItem(GroceryItem groceryItem) {
        if (groceryItem == selectedGroceryItem){
            deselectGroceryItem();
            return;
        }
        else if (selectedGroceryItem != null) {
            deselectGroceryItem();
        }

        int position = groceriesRecyclerView.getRecyclerViewAdapter().getSortedGroceryItems().indexOf(groceryItem);
        View v = groceriesListRecyclerViewAdapter.recyclerView.getLayoutManager().findViewByPosition(position);
        selectedGroceryItem = groceryItem;

        Button btnRemoveItem = v.findViewById(R.id.btnRemoveItem);
        btnRemoveItem.setOnClickListener(btnRemove -> {
            groceriesListDataBaseHelper.deleteOne(groceryItem);
        });

        btnRemoveItem.setVisibility(View.VISIBLE);


    }
    public void deselectGroceryItem() {
        if (selectedGroceryItem != null) {
            View v = groceriesListRecyclerViewAdapter.recyclerView.getLayoutManager()
                    .findViewByPosition(groceriesRecyclerView.getRecyclerViewAdapter()
                            .getSortedGroceryItems()
                            .indexOf(selectedGroceryItem));

            ((Button) v.findViewById(R.id.btnRemoveItem)).setVisibility(View.GONE);
            selectedGroceryItem = null;
        }
    }
}