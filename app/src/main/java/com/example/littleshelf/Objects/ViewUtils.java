package com.example.littleshelf.Objects;

import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {

    public static void disableViewGroup(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            child.setEnabled(false);
            if (child instanceof ViewGroup) {
                disableViewGroup((ViewGroup) child);
            }
        }
        viewGroup.setVisibility(View.GONE);
    }

    public static void enableViewGroup(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            child.setEnabled(true);
            if (child instanceof ViewGroup) {
                enableViewGroup((ViewGroup) child);
            }
        }
        viewGroup.setVisibility(View.VISIBLE);
    }
}

