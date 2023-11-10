package com.example.littleshelf.ShelfGroceriesActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.littleshelf.databinding.FragmentPickGroceryNameBinding;

public class SelectGroceryNameFragment extends Fragment {
    private FragmentPickGroceryNameBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPickGroceryNameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}