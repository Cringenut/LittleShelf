package com.example.littleshelf.ShelfGroceriesActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.littleshelf.AddGroceryMenuViewModel;
import com.example.littleshelf.databinding.ActivityShelfGroceriesBinding;
import com.example.littleshelf.databinding.ButtonGroceryDataFieldBinding;
import com.example.littleshelf.databinding.FragmentAddGroceryMenuBinding;

public class AddGroceryMenuFragment extends Fragment {
    private FragmentAddGroceryMenuBinding binding;
    private ButtonGroceryDataFieldBinding dataFieldBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddGroceryMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        AddGroceryMenuViewModel addGroceryMenuViewModel = new AddGroceryMenuViewModel();
        dataFieldBinding = ButtonGroceryDataFieldBinding.bind(binding.btnNameField.getRoot());
        dataFieldBinding.textFieldValue.setText(addGroceryMenuViewModel.getGroceryName().getValue());
        dataFieldBinding.textFieldName.setText("Name:");

        addGroceryMenuViewModel.getGroceryName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dataFieldBinding.textFieldValue.setText(addGroceryMenuViewModel.getGroceryName().getValue());
            }
        });

        return view;
    }
}