package com.example.littleshelf;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SetExpirationDateFragment extends Fragment {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_set_expiration_date, container, false);
        initWidgets(v);
        selectedDate = LocalDate.now();
        setMonthView(v);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView(View v) {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> dayInMonth = daysInMonthArray(selectedDate);

        CalendarRecyclerViewAdapter calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter(dayInMonth, (position, dayText, view) -> {
            if (!dayText.equals("")) {
                String message = "Selected date " + dayText + " " + monthYearFromDate(selectedDate);
                Toast.makeText(calendarRecyclerView.getContext(), message, Toast.LENGTH_SHORT).show();
                view.findViewById(R.id.constraintLayoutMain).setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));;
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(v.getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarRecyclerViewAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 35; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }

        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets(View v) {
        calendarRecyclerView = v.findViewById(R.id.recyclerViewCalendar);
        monthYearText = v.findViewById(R.id.textViewMonthYear);
    }

    public void previousMonthAction(View v) {

    }

    public void nextMonthAction(View v) {

    }
}