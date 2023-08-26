package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.GroceriesRecyclerView.SortByRecyclerViewInterface;

public class SortGroceriesListFragment extends Fragment {

    private SortByRecyclerViewInterface sortByRecyclerViewInterface;

    public void setSortByRecyclerViewInterface(SortByRecyclerViewInterface sortByRecyclerViewInterface) {
        this.sortByRecyclerViewInterface = sortByRecyclerViewInterface;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_sort_groceries_list, container, false);

        v.findViewById(R.id.btnUnsorted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByRecyclerViewInterface.onSortByButtonClicked(SortTypesEnum.unsorted);
            }
        });

        v.findViewById(R.id.btnNameASC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByRecyclerViewInterface.onSortByButtonClicked(SortTypesEnum.itemNameASC);
            }
        });

        v.findViewById(R.id.btnNameDESC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByRecyclerViewInterface.onSortByButtonClicked(SortTypesEnum.itemNameDESC);
            }
        });

        return v;
    }
}