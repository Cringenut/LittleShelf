package com.example.littleshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

class GroceriesRecyclerViewHolder extends RecyclerView.ViewHolder {
    // Variables from grocery item view
    public GroceriesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}


public class GroceriesRecyclerViewAdapter extends RecyclerView.Adapter<GroceriesRecyclerViewHolder> {

    List<Grocery> testList = Arrays.asList(new Grocery[]{new Grocery.GroceryBuilder("Grocery")
            .build(), new Grocery.GroceryBuilder("Grocery")
            .build()});

    public GroceriesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public GroceriesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_item_view, parent, false);
        return new GroceriesRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceriesRecyclerViewHolder holder, int position) {
        //Grocery grocery = testList.get(position);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }
}
