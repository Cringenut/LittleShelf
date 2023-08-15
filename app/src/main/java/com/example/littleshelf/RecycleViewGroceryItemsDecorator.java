package com.example.littleshelf;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewGroceryItemsDecorator extends RecyclerView.ItemDecoration {

    private int space;

    public RecycleViewGroceryItemsDecorator(Context context) {
        this.space = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._4sdp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }

}
