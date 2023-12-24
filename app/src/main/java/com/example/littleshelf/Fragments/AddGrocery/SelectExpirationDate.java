package com.example.littleshelf.Fragments.AddGrocery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.ViewModels.AddGroceryViewModel;
import com.example.littleshelf.databinding.FragmentCalendarBinding;
import com.example.littleshelf.databinding.FragmentSelectGroceryNameBinding;


public class SelectExpirationDate extends Fragment {

    private FragmentCalendarBinding binding;

    private AddGroceryViewModel viewModel;

    public void setAddGroceryMenuViewModel(AddGroceryViewModel addNewGroceryMenuViewModel) {
        this.viewModel = addNewGroceryMenuViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Assuming your layout file is named fragment_select_grocery_expiration_date.xml
        // Update this line with the correct binding class
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Your other code here (if any)

        return view;
    }
}