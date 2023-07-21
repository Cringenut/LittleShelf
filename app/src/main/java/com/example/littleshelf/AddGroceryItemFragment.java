package com.example.littleshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AddGroceryItemFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_add_grocery_item, container, false);
        ((Button) v.findViewById(R.id.buttonAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonAdd))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String addGroceryItemName = ((TextInputEditText) getSupportFragmentManager()
                                .findFragmentById(R.id.addItem).getView()
                                .findViewById(R.id.textInputField)).getText().toString();

                        if (addGroceryItemName.length() > 0) {
                            String date = ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonDate)).getText().toString();
                            dataBaseHelper.addOne(new GroceryItem(-1, addGroceryItemName, date));
                            showGroceriesItemsOnListView(dataBaseHelper);

                            ((TextInputEditText) getSupportFragmentManager()
                                    .findFragmentById(R.id.addItem).getView()
                                    .findViewById(R.id.textInputField)).setText("");

                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.hide(getSupportFragmentManager().findFragmentById(R.id.addItem));
                            fragmentTransaction.commit();

                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        }
                    }
                });

        ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonDate))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String date = dayOfMonth + " " + month + " " + year;
                                ((Button) getSupportFragmentManager().findFragmentById(R.id.addItem).getView().findViewById(R.id.buttonDate)).setText(date);
                            }
                        };

                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        int style = AlertDialog.THEME_HOLO_LIGHT;
                        DatePickerDialog datePickerDialog = new DatePickerDialog(GroceriesListActivity.this, style, dateSetListener, year, month, day);

                        datePickerDialog.show();

                    }
                });
    }*/


        return v;
    }
}