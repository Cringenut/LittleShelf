package com.example.littleshelf.GroceriesList;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.littleshelf.R;
import com.example.littleshelf.Objects.GroceryItem;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddGroceryItemFragment extends Fragment {

    private TextInputEditText textInputItemNameField;
    private Button buttonDate;
    private GroceriesListActivity groceriesListActivity;
    private AddItemDataBaseHelper addItemDataBaseHelper;
    private ListView itemOptions;
    private GroceryItem itemToAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_add_grocery_item, container, false);

    // Set the default values
    groceriesListActivity = (GroceriesListActivity) getActivity();
    textInputItemNameField = v.findViewById(R.id.textInputItemNameField);
    buttonDate = v.findViewById(R.id.buttonDate);
    itemOptions = v.findViewById(R.id.itemOptions);
    addItemDataBaseHelper = new AddItemDataBaseHelper(getContext());

    // TEST
        ArrayList<GroceryItem> testList = new ArrayList<>();
        testList.add(new GroceryItem(-1, "Cheese", null));
        testList.add(new GroceryItem(-1, "Ham", null));
        testList.add(new GroceryItem(-1, "Water", null));


        DatabaseItemsListViewAdapter databaseItemsListViewAdapter =
        new DatabaseItemsListViewAdapter(getContext(), R.layout.fragment_database_item, (ArrayList<GroceryItem>) addItemDataBaseHelper.getAllItems());
        itemOptions.setAdapter(databaseItemsListViewAdapter);

        itemOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemToAdd = (GroceryItem) parent.getItemAtPosition(position);
                textInputItemNameField.setText(itemToAdd.getName());

                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                textInputItemNameField.clearFocus();
            }
        });

    // TEST

    textInputItemNameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (itemToAdd != null) {
                    itemToAdd = null;
                    textInputItemNameField.setText("");
                }

                itemOptions.setVisibility(View.VISIBLE);
            }
            else {
                itemOptions.setVisibility(View.INVISIBLE);
            }
        }
    });



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

        // Set the OnTouchListener to the parent layout
        View parentLayout = v.findViewById(R.id.parentLayout);
        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Check if the touch event is outside of the EditText
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (textInputItemNameField.isFocused()) {
                        // Clear focus from the EditText to make it lose focus
                        textInputItemNameField.clearFocus();

                        // Hide the keyboard
                        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return false; // Return false to pass the touch event to other views if needed
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
            itemToAdd = null;
        }
    }

    private void addNewItemToList(View v) {
        String addGroceryItemName = textInputItemNameField.getText().toString();

        // If name field is not empty create new item (temporary)
        if (itemToAdd != null) {
            itemToAdd.setExpirationDate(buttonDate.getText().toString());
            groceriesListActivity.getDataBaseHelper().addOne(itemToAdd);
            groceriesListActivity.getDataBaseHelper().showListViewItems();

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