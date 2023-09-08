package com.example.littleshelf.Groceries;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.littleshelf.Groceries.AddGroceryItem.AddItemMenuFragment;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.RecyclerViewOnGroceryItemClickInterface;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.SortGroceriesListFragment;
import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;
import com.example.littleshelf.Groceries.SearchBar.SearchBarFragment;
import com.example.littleshelf.Main.Sort.SortButtonFragment;
import com.example.littleshelf.Groceries.AddGroceryItem.SuggestionListFragment;

import java.util.ArrayList;

public class GroceriesActivity extends AppCompatActivity implements RecyclerViewOnGroceryItemClickInterface {

    private GroceriesDataBaseHelper groceriesDataBaseHelper;
    private GroceriesRecyclerViewAdapter groceriesRecyclerViewAdapter;
    private GroceriesRecyclerViewFragment groceriesRecyclerView;
    private SearchBarFragment searchBar;
    private GroceryItem selectedGroceryItem;

    public GroceriesDataBaseHelper getGroceriesListDataBaseHelper() {
        return groceriesDataBaseHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_activity_groceries);

        // Creating database for current groceries
        groceriesDataBaseHelper = new GroceriesDataBaseHelper(this);

        // Obtain an instance of the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Main functions
        createAddButton(fragmentManager);
        createSearchBarAndSortButton(fragmentManager);
        createMainGroceriesList(fragmentManager);
    }

    private void createMainGroceriesList(FragmentManager fragmentManager) {
        // Create recycler view fragment
        groceriesRecyclerView = new GroceriesRecyclerViewFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.containerRecyclerViewReceipts, groceriesRecyclerView)
                .commit(); // Replacing container

        // Set main recycler view properties
        groceriesRecyclerViewAdapter = new GroceriesRecyclerViewAdapter(this, searchBar, (ArrayList<GroceryItem>)(groceriesDataBaseHelper.getAllItems()), this);
        groceriesRecyclerView.setRecyclerViewAdapter(groceriesRecyclerViewAdapter);
        groceriesRecyclerView.getRecyclerViewAdapter().setGroceryItemsListFilter();
        groceriesDataBaseHelper.setRecyclerView(groceriesRecyclerView);

        searchBar.setGroceriesRecyclerView(groceriesRecyclerView);
    }

    private void createAddButton(FragmentManager fragmentManager) {
        // Creating add button and lambda listener
        Button buttonAdd = findViewById(R.id.btnAddNewItem);
        buttonAdd.setOnClickListener(btnAdd -> {
            // Creating new
            SuggestionListFragment addItemListFragment = new SuggestionListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerWholeScreenFragment, addItemListFragment)
                    .commit();

            // Create final add fragment and empty grocery item,
            // so we can set it's parameters directly from sub-fragments
            AddItemMenuFragment addItemMenuFragment = new AddItemMenuFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.containerBottomFragment, addItemMenuFragment)
                    .hide(addItemMenuFragment)
                    .commit();
            deselectGroceryItem(); // Always deselect, even if not selected,
                                    // so selection in new list won't be broken
            addItemMenuFragment.setGroceryItem(new GroceryItem(""));
        });
    }

    private void createSearchBarAndSortButton(FragmentManager fragmentManager) {
        // Creating new search bar fragment and replacing container
        searchBar = new SearchBarFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.containerSearchBar, searchBar)
                .commit();

        // Create sort button outside the search bar,
        // so we can assign function inside the search bar when view is initialized
        SortGroceriesListFragment sortGroceriesListFragment = new SortGroceriesListFragment();
        searchBar.setBtnFilter(new SortButtonFragment(() -> fragmentManager.beginTransaction()
                .replace(R.id.containerBottomFragment, sortGroceriesListFragment)
                .commit()));

        // If clicked on the same sort do nothing, otherwise sort and close the "sort by" fragment
        sortGroceriesListFragment.setSortByRecyclerViewInterface(sortType -> {
            if (groceriesRecyclerViewAdapter.getCurrentSort() != sortType) {
                groceriesRecyclerViewAdapter.setCurrentSort(sortType);
                groceriesRecyclerViewAdapter.sortGroceryItems();
            }

            fragmentManager.beginTransaction()
                    .remove(sortGroceriesListFragment)
                    .commit();
        });
    }

    /* OPERATIONS WITH GROCERY ITEMS (place to adapter later) */
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
        View v = groceriesRecyclerViewAdapter.recyclerView.getLayoutManager().findViewByPosition(position);
        selectedGroceryItem = groceryItem;

        Button btnRemoveItem = v.findViewById(R.id.btnRemoveItem);
        btnRemoveItem.setOnClickListener(btnRemove -> {
            groceriesDataBaseHelper.deleteOne(groceryItem);
        });

        btnRemoveItem.setVisibility(View.VISIBLE);


    }
    public void deselectGroceryItem() {
        if (selectedGroceryItem != null) {
            View v = groceriesRecyclerViewAdapter.recyclerView.getLayoutManager()
                    .findViewByPosition(groceriesRecyclerView.getRecyclerViewAdapter()
                            .getSortedGroceryItems()
                            .indexOf(selectedGroceryItem));

            v.findViewById(R.id.btnRemoveItem).setVisibility(View.GONE);
            selectedGroceryItem = null;
        }
    }

    public SearchBarFragment getSearchBar() {
        return searchBar;
    }
}