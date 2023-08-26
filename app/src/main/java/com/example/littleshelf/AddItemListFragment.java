package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.GroceriesRecyclerView.AddGroceryItemDataBaseHelper;
import com.example.littleshelf.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.Objects;

public class AddItemListFragment extends Fragment implements RecyclerViewOnItemClickInterface {

    private Button btnBack;
    private GroceriesRecyclerViewFragment groceriesRecyclerView;
    private SearchBarFragment searchBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.d_fragment_add_item_list, container, false);

        btnBack = view.findViewById(R.id.btnBack);
        GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();
        btnBack.setOnClickListener(btn -> {
            groceriesActivity.getSupportFragmentManager().beginTransaction()
                    .remove(this)
                    .commit();
        });

        searchBar = new SearchBarFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerSearchBar, searchBar)
                .commit();

        groceriesRecyclerView = new GroceriesRecyclerViewFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerRecyclerViewGroceries, groceriesRecyclerView)
                .commit();
        GroceriesListRecyclerViewAdapter groceriesListRecyclerViewAdapter = new GroceriesListRecyclerViewAdapter(view.getContext(), searchBar, (ArrayList<GroceryItem>)(new AddGroceryItemDataBaseHelper(getContext()).getAllItems()), this);
        groceriesRecyclerView.setRecyclerViewAdapter(groceriesListRecyclerViewAdapter);
        groceriesRecyclerView.getRecyclerViewAdapter().setAddGroceryItemsListFilter();
        searchBar.setGroceriesRecyclerView(groceriesRecyclerView);

        return view;
    }

    @Override
    public void onItemClicked(GroceryItem groceryItem) {
        GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();
        /*groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .remove(this)
                .commit();*/
        groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();

        ((AddItemFragment) groceriesActivity.getSupportFragmentManager().findFragmentById(R.id.containerBottomFragment))
                .getGroceryItem().setName(groceryItem.getName());
        groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .show(Objects.requireNonNull(groceriesActivity.getSupportFragmentManager()
                        .findFragmentById(R.id.containerBottomFragment)))
                .commit();
    }
}