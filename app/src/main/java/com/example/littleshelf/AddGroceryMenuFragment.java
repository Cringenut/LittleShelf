package com.example.littleshelf;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        dataFieldBinding = ButtonGroceryDataFieldBinding.bind(binding.btnNameField.getRoot());
        dataFieldBinding.textFieldValue.setText("65");
        dataFieldBinding.textFieldName.setText("Grocery:");

        return view;
    }
}