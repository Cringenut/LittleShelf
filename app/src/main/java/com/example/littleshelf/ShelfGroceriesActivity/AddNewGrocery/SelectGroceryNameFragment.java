package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.ViewModels.ShelfGroceriesViewModel;
import com.example.littleshelf.databinding.FragmentSelectGroceryNameBinding;

public class SelectGroceryNameFragment extends Fragment {
    private FragmentSelectGroceryNameBinding binding;

    public void setAddNewGroceryMenuViewModel(ShelfGroceriesViewModel addNewGroceryMenuViewModel) {
        this.addNewGroceryMenuViewModel = addNewGroceryMenuViewModel;
    }

    ShelfGroceriesViewModel addNewGroceryMenuViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectGroceryNameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SuggestionsRecyclerViewAdapter suggestionsRecyclerViewAdapter = new SuggestionsRecyclerViewAdapter(addNewGroceryMenuViewModel);
        binding.suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.suggestionsRecyclerView.setAdapter(suggestionsRecyclerViewAdapter);

        return view;
    }


}