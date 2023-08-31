package com.example.littleshelf.Sort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.R;

public class SortButtonFragment extends Fragment {

    private final SortRecyclerViewInterface sortRecyclerViewInterface;

    public SortButtonFragment(SortRecyclerViewInterface sortRecyclerViewInterface) {
        this.sortRecyclerViewInterface = sortRecyclerViewInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_sort_button, container, false);
        v.findViewById(R.id.btnFilter).setOnClickListener(btnFilter -> sortRecyclerViewInterface.onSortButtonClicked());

        return v;
    }

}