package com.example.littleshelf;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class GroceriesRecyclerViewHolder extends RecyclerView.ViewHolder {
    public GroceriesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}


public class GroceriesRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesRecyclerViewHolder> {

    @NonNull
    @Override
    public GroceriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
