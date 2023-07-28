package com.example.littleshelf.GroceriesList.AddGroceryItemFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.littleshelf.GroceriesList.Main.GroceriesListActivity;
import com.example.littleshelf.R;
import com.example.littleshelf.Objects.GroceryItem;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Calendar;

public class AddGroceryItemFragment extends Fragment {

    private TextInputEditText textInputItemNameField;
    private Button buttonDate;
    private GroceriesListActivity groceriesListActivity;
    private AddGroceryItemDataBaseHelper addGroceryItemDataBaseHelper;
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
    addGroceryItemDataBaseHelper = new AddGroceryItemDataBaseHelper(getContext());

    // TEST

        AddGroceryItemListViewAdapter addGroceryItemListViewAdapter =
        new AddGroceryItemListViewAdapter(this, R.layout.fragment_database_item, (ArrayList<GroceryItem>) addGroceryItemDataBaseHelper.getAllItems());
        itemOptions.setAdapter(addGroceryItemListViewAdapter);

        itemOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemToAdd = (GroceryItem) parent.getItemAtPosition(position);
                textInputItemNameField.setText(itemToAdd.getName());

                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                textInputItemNameField.clearFocus();
            }
        });

        textInputItemNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addGroceryItemListViewAdapter.getFilter().filter(s.toString());

                // Update the tag with the current sequence for the next comparison
                textInputItemNameField.setTag(s.toString());
            }
        });

    textInputItemNameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (itemToAdd != null && hasFocus) {
                itemToAdd = null;
                textInputItemNameField.setText("");
            }

            itemOptions.setVisibility(hasFocus ? View.VISIBLE : View.INVISIBLE);
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
        clearDataOnHidden(hidden);
    }

    private void clearDataOnHidden(boolean hidden) {
        // Clear the fields when fragment becomes invisible, otherwise the user can see the fields getting cleared
        if (hidden) {
            textInputItemNameField.setText("");
            buttonDate.setText("");
            itemToAdd = null;
        }
    }

    private void addNewItemToList(View v) {
        // Add item if selected
        if (itemToAdd != null) {
            itemToAdd.setExpirationDate(buttonDate.getText().toString());
            groceriesListActivity.getDataBaseHelper().addOne(itemToAdd);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.hide(AddGroceryItemFragment.this);
            fragmentTransaction.commit();

            hideKeyboard(v);
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

        int style = AlertDialog.THEME_HOLO_DARK;
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), style, dateSetListener, year, month, day);

        datePickerDialog.show();
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public String getTextInputItemNameFieldTag() {
        return textInputItemNameField.getTag().toString();
    }
}