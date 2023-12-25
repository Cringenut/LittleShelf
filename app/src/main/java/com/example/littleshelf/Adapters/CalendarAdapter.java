package com.example.littleshelf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.Fragments.AddGrocery.CalendarMonthView;
import com.example.littleshelf.databinding.ViewCalendarMonthBinding;
import com.example.littleshelf.databinding.ViewCalendarMonthBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder> {

    public int size;
    public CalendarAdapter(int amount) {
        size = amount;
    }

    @NonNull
    @Override
    public CalendarAdapter.CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarHolder(ViewCalendarMonthBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.CalendarHolder holder, int position) {
        Calendar calendar = Calendar.getInstance();

        // Calculate the month and year for the given position
        calendar.add(Calendar.MONTH, position);
        int displayMonth = calendar.get(Calendar.MONTH);
        int displayYear = calendar.get(Calendar.YEAR);

        // Format the month name
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        String monthName = monthFormat.format(calendar.getTime());

        // Set the text for month and year
        holder.binding.MonthAndYearText.setText(monthName + " " + displayYear);
    }
    @Override
    public int getItemCount() {
        return size;
    }

    static class CalendarHolder extends RecyclerView.ViewHolder {
        ViewCalendarMonthBinding binding;
        CalendarMonthView calendarMonthView;

        public CalendarHolder(@NonNull ViewCalendarMonthBinding binding, Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.calendarMonthView = new CalendarMonthView(context);

            // Call testFunc() here if appropriate, or elsewhere in the adapter
            this.calendarMonthView.populateMonthCells();
        }
    }
}
