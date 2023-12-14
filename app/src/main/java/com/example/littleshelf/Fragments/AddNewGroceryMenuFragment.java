package com.example.littleshelf.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.littleshelf.Objects.ViewUtils;
import com.example.littleshelf.ViewModels.AddGroceryViewModel;
import com.example.littleshelf.databinding.ButtonGroceryDataFieldBinding;
import com.example.littleshelf.databinding.FragmentAddGroceryMenuBinding;

public class AddNewGroceryMenuFragment extends Fragment {
    private FragmentAddGroceryMenuBinding binding;
    private ButtonGroceryDataFieldBinding nameFieldBinding;
    // Creating a ViewModel responsible for fragment on object initialization
    private final AddGroceryViewModel viewModel = new AddGroceryViewModel(getContext());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create a binding for this fragment
        binding = FragmentAddGroceryMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Create a binding fir NameField
        nameFieldBinding = ButtonGroceryDataFieldBinding.bind(binding.btnNameField.getRoot());
        initNameField();

        // Set a new name value for NameField when new one is chosen
        viewModel.getGroceryName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameFieldBinding.textFieldValue.setText(viewModel.getGroceryName().getValue());
                // Set a new Grocery name as original
                viewModel.setOriginalName(viewModel.getGroceryName().getValue());
            }
        });

        // Select the Grocery name when clicked
        binding.btnNameField.btnField.setOnClickListener(v -> {
            // Create a fragment
            SelectGroceryNameFragment selectGroceryNameFragment = new SelectGroceryNameFragment();
            // Take a ViewModel from here in order to manipulate data using it without creating a separate one
            selectGroceryNameFragment.setAddNewGroceryMenuViewModel(viewModel);
            // Begin transaction
            FragmentTransaction fragmentTransaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            // Add fragment to screen and BackStack
            fragmentTransaction
                    .add(binding.getRoot().getId(), selectGroceryNameFragment, "SelectName")
                    .addToBackStack("SelectName")
                    .commit();
            //
            ViewUtils.disableChildren(binding);
        });

        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addGroceryToDatabase();
                ((Activity) getContext()).onBackPressed();
            }
        });

        return view;
    }

    private void initNameField() {
        // Set values for NameField button
        nameFieldBinding.textFieldValue.setText(viewModel.getGroceryName().getValue());
        nameFieldBinding.textFieldName.setText("Name:");
    }
}