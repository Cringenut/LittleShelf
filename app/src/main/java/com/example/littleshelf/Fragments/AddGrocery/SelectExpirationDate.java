package com.example.littleshelf.Fragments.AddGrocery;

import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.littleshelf.Activities.ShelfGroceriesActivity;
import com.example.littleshelf.Adapters.CalendarAdapter;
import com.example.littleshelf.Adapters.GroceriesAdapter;
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

        // Use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        // Create an adapter
        CalendarAdapter adapter = new CalendarAdapter(5);

        binding.CalendarMonthsRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });
        binding.CalendarMonthsRecyclerView.setLayoutManager(layoutManager);
        binding.CalendarMonthsRecyclerView.setAdapter(adapter);



        return view;
    }

    private void addDataToList() {
        Toast.makeText(getActivity(), "ADD",
                Toast.LENGTH_SHORT).show();
        CalendarAdapter adapter = ((CalendarAdapter)binding.CalendarMonthsRecyclerView.getAdapter());
        adapter.size += 2;
        for (int i = adapter.getItemCount() - 1; i < adapter.size; i++) {
            binding.CalendarMonthsRecyclerView.getAdapter().notifyItemInserted(i);
        }
    }

}