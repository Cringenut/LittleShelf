package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.ViewModels.AddNewGroceryViewModel;
import com.example.littleshelf.databinding.FragmentSelectGroceryNameBinding;

import java.util.List;

public class SelectGroceryNameFragment extends Fragment {
    private FragmentSelectGroceryNameBinding binding;

    public void setAddNewGroceryMenuViewModel(AddNewGroceryViewModel addNewGroceryMenuViewModel) {
        this.addNewGroceryMenuViewModel = addNewGroceryMenuViewModel;
    }

    AddNewGroceryViewModel addNewGroceryMenuViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectGroceryNameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SuggestionsRecyclerViewAdapter suggestionsRecyclerViewAdapter = new SuggestionsRecyclerViewAdapter(addNewGroceryMenuViewModel);
        binding.suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.suggestionsRecyclerView.setAdapter(suggestionsRecyclerViewAdapter);

        binding.searchBar.inputField.setText(addNewGroceryMenuViewModel.getGroceryName().getValue());
        binding.searchBar.inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        addNewGroceryMenuViewModel.getSuggestions().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                suggestionsRecyclerViewAdapter.notifyItemChanged(0);
            }
        });

        return view;
    }


}