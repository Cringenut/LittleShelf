package com.example.littleshelf.Receipts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewFragment;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.SortByGroceriesListFragment;
import com.example.littleshelf.Groceries.SearchBar.SearchBarFragment;
import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.Main.Sort.SortButtonFragment;
import com.example.littleshelf.R;

import java.util.ArrayList;

public class ReceiptsActivity extends AppCompatActivity {

    private ReceiptsRecyclerViewAdapter receiptsRecyclerViewAdapter;
    private ReceiptsRecyclerViewFragment receiptsRecyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_activity_receipts);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create recycler view fragment
        receiptsRecyclerViewFragment = new ReceiptsRecyclerViewFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.containerRecyclerViewReceipts, receiptsRecyclerViewFragment)
                .commit(); // Replacing container

        // Set main recycler view properties
        receiptsRecyclerViewAdapter = new ReceiptsRecyclerViewAdapter();
        receiptsRecyclerViewFragment.setRecyclerViewAdapter(receiptsRecyclerViewAdapter);

        findViewById(R.id.btnAddNewReceipt).setOnClickListener(view -> {
            AddReceiptOptionFragment addReceiptOptionFragment = new AddReceiptOptionFragment();

            addReceiptOptionFragment.setAddReceiptOptionInterface(addReceiptOptionTypesEnum -> {
                switch (addReceiptOptionTypesEnum) {
                    case AddNewReceipt:
                    {
                        AddReceiptMenuFragment addReceiptMenuFragment = new AddReceiptMenuFragment();
                        addReceiptMenuFragment.setParentActivity(this);
                        fragmentManager.beginTransaction()
                                .replace(R.id.containerBottomFragment, addReceiptMenuFragment)
                                .commit();
                    }
                    case UploadPDFReceipt:
                    {

                    }
                    case ScanReceipt:
                    {

                    }
                }
            });

            fragmentManager.beginTransaction()
                    .replace(R.id.containerBottomFragment, addReceiptOptionFragment)
                    .commit();
        });
    }

    /* CHANGE TO DATABASE LATER */
    public ReceiptsRecyclerViewAdapter getReceiptsRecyclerViewAdapter() {
        return receiptsRecyclerViewAdapter;
    }
}
