package com.example.littleshelf;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.Undesigned.GroceriesList.AddGroceryItemFragment.AddGroceryItemDataBaseHelper;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.Objects;

public class SearchBarFragment extends Fragment {

    private GroceriesRecyclerViewFragment groceriesRecyclerView;
    private EditText searchBarField;

    public SearchBarFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_search_bar, container, false);

        searchBarField = v.findViewById(R.id.editTexSearchBar);
        searchBarField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                groceriesRecyclerView.getRecyclerViewAdapter().getFilter().filter(s);
                searchBarField.setTag(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        return v;
    }

    public void clearSearch() {
        groceriesRecyclerView.getRecyclerViewAdapter().getFilter().filter("");
        searchBarField.setText("");
        searchBarField.setTag("");
    }

    public void setGroceriesRecyclerView(GroceriesRecyclerViewFragment groceriesRecyclerView) {
        this.groceriesRecyclerView = groceriesRecyclerView;
    }

    public GroceriesRecyclerViewFragment getGroceriesRecyclerView() {
        return this.groceriesRecyclerView;
    }

    public String getSearchBarFieldTag() {
        return searchBarField.getTag().toString();
    }
}