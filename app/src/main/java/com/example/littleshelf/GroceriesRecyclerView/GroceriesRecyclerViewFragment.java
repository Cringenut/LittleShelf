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
import android.widget.Filter;
import android.widget.TextView;

import com.example.littleshelf.R;
import com.example.littleshelf.Undesigned.Objects.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceriesRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private AddItemListRecycleViewAdapter recycleViewAdapter;
    private Context context;
    private Filter filter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.d_fragment_grocery_items_recycler_view, container, false);
        this.context = rootView.getContext();

        recyclerView = rootView.findViewById(R.id.recyclerViewGroceries);
        recyclerView.addItemDecoration(new RecycleViewGroceryItemsDecorator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /* Decorator */
    // Decorator to create space between recycler view items
    private class RecycleViewGroceryItemsDecorator extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._4sdp);
        }
    }
    public AddItemListRecycleViewAdapter getRecycleViewAdapter() {
        return recycleViewAdapter;
    }

}