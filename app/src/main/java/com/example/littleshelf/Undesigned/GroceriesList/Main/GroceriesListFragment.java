package com.example.littleshelf.Undesigned.GroceriesList.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.littleshelf.R;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;

public class GroceriesListFragment extends Fragment {

    private GroceriesListActivity groceriesListActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_groceries_list, container, false);

        // Set default values and list view for database helper
        ListView listView = v.findViewById(R.id.idListView);
        groceriesListActivity = (GroceriesListActivity) getActivity();
        //groceriesListActivity.getDataBaseHelper().setListView(listView);

        // Show list items
        ArrayList<GroceryItem> groceryItems = (ArrayList<GroceryItem>) groceriesListActivity
                                              .getDataBaseHelper()
                                              .getAllItems();

        GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(groceriesListActivity, R.layout.fragment_list_item, groceryItems);
       /* groceriesListActivity.getDataBaseHelper()
                .getListView()
                .setAdapter(groceriesListViewAdapter);*/

        // Set on item click listener to delete item (temporary)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Cast to item at position and delete it from database
                GroceryItem clickedGroceryItem = (GroceryItem) parent.getItemAtPosition(position);
                groceriesListActivity.getDataBaseHelper().deleteOne(clickedGroceryItem);
            }
        });

        return v;
    }
}