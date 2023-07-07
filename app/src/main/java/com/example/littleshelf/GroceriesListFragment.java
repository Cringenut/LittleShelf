package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class GroceriesListFragment extends Fragment {

    ListView lv;

    SearchView searchView;
    ArrayAdapter<String> adapter;
    String[] data = {"item1", "item2", "item3"};

    public GroceriesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_groceries_list, container, false);
        lv = (ListView) view.findViewById(R.id.ListView);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
        return view;
    }
}