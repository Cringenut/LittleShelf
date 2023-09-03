package com.example.littleshelf.Main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Groceries.GroceriesActivity;
import com.example.littleshelf.HomePage.HomePage;
import com.example.littleshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class BottomNavMenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_fragment_bottom_nav_menu, container, false);

        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomNawMenu);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();
            if (id == R.id.Homepage && requireActivity().getClass() != HomePage.class) {

            }
            else if (id == R.id.Groceries && requireActivity().getClass() != GroceriesActivity.class) {

            }
            else if (id == R.id.Receipts) {

            }
            else if (id == R.id.Profile) {

            }

            return true;
        });



        return v;
    }
}