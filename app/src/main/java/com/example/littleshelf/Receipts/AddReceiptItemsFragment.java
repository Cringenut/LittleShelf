package com.example.littleshelf.Receipts;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.Main.RecyclerView.RecyclerViewFragment;
import com.example.littleshelf.R;

public class AddReceiptItemsFragment extends Fragment {

    private Receipt receipt;

    public AddReceiptItemsFragment(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.rec_fragment_add_receipt_items, container, false);

        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        getChildFragmentManager().beginTransaction()
                .replace(v.findViewById(R.id.containerRecyclerViewReceiptItems).getId(), recyclerViewFragment)
                .commit();
        recyclerViewFragment.setRecyclerViewAdapter(new AddReceiptItemRecyclerViewAdapter(receipt));

        v.findViewById(R.id.btnClose).setOnClickListener(view -> {
            ((ReceiptsActivity)requireActivity())
                    .getSupportFragmentManager().beginTransaction()
                    .remove(this)
                    .commit();

        });



        return v;
    }
}