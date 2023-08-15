package com.example.littleshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_groceries);

        class RecycleViewHolder extends RecyclerView.ViewHolder {

            public RecycleViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

            Context context;
            List<GroceryItem> groceryItems;

            public RecycleViewAdapter(Context context, List<GroceryItem> groceryItems) {
                this.context = context;
                this.groceryItems = groceryItems;
            }

            @NonNull
            @Override
            public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.d_fragment_grocery_item, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return groceryItems.size();
            }
        }

        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>();
        groceryItems.add(new GroceryItem(-1, "Cheese", null));
        groceryItems.add(new GroceryItem(-1, "Cheese", null));
        groceryItems.add(new GroceryItem(-1, "Cheese", null));
        groceryItems.add(new GroceryItem(-1, "Cheese", null));
        groceryItems.add(new GroceryItem(-1, "Cheese", null));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewGroceries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecycleViewAdapter(this, groceryItems));

    }
}