package com.example.littleshelf;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AddGroceryMenuFragment extends Fragment {
    Button nameField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_grocery_menu, container, false);

        TextView defaultFieldName;
        TextView defaultFieldValue;

        nameField = v.findViewById(R.id.btnNameField);
        defaultFieldName = nameField.findViewById(R.id.textFieldName);
        defaultFieldName.setText("Name:");
        defaultFieldValue = nameField.findViewById(R.id.textFieldValue);
        defaultFieldValue.setText("Grocery");

        return v;
    }
}