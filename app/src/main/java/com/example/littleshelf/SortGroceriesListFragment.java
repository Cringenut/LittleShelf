package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Main.Sort.SortByRecyclerViewInterface;
import com.example.littleshelf.Main.Sort.SortTypesEnum;

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

        // Call sort function inside interface
        v.findViewById(R.id.btnUnsorted).setOnClickListener(btnUnsorted -> sortByRecyclerViewInterface.onSortByButtonClicked(SortTypesEnum.unsorted));
        v.findViewById(R.id.btnNameASC).setOnClickListener(btnItemNameASC -> sortByRecyclerViewInterface.onSortByButtonClicked(SortTypesEnum.itemNameASC));
        v.findViewById(R.id.btnNameDESC).setOnClickListener(btnItemNameDESC -> sortByRecyclerViewInterface.onSortByButtonClicked(SortTypesEnum.itemNameDESC));

        return v;
    }
}