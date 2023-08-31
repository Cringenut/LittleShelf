package com.example.littleshelf;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarRecyclerViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final RecyclerViewOnCalendarDayClickInterface recyclerViewOnCalendarDayClickInterface;
    private View selectedCalendarCell;
    private final SetExpirationDateFragment parentFragment;
    public CalendarRecyclerViewAdapter(ArrayList<String> daysOfMonth, RecyclerViewOnCalendarDayClickInterface recyclerViewOnCalendarDayClickInterface, SetExpirationDateFragment parentFragment) {
        this.daysOfMonth = daysOfMonth;
        this.recyclerViewOnCalendarDayClickInterface = recyclerViewOnCalendarDayClickInterface;
        this.parentFragment = parentFragment;
    }

    public View getSelectedCalendarCell() {
        return selectedCalendarCell;
    }

    public void setSelectedCalendarCell(View selectedCalendarCell) {
        this.selectedCalendarCell = selectedCalendarCell;
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
        View v = inflater.inflate(R.layout.d_cell_calendar, parent, false);
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight() * 0.166666);
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
        holder.itemView.findViewById(R.id.constraintLayoutMain).setBackgroundColor(Color.argb(255, 255, 102, 102));
        selectedCalendarCell = holder.itemView;
    }

    public void deselectCalendarCell() {
        selectedCalendarCell.findViewById(R.id.constraintLayoutMain).setBackgroundColor(Color.argb(255, 255, 255, 255));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface RecyclerViewOnCalendarDayClickInterface {
        void onItemClicked(int position, CalendarRecyclerViewHolder clickedItem);
    }
}
