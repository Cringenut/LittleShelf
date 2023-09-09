package com.example.littleshelf.BottomNavMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Groceries.GroceriesActivity;
import com.example.littleshelf.HomePage.HomePageActivity;
import com.example.littleshelf.R;
import com.example.littleshelf.Receipts.ReceiptsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


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

            if (id == R.id.Homepage && requireActivity().getClass() != HomePageActivity.class) {
                startActivity(new Intent(v.getContext(), HomePageActivity.class));
                return true;
            }
            else if (id == R.id.Groceries && requireActivity().getClass() != GroceriesActivity.class) {
                startActivity(new Intent(v.getContext(), GroceriesActivity.class));
                return true;
            }
            else if (id == R.id.Receipts && requireActivity().getClass() != ReceiptsActivity.class) {
                startActivity(new Intent(v.getContext(), ReceiptsActivity.class));
                return true;
            }

            return true;
        });

        if (requireActivity().getClass() == HomePageActivity.class) {
            bottomNavigationView.setSelectedItemId(R.id.Homepage);
        }
        else if (requireActivity().getClass() == GroceriesActivity.class) {
            bottomNavigationView.setSelectedItemId(R.id.Groceries);
        }



        return v;
    }
}