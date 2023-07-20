package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.littleshelf.Activities.GroceriesListActivity;
import com.example.littleshelf.items.GroceryItem;

import java.util.ArrayList;

public class GroceriesListFragment extends Fragment {

    ListView listView;
    DataBaseHelper dataBaseHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_groceries_list, container, false);

        dataBaseHelper = new DataBaseHelper(getActivity());
        listView = v.findViewById(R.id.idListView);

        showListItems();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroceryItem clickedGroceryItem = (GroceryItem) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedGroceryItem);
                showListItems();
            }
        });

        return v;
    }

    private void showListItems() {
        GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(getActivity(), R.layout.fragment_list_item, (ArrayList<GroceryItem>) dataBaseHelper.getAllItems());
        listView.setAdapter(groceriesListViewAdapter);
    }
}