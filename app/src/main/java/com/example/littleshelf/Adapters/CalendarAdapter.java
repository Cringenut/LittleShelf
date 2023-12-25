package com.example.littleshelf.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.littleshelf.databinding.ViewMonthBinding;
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder> {

    @NonNull
    @Override
    public CalendarAdapter.CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarHolder(ViewMonthBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.CalendarHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class CalendarHolder extends RecyclerView.ViewHolder {
        // Variables from grocery item view
        ViewMonthBinding binding;
        public CalendarHolder(@NonNull ViewMonthBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
