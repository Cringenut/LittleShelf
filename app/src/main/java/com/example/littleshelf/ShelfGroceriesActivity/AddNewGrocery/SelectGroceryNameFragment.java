package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.ViewModels.AddNewGroceryMenuViewModel;
import com.example.littleshelf.databinding.FragmentSelectGroceryNameBinding;

public class SelectGroceryNameFragment extends Fragment {
    private FragmentSelectGroceryNameBinding binding;

    public void setAddNewGroceryMenuViewModel(AddNewGroceryMenuViewModel addNewGroceryMenuViewModel) {
        this.addNewGroceryMenuViewModel = addNewGroceryMenuViewModel;
    }

    AddNewGroceryMenuViewModel addNewGroceryMenuViewModel;
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