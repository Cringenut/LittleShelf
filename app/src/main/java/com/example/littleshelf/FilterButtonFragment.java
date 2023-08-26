package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.GroceriesRecyclerView.FilterRecyclerViewInterface;

public class FilterButtonFragment extends Fragment {

    private FilterRecyclerViewInterface filterRecyclerViewInterface;

    public FilterButtonFragment(FilterRecyclerViewInterface filterRecyclerViewInterface) {
        this.filterRecyclerViewInterface = filterRecyclerViewInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_filter_button, container, false);

        v.findViewById(R.id.btnFilter).setOnClickListener(btnFilter -> {
            filterRecyclerViewInterface.onFilterButtonClicked();
        });

        return v;
    }

}