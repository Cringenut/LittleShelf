package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesListRecyclerViewAdapter;
import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;

public class SearchBarFragment extends Fragment {

    private GroceriesRecyclerViewFragment groceriesRecyclerView;

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

        EditText searchBarField = v.findViewById(R.id.editTexSearchBar);
        groceriesRecyclerView.setRecyclerViewAdapter(new GroceriesListRecyclerViewAdapter(getContext(), null));
        groceriesRecyclerView.getRecyclerViewAdapter().setAddGroceryItemsListFilter();
        searchBarField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    public void setGroceriesRecyclerView(GroceriesRecyclerViewFragment groceriesRecyclerView) {
        this.groceriesRecyclerView = groceriesRecyclerView;
    }
}