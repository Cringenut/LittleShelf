package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.littleshelf.GroceriesRecyclerView.GroceriesRecyclerViewFragment;

public class SearchBarFragment extends Fragment {

    private GroceriesRecyclerViewFragment groceriesRecyclerView;
    private EditText searchBarField;
    private FilterButtonFragment btnFilter;

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

        if (btnFilter != null) {
            getChildFragmentManager().beginTransaction()
                    .replace(v.findViewById(R.id.containerFilterButton).getId(), btnFilter)
                    .commit();
        }

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

    public FilterButtonFragment getBtnFilter() {
        return btnFilter;
    }

    public void setBtnFilter(FilterButtonFragment btnFilter) {
        this.btnFilter = btnFilter;
    }
}