package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.Undesigned.GroceriesList.AddGroceryItemFragment.AddGroceryItemDataBaseHelper;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;

public class AddItemListFragment extends Fragment {

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

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        getChildFragmentManager().beginTransaction();


        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            FragmentTransaction fragmentBackTransaction = fragmentManager.beginTransaction();
            fragmentBackTransaction.remove(this);
            fragmentBackTransaction.commit();
        });

        searchBar = new SearchBarFragment();
        getChildFragmentManager().beginTransaction()
                .replace(view.findViewById(R.id.containerSearchBar).getId(), searchBar)
                .commit();

        groceriesRecyclerView = new GroceriesRecyclerViewFragment();
        getChildFragmentManager().beginTransaction()
                .replace(view.findViewById(R.id.containerRecyclerViewGroceries).getId(), groceriesRecyclerView)
                .commit();
        GroceriesListRecyclerViewAdapter groceriesListRecyclerViewAdapter = new GroceriesListRecyclerViewAdapter(view.getContext(), searchBar, (ArrayList<GroceryItem>)(new AddGroceryItemDataBaseHelper(getContext()).getAllItems()));
        groceriesRecyclerView.setRecyclerViewAdapter(groceriesListRecyclerViewAdapter);
        groceriesRecyclerView.getRecyclerViewAdapter().setAddGroceryItemsListFilter();
        searchBar.setGroceriesRecyclerView(groceriesRecyclerView);

        return view;
    }
}