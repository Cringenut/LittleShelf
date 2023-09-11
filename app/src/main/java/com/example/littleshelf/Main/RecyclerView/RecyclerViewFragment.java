package com.example.littleshelf.Main.RecyclerView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.littleshelf.R;

public class RecyclerViewFragment extends Fragment {
    protected RecyclerView.Adapter recyclerViewAdapter;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_groceries_recycler_view, container, false);
        this.context = rootView.getContext();

        RecyclerView recyclerView = rootView.findViewById(R.id.containerRecyclerViewReceipts);
        recyclerView.addItemDecoration(new RecyclerViewDecorator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerViewAdapter);

        return rootView;
    }

    /* Decorator */
    // Decorator to create space between recycler view items
    private class RecyclerViewDecorator extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._4sdp);
        }
    }
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public void setRecyclerViewAdapter(RecyclerView.Adapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
    }
}
