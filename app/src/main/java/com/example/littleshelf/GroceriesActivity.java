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
import android.widget.TextView;

import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListActivity;
import com.example.littleshelf.Undesigned.GroceriesList.Main.GroceriesListDataBaseHelper;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_groceries);


        class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

            Context context;
            List<GroceryItem> groceryItems;

            class RecycleViewHolder extends RecyclerView.ViewHolder {

                TextView itemName;
                TextView itemExpirationDate;
                public RecycleViewHolder(@NonNull View itemView) {
                    super(itemView);

                    itemName = itemView.findViewById(R.id.textViewItemName);
                    itemExpirationDate = itemView.findViewById(R.id.textViewItemExpirationDate);
                }
            }

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
                holder.itemName.setText(groceryItems.get(position).getName());

                if (groceryItems.get(position).getExpirationDate() == null) {
                    holder.itemExpirationDate.setVisibility(View.GONE);
                }
                else {
                    /*holder.itemName.setPadding(
                            holder.itemName.getPaddingLeft(),
                            holder.itemName.getPaddingTop(),
                            holder.itemName.getPaddingRight(),
                            getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._12ssp));*/
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemName.getLayoutParams();
                    layoutParams.setMargins(layoutParams.getMarginStart(), 0, 0, getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._12sdp));
                    holder.itemExpirationDate.setText(groceryItems.get(position).getExpirationDate());
                }
            }

            @Override
            public int getItemCount() {
                return groceryItems.size();
            }
        }

        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>();
        GroceriesListDataBaseHelper groceriesListDataBaseHelper = new GroceriesListDataBaseHelper(GroceriesActivity.this);
        groceryItems = groceriesListDataBaseHelper.getAllItems();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewGroceries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewGroceryItemsDecorator(this));
        recyclerView.setAdapter(new RecycleViewAdapter(this, groceryItems));

    }
}