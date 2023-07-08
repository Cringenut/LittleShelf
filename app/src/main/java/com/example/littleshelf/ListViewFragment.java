package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class ListViewFragment extends Fragment {

    ListView lv;

    SearchView searchView;
    BaseAdapter adapter;
    String[] data = {"item1", "item2", "item3"};

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        lv = (ListView) view.findViewById(R.id.ListView);
        //adapter = new GrociesListViewAdapter<String>();
        lv.setAdapter(adapter);
        return view;
    }
}