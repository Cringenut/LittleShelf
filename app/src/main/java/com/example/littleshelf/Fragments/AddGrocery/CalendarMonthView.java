package com.example.littleshelf.Fragments.AddGrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.databinding.ViewCalendarCellBinding;
import com.example.littleshelf.databinding.ViewCalendarMonthBinding;

public class CalendarMonthView extends View {
    private ViewCalendarMonthBinding binding;
    public CalendarMonthView(Context context) {
        super(context);
        binding = ViewCalendarMonthBinding.inflate(LayoutInflater.from(context));
    }

    public void populateMonthCells() {
        for (int i = 0; i < 5; i++) {
            CalendarDayView cell = new CalendarDayView(getContext());
            binding.daysGrid.addView(cell);
        }
    }
}
