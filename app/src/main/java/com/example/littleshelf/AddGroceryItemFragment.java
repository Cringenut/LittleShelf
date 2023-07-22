package com.example.littleshelf;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.littleshelf.Activities.GroceriesListActivity;
import com.example.littleshelf.items.GroceryItem;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddGroceryItemFragment extends Fragment {

    private TextInputEditText textInputItemNameField;
    private Button buttonDate;
    private GroceriesListActivity groceriesListActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_add_grocery_item, container, false);

    groceriesListActivity = (GroceriesListActivity) getActivity();
    textInputItemNameField = v.findViewById(R.id.textInputItemNameField);
    buttonDate = v.findViewById(R.id.buttonDate);

    textInputItemNameField.setText("");
    buttonDate.setText("");

    v.findViewById(R.id.buttonAddItem).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addNewItemToList(v);
        }
    });

    buttonDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = dayOfMonth + " " + month + " " + year;
                    buttonDate.setText(date);
                }
            };

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_LIGHT;
            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), style, dateSetListener, year, month, day);

            datePickerDialog.show();

        }
    });
        return v;
    }

    private void addNewItemToList(View v) {
        String addGroceryItemName = textInputItemNameField.getText().toString();

        if (addGroceryItemName.length() > 0) {
            String date = buttonDate.getText().toString();
            groceriesListActivity.getDataBaseHelper().addOne(new GroceryItem(-1, addGroceryItemName, date));
            groceriesListActivity.getDataBaseHelper().showListViewItems();

            FragmentTransaction fragmentTransaction = requireFragmentManager().beginTransaction();

            // Hide the current fragment
            fragmentTransaction.hide(AddGroceryItemFragment.this);
            fragmentTransaction.commit();

            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}