package com.example.littleshelf;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarRecyclerViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final RecyclerViewOnCalendarDayClickInterface recyclerViewOnCalendarDayClickInterface;
    private View selectedCalendarCell;

    public CalendarRecyclerViewAdapter(ArrayList<String> daysOfMonth, RecyclerViewOnCalendarDayClickInterface recyclerViewOnCalendarDayClickInterface) {
        this.daysOfMonth = daysOfMonth;
        this.recyclerViewOnCalendarDayClickInterface = recyclerViewOnCalendarDayClickInterface;
    }

    public View getSelectedCalendarCell() {
        return selectedCalendarCell;
    }

    public void setSelectedCalendarCell(View selectedCalendarCell) {
        this.selectedCalendarCell = selectedCalendarCell;
    }

    public class CalendarRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView dayOfMonth;
        public CalendarRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dayOfMonth = itemView.findViewById(R.id.textViewDayOfMonth);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnCalendarDayClickInterface.onItemClicked(getAdapterPosition(), dayOfMonth.getText().toString());
        }

        public void selectCalendarCell() {
            this.itemView.findViewById(R.id.constraintLayoutMain).setBackgroundColor(Color.argb(255, 255, 102, 102));
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
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface RecyclerViewOnCalendarDayClickInterface {
        void onItemClicked(int position, String dayText);
    }
}
