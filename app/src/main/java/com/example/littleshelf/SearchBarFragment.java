package com.example.littleshelf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SearchBarFragment extends Fragment {

    private AddItemListFragment parentFragment;

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
        parentFragment.setupSearchBarField(searchBarField);
        return v;
    }

    public void setParentFragment(AddItemListFragment parentFragment) {
        this.parentFragment = parentFragment;
    }
}