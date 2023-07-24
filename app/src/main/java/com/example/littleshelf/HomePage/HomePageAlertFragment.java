package com.example.littleshelf.HomePage;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.littleshelf.GroceriesList.AddGroceryItemFragment;
import com.example.littleshelf.GroceriesList.GroceriesListActivity;
import com.example.littleshelf.Objects.GroceryItem;
import com.example.littleshelf.R;

import org.w3c.dom.Text;

public class HomePageAlertFragment extends Fragment {

    private HomePageActivity homePageActivity;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_page_alert, container, false);

        homePageActivity = (HomePageActivity) getActivity();

        int count = 0;
        assert homePageActivity != null;
        for (GroceryItem groceryItem : homePageActivity.getDataBaseHelper().getAllItems()) {
            if (!groceryItem.isFresh(null)) {
                ++count;
            }
        }

        if (count == 0) {
            // Hide the current fragment
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.hide(HomePageAlertFragment.this);
            fragmentTransaction.commit();
        }
        else if (count == 1) {
            ((TextView) v.findViewById(R.id.textAlert)).setText("You have: 1 expired item.");

            // Show the current fragment
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.show(HomePageAlertFragment.this);
            fragmentTransaction.commit();
        }
        else {
            ((TextView) v.findViewById(R.id.textAlert)).setText("You have: " + count + " expired items.");

            // Show the current fragment
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.show(HomePageAlertFragment.this);
            fragmentTransaction.commit();
        }

        return v;
    }
}