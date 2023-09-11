package com.example.littleshelf.Receipts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.example.littleshelf.Main.RecyclerView.RecyclerViewFragment;
import com.example.littleshelf.R;

import java.util.ArrayList;

public class ReceiptsActivity extends AppCompatActivity {

    private ReceiptsRecyclerViewAdapter receiptsRecyclerViewAdapter;
    private RecyclerViewFragment recyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_activity_receipts);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create recycler view fragment
        recyclerViewFragment = new RecyclerViewFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.containerRecyclerViewReceipts, recyclerViewFragment)
                .commit(); // Replacing container

        // Set main recycler view properties
        receiptsRecyclerViewAdapter = new ReceiptsRecyclerViewAdapter();
        recyclerViewFragment.setRecyclerViewAdapter(receiptsRecyclerViewAdapter);

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
