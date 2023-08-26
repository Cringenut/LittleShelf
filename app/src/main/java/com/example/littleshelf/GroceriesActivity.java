package com.example.littleshelf;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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

public class GroceriesActivity extends AppCompatActivity implements RecyclerViewOnItemClickInterface {

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
                    .replace(R.id.containerBottomFragment, addItemFragment)
                    .hide(addItemFragment)
                    .commit();
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

        int position = groceriesRecyclerView.getRecyclerViewAdapter().getFilteredGroceryItems().indexOf(groceryItem);
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
                            .getFilteredGroceryItems()
                            .indexOf(selectedGroceryItem));

            ((Button) v.findViewById(R.id.btnRemoveItem)).setVisibility(View.GONE);
            selectedGroceryItem = null;
        }
    }
}