package com.example.littleshelf.ShelfGroceriesActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.ViewModels.AddGroceryMenuViewModel;
import com.example.littleshelf.databinding.ButtonGroceryDataFieldBinding;
import com.example.littleshelf.databinding.FragmentAddGroceryMenuBinding;

public class AddGroceryMenuFragment extends Fragment {
    private FragmentAddGroceryMenuBinding binding;
    private ButtonGroceryDataFieldBinding nameFieldBinding;
    private final AddGroceryMenuViewModel addGroceryMenuViewModel = new AddGroceryMenuViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddGroceryMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        nameFieldBinding = ButtonGroceryDataFieldBinding.bind(binding.btnNameField.getRoot());
        initNameField();

        addGroceryMenuViewModel.getGroceryName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameFieldBinding.textFieldValue.setText(addGroceryMenuViewModel.getGroceryName().getValue());
            }
        });

        binding.btnNameField.btnField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectGroceryNameFragment selectGroceryNameFragment = new SelectGroceryNameFragment();

            }
        });

        return view;
    }

    private void initNameField() {
        nameFieldBinding.textFieldValue.setText(addGroceryMenuViewModel.getGroceryName().getValue());
        nameFieldBinding.textFieldName.setText("Name:");
    }
}