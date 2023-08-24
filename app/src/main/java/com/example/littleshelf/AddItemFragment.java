package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class AddItemFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.d_fragment_add_item, container, false);

        FrameLayout frameLayout = v.findViewById(R.id.frameLayoutRemoveFragment);
        frameLayout.setOnClickListener(frame ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(this)
                        .commit());

        Button btnItemName = v.findViewById(R.id.btnAddItemName);
        btnItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }
}