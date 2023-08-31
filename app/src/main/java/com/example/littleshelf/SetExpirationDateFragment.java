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

import com.example.littleshelf.Objects.GroceryItem;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

public class SetExpirationDateFragment extends Fragment {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    private LocalDate selectedDate;
    private final GroceryItem groceryItem;
    private CalendarRecyclerViewAdapter calendarRecyclerViewAdapter;
    private ConstraintLayout holderDateInfo;
    private TextView textViewNotSet;

    public SetExpirationDateFragment(GroceryItem groceryItem) {
        this.groceryItem = groceryItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_set_expiration_date, container, false);
        v.findViewById(R.id.btnBack).setOnClickListener(btnConfirm -> closeDatePicker());

        textViewNotSet = v.findViewById(R.id.textViewNotSet);
        holderDateInfo = v.findViewById(R.id.holderDateInfo);
        if (groceryItem.getExpirationDate() == null) {
            selectedDate = LocalDate.now();
        }
        else {
            selectedDate = groceryItem.getExpirationDate();
            setTextHolderDateInfo(String.valueOf(selectedDate.getDayOfMonth()), selectedDate.getMonth().toString(), String.valueOf(selectedDate.getYear()));
            v.findViewById(R.id.linearLayoutActionButtons).setVisibility(View.VISIBLE);
        }

        v.findViewById(R.id.btnPrevMonth).setOnClickListener(btnPrev -> previousMonthAction(v));
        v.findViewById(R.id.btnNextMonth).setOnClickListener(btnNext -> nextMonthAction(v));

        initWidgets(v);
        setMonthView(v);
        return v;
    }

    public void setCurrentSelectedDate(View v, String dayText) {
        textViewNotSet.setVisibility(View.GONE);
        holderDateInfo.setVisibility(View.VISIBLE);
        setTextHolderDateInfo(dayText, selectedDate.getMonth().toString(), String.valueOf(selectedDate.getYear()));

        v.findViewById(R.id.linearLayoutActionButtons).setVisibility(View.VISIBLE);
        v.findViewById(R.id.btnClearDate).setOnClickListener(btnClear -> clearSelectedDate(v));
        v.findViewById(R.id.btnConfirmDate).setOnClickListener(btnConfirm -> acceptSelectedDate(v));


        if (getCurrentSelectedDate(v).isBefore(LocalDate.now().plusDays(1))) {
            ((TextView) holderDateInfo.findViewById(R.id.textViewDaysBeforeExpiration)).setText("Expired");
        }
        else {
            ((TextView) holderDateInfo.findViewById(R.id.textViewDaysBeforeExpiration)).setText(
                    ChronoUnit.DAYS.between(LocalDate.now(), getCurrentSelectedDate(v)) + " days");
        }

        selectedDate = getCurrentSelectedDate(v);
    }

    private void acceptSelectedDate(View v) {
        groceryItem.setExpirationDate(getCurrentSelectedDate(v));
        closeDatePicker();
    }

    private void closeDatePicker() {
        GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();

        groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ((AddItemMenuFragment) groceriesActivity.getSupportFragmentManager().findFragmentById(R.id.containerBottomFragment))
                .getBtnItemExpirationDate().setText(
                        groceryItem.getExpirationDate() != null ? groceryItem.getExpirationDate().format(formatter) : "");
        groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .show(Objects.requireNonNull(groceriesActivity.getSupportFragmentManager()
                        .findFragmentById(R.id.containerBottomFragment)))
                .commit();
    }

    public LocalDate getCurrentSelectedDate(View v) {
        String day = ((TextView) v.findViewById(R.id.textViewExpirationDay)).getText().toString();
        String month = ((TextView) v.findViewById(R.id.textViewExpirationMonth)).getText().toString();
        String year = ((TextView) v.findViewById(R.id.textViewExpirationYear)).getText().toString();

        if (day.equals("") || month.equals("") || year.equals("")) {
            return null;
        }

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMMM yyyy").toFormatter();
        return LocalDate.parse(day + " " + month + " " + year, formatter);
    }

    private void clearTextHolderDateInfo() {
        ((TextView) holderDateInfo.findViewById(R.id.textViewExpirationDay)).setText("");
        ((TextView) holderDateInfo.findViewById(R.id.textViewExpirationMonth)).setText("");
        ((TextView) holderDateInfo.findViewById(R.id.textViewExpirationYear)).setText("");
    }

    private void setTextHolderDateInfo(String dayText, String monthText, String yearText) {
        ((TextView) holderDateInfo.findViewById(R.id.textViewExpirationDay)).setText(dayText);
        ((TextView) holderDateInfo.findViewById(R.id.textViewExpirationMonth)).setText(monthText);
        ((TextView) holderDateInfo.findViewById(R.id.textViewExpirationYear)).setText(yearText);
    }

    private void clearSelectedDate(View v) {
        textViewNotSet.setVisibility(View.VISIBLE);
        holderDateInfo.setVisibility(View.GONE);

        v.findViewById(R.id.linearLayoutActionButtons).setVisibility(View.GONE);
        clearTextHolderDateInfo();

        selectedDate = LocalDate.now();
        setMonthView(v);
    }

    private void setMonthView(View v) {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> dayInMonth = daysInMonthArray(selectedDate);

        calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter(dayInMonth, (position, recyclerViewHolder) -> {
            if (!recyclerViewHolder.getDayOfMonthText().equals("")) {
                if (calendarRecyclerViewAdapter.getSelectedCalendarCell() != null && calendarRecyclerViewAdapter.getSelectedCalendarCell() != recyclerViewHolder.itemView) {
                    calendarRecyclerViewAdapter.deselectCalendarCell();
                }
                calendarRecyclerViewAdapter.selectCalendarCell(recyclerViewHolder);
                setCurrentSelectedDate(v, recyclerViewHolder.getDayOfMonthText());
            }
        }, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(v.getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarRecyclerViewAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() == 7 ? 0 : firstOfMonth
                .getDayOfWeek()
                .getValue();

        for (int i = 0; i < 42; i++) {
            if (i < dayOfWeek || i >= daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek + 1));
            }
        }

        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets(View v) {
        calendarRecyclerView = v.findViewById(R.id.recyclerViewCalendar);
        monthYearText = v.findViewById(R.id.textViewMonthYear);
    }

    public void previousMonthAction(View v) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView(v);
    }

    public void nextMonthAction(View v) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView(v);
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}