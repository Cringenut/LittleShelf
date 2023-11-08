package com.example.littleshelf.Fragments;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ButtonBack extends androidx.appcompat.widget.AppCompatButton implements View.OnClickListener {

    public ButtonBack(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ButtonBack(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonBack(Context context) {
        super(context);
        init();
    }

    private void init(){
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ((Activity) getContext()).onBackPressed();
    }
}
