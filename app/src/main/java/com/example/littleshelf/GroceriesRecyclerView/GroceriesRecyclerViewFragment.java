package com.example.littleshelf.GroceriesRecyclerView;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.R;

public class GroceriesRecyclerViewFragment extends Fragment {

    private GroceriesListRecyclerViewAdapter recyclerViewAdapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.d_fragment_grocery_items_recycler_view, container, false);
        this.context = rootView.getContext();

        RecyclerView recyclerView = rootView.findViewById(R.id.containerRecyclerViewGroceries);
        recyclerView.addItemDecoration(new RecycleViewGroceryItemsDecorator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerViewAdapter);

        return rootView;
    }

    /* Decorator */
    // Decorator to create space between recycler view items
    private class RecycleViewGroceryItemsDecorator extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._4sdp);
        }
    }
    public GroceriesListRecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public void setRecyclerViewAdapter(GroceriesListRecyclerViewAdapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
    }
}