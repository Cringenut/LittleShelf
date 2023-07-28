package com.example.littleshelf;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.GroceriesList.Main.GroceriesListActivity;
import com.example.littleshelf.HomePage.HomePageActivity;
import com.example.littleshelf.ReceiptsList.ReceiptsListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_navigation_menu, container, false);

        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.ReceiptsList);

        if (getActivity().getClass() == HomePageActivity.class) {
            bottomNavigationView.setSelectedItemId(R.id.HomePage);
        }
        else if (getActivity().getClass() == GroceriesListActivity.class) {
            bottomNavigationView.setSelectedItemId(R.id.GroceriesList);
        }
        else if (getActivity().getClass() == ReceiptsListActivity.class) {
            bottomNavigationView.setSelectedItemId(R.id.ReceiptsList);
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.HomePage && getActivity().getClass() != HomePageActivity.class)
            {
                startActivity(new Intent(v.getContext(), HomePageActivity.class));
                return true;
            }
            else if (item.getItemId() == R.id.GroceriesList && getActivity().getClass() != GroceriesListActivity.class)
            {
                startActivity(new Intent(v.getContext(), GroceriesListActivity.class));
                return true;
            }
            else if (item.getItemId() == R.id.ReceiptsList && getActivity().getClass() != ReceiptsListActivity.class)
            {
                startActivity(new Intent(v.getContext(), ReceiptsListActivity.class));
                return true;
            }
            
            return false;
        });

        return v;
    }
}