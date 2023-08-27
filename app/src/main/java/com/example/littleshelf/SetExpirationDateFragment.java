package com.example.littleshelf;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littleshelf.Objects.GroceryItem;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SetExpirationDateFragment extends Fragment {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private final GroceryItem groceryItem;
    private ConstraintLayout holderDateInfo;

    public SetExpirationDateFragment(GroceryItem groceryItem) {
        this.groceryItem = groceryItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_set_expiration_date, container, false);

        holderDateInfo = v.findViewById(R.id.holderDateInfo);
        if (groceryItem.getExpirationDate() == null) {
            selectedDate = LocalDate.now();
        }
        else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMMM yyyy").toFormatter();
            selectedDate = LocalDate.parse(groceryItem.getExpirationDate(), formatter);
            holderDateInfo.setVisibility(View.VISIBLE);
        }

        v.findViewById(R.id.btnPrevMonth).setOnClickListener(btnPrev -> previousMonthAction(v));
        v.findViewById(R.id.btnNextMonth).setOnClickListener(btnNext -> nextMonthAction(v));

        initWidgets(v);
        setMonthView(v);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void selectDateCell(View v, String dayText) {
        holderDateInfo.setVisibility(View.VISIBLE);
        ((TextView) v.findViewById(R.id.textViewExpirationDay)).setText(dayText);
        ((TextView) v.findViewById(R.id.textViewExpirationMonth)).setText(selectedDate.getMonth().toString().toUpperCase());
        ((TextView) v.findViewById(R.id.textViewExpirationYear)).setText(String.valueOf(selectedDate.getYear()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate getCurrentSelectedDate(View v) {
        String day = ((TextView) v.findViewById(R.id.textViewExpirationDay)).getText().toString();
        String month = ((TextView) v.findViewById(R.id.textViewExpirationMonth)).getText().toString();
        String year = ((TextView) v.findViewById(R.id.textViewExpirationYear)).getText().toString();

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMMM yyyy").toFormatter();
        return LocalDate.parse(day + " " + month + " " + year, formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView(View v) {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> dayInMonth = daysInMonthArray(selectedDate);

        CalendarRecyclerViewAdapter calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter(dayInMonth, (position, dayText) -> {
            if (!dayText.equals("")) {
                selectDateCell(v, dayText);
                selectedDate = getCurrentSelectedDate(v);
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
        dayOfWeek = dayOfWeek == 7 ? 0 : dayOfWeek;

        for (int i = 0; i < 42; i++) {
            if (i < dayOfWeek || i >= daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek + 1));
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View v) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView(v);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View v) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView(v);
    }
}