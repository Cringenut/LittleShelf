package com.example.littleshelf.AddNewGrocery.AddNewGroceryFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.AddNewGrocery.ViewModels.AddNewGroceryViewModel;
import com.example.littleshelf.databinding.FragmentSelectGroceryNameBinding;

public class SelectGroceryNameFragment extends Fragment {
    private AddNewGroceryViewModel viewModel;
    private FragmentSelectGroceryNameBinding binding;

    public void setAddNewGroceryMenuViewModel(AddNewGroceryViewModel addNewGroceryMenuViewModel) {
        this.viewModel = addNewGroceryMenuViewModel;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectGroceryNameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SuggestionsRecyclerViewAdapter suggestionsRecyclerViewAdapter = new SuggestionsRecyclerViewAdapter(this, viewModel);
        binding.suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.suggestionsRecyclerView.setAdapter(suggestionsRecyclerViewAdapter);

        binding.searchBar.inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setSuggestions(charSequence, i, i1, i2);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        binding.searchBar.inputField.setText(viewModel.getGroceryName().getValue());

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setGroceryName(suggestionsRecyclerViewAdapter.getSelectedName().getValue());
                ((Activity) requireContext()).onBackPressed();
            }
        });

        suggestionsRecyclerViewAdapter.getSelectedName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnConfirm.setText(suggestionsRecyclerViewAdapter.getSelectedName().getValue());
            }
        });

        return view;
    }


}