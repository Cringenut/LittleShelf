package com.example.littleshelf.GroceriesList;

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

import com.example.littleshelf.R;
import com.example.littleshelf.Objects.GroceryItem;
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

    // Set the default values
    groceriesListActivity = (GroceriesListActivity) getActivity();
    textInputItemNameField = v.findViewById(R.id.textInputItemNameField);
    buttonDate = v.findViewById(R.id.buttonDate);

    // Button add item listener
    v.findViewById(R.id.buttonAddItem).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addNewItemToList(v);
        }
    });

    // Button date listener
    buttonDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pickDate(v);
        }
    });
        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // Clear the fields when fragment becomes invisible, otherwise the user can see the fields getting cleared
        if (hidden) {
            textInputItemNameField.setText("Cheese");
            buttonDate.setText("");
        }
    }

    private void addNewItemToList(View v) {
        String addGroceryItemName = textInputItemNameField.getText().toString();

        // If name field is not empty create new item (temporary)
        if (addGroceryItemName.length() > 0) {
            String date = buttonDate.getText().toString();
            groceriesListActivity.getDataBaseHelper().addOne(new GroceryItem(-1, addGroceryItemName, date));
            groceriesListActivity.getDataBaseHelper().showListViewItems();

            // Hide the current fragment
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.hide(AddGroceryItemFragment.this);
            fragmentTransaction.commit();

            // Hide the keyboard
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void pickDate(View v) {
        // Set button text as a date from the date picker dialog (temporary)
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Incrementing month to display the real value
                String date = dayOfMonth + " " + ++month + " " + year;
                buttonDate.setText(date);
            }
        };

        // Set today's date and show date picker dialog
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), style, dateSetListener, year, month, day);

        datePickerDialog.show();
    }
}