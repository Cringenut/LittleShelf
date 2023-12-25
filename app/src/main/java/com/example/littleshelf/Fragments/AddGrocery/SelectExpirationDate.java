package com.example.littleshelf.Fragments.AddGrocery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.CalendarMonthsRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });


        return view;
    }

    private void addDataToList() {
        Toast.makeText(getActivity(), "ADD",
                Toast.LENGTH_SHORT).show();
    }

}