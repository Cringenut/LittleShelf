package com.example.littleshelf.ShelfGroceriesActivity.AddNewGrocery;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.littleshelf.Objects.ViewUtils;
import com.example.littleshelf.ViewModels.ShelfGroceriesViewModel;
import com.example.littleshelf.databinding.ButtonGroceryDataFieldBinding;
import com.example.littleshelf.databinding.FragmentAddGroceryMenuBinding;

public class AddNewGroceryMenuFragment extends Fragment {
    private FragmentAddGroceryMenuBinding binding;
    private ButtonGroceryDataFieldBinding nameFieldBinding;

    private final ShelfGroceriesViewModel addNewGroceryMenuViewModel =
            new ShelfGroceriesViewModel();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddGroceryMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        nameFieldBinding = ButtonGroceryDataFieldBinding.bind(binding.btnNameField.getRoot());
        initNameField();

        addNewGroceryMenuViewModel.getGroceryName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameFieldBinding.textFieldValue.setText(addNewGroceryMenuViewModel.getGroceryName().getValue());
            }
        });

        binding.btnNameField.btnField.setOnClickListener(v -> {
            SelectGroceryNameFragment selectGroceryNameFragment = new SelectGroceryNameFragment();
            selectGroceryNameFragment.setAddNewGroceryMenuViewModel(addNewGroceryMenuViewModel);
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .add(binding.getRoot().getId(), selectGroceryNameFragment, "SelectName")
                    .addToBackStack("SelectName")
                    .commit();
            ViewUtils.disableChildren(binding);
        });

        return view;
    }

    private void initNameField() {
        nameFieldBinding.textFieldValue.setText(addNewGroceryMenuViewModel.getGroceryName().getValue());
        nameFieldBinding.textFieldName.setText("Name:");
    }
}