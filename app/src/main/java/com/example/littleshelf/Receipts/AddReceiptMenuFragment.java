package com.example.littleshelf.Receipts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;

public class AddReceiptMenuFragment extends Fragment {


    private Receipt receipt;

    private ReceiptsActivity parentActivity;


    public AddReceiptMenuFragment() {
        this.receipt = new Receipt();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.rec_fragment_add_receipt_menu, container, false);


        v.findViewById(R.id.btnAddReceiptItems).setOnClickListener(view -> {

            AddReceiptItemsFragment addReceiptItemsFragment = new AddReceiptItemsFragment(receipt);

            parentActivity.getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.containerWholeScreenFragment, addReceiptItemsFragment).
                    commit();
        });


        v.findViewById(R.id.btnAddNewReceiptConfirm).setOnClickListener(view -> {

        /* CHANGE TO DATABASE LATER */
        parentActivity.getReceiptsRecyclerViewAdapter().receipts.add(this.receipt);
        parentActivity.getReceiptsRecyclerViewAdapter().notifyDataSetChanged();

        for (GroceryItem item : receipt.getReceiptItems()) {
            //(new GroceriesDataBaseHelper(getContext())).addOne(item);
        }

        /* TEST */
        parentActivity.getSupportFragmentManager().
                beginTransaction().
                remove(this).
                commit();
        });

        return v;
    }

    public void setParentActivity(ReceiptsActivity parentActivity) {
        this.parentActivity = parentActivity;
    }
}