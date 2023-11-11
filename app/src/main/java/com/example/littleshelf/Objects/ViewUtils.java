package com.example.littleshelf.Objects;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

public class ViewUtils {

    public static void disableChildren(ViewBinding binding) {
        View rootView = binding.getRoot();
        if (rootView instanceof ViewGroup) {
            disableAllChildren((ViewGroup) rootView);
        }
    }

    private static void disableAllChildren(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            child.setEnabled(false);
            child.setClickable(false);
            child.setVisibility(View.GONE);
            if (child instanceof ViewGroup) {
                disableAllChildren((ViewGroup) child);
            }
        }
    }

    public static void enableChildren(ViewBinding binding) {
        View rootView = binding.getRoot();
        if (rootView instanceof ViewGroup) {
            enableAllChildren((ViewGroup) rootView);
        }
    }

    private static void enableAllChildren(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            child.setEnabled(true);
            child.setClickable(true);
            child.setVisibility(View.VISIBLE);
            if (child instanceof ViewGroup) {
                enableAllChildren((ViewGroup) child);
            }
        }
    }
}

