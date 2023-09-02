package com.example.littleshelf.Groceries.AddGroceryItem;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleshelf.R;

import java.util.ArrayList;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarRecyclerViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final RecyclerViewOnCalendarDayClickInterface recyclerViewOnCalendarDayClickInterface;
    private View selectedCalendarCell;
    private final DatePickerFragment parentFragment;
    private final int defaultColor = Color.argb(255, 255, 255, 255);
    private final int selectionColor = Color.argb(255, 255, 102, 102);
    public CalendarRecyclerViewAdapter(ArrayList<String> daysOfMonth, RecyclerViewOnCalendarDayClickInterface recyclerViewOnCalendarDayClickInterface, DatePickerFragment parentFragment) {
        this.daysOfMonth = daysOfMonth;
        this.recyclerViewOnCalendarDayClickInterface = recyclerViewOnCalendarDayClickInterface;
        this.parentFragment = parentFragment;
    }

    public View getSelectedCalendarCell() {
        return selectedCalendarCell;
    }

    public class CalendarRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public String getDayOfMonthText() {
            return dayOfMonth.getText().toString();
        }

        private final TextView dayOfMonth;
        public CalendarRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dayOfMonth = itemView.findViewById(R.id.textViewDayOfMonth);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnCalendarDayClickInterface.onItemClicked(getAdapterPosition(), this);
        }
    }

    @NonNull
    @Override
    public CalendarRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.g_cell_calendar, parent, false);
        v.getLayoutParams().height = (int)(parent.getHeight() * 0.166666);
        return new CalendarRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarRecyclerViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));

        if (parentFragment.getSelectedDate().equals(parentFragment.getCurrentSelectedDate(parentFragment.getView()))) {
            if (holder.dayOfMonth.getText().equals(String.valueOf(parentFragment.getSelectedDate().getDayOfMonth()))) {
                selectCalendarCell(holder);
            }
        }
    }

    public void selectCalendarCell(@NonNull CalendarRecyclerViewHolder holder) {
        holder.itemView.findViewById(R.id.constraintLayoutMain).setBackgroundColor(selectionColor); // Set background of this view
        selectedCalendarCell = holder.itemView; // Set it as selected, so can be deselected
    }

    public void deselectCalendarCell() {
        selectedCalendarCell.findViewById(R.id.constraintLayoutMain).setBackgroundColor(defaultColor); // set default background
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface RecyclerViewOnCalendarDayClickInterface {
        void onItemClicked(int position, CalendarRecyclerViewHolder clickedItem);
    }
}
