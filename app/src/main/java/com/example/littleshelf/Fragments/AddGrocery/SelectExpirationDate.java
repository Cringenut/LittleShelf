package com.example.littleshelf.Fragments.AddGrocery;

import com.example.littleshelf.R;
import android.os.Bundle;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
        binding.CalendarMonthsRecyclerView.setLayoutManager(layoutManager);
        binding.CalendarMonthsRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });

        calculateStarterMonthsAmount();
        return view;
    }

    private void addDataToList() {
        CalendarAdapter adapter = ((CalendarAdapter)binding.CalendarMonthsRecyclerView.getAdapter());
        adapter.size += 2;
        for (int i = adapter.getItemCount() - 1; i < adapter.size; i++) {
            binding.CalendarMonthsRecyclerView.getAdapter().notifyItemInserted(i);
        }
    }

    private void calculateStarterMonthsAmount() {

        binding.CalendarMonthsRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ensure we only call this once
                binding.CalendarMonthsRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int recyclerViewHeight = binding.CalendarMonthsRecyclerView.getHeight();

                // Inflate a single view_month item to measure its height
                View viewMonth = LayoutInflater.from(binding.CalendarMonthsRecyclerView.getContext()).inflate(R.layout.view_calendar_month, null);
                viewMonth.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int itemHeight = viewMonth.getMeasuredHeight();

                // Calculate the number of items that can fit
                int numberOfItemsThatCanFit = recyclerViewHeight / itemHeight;

                CalendarAdapter adapter = new CalendarAdapter(numberOfItemsThatCanFit + 5);
                binding.CalendarMonthsRecyclerView.setAdapter(adapter);
            }
        });


    }

}