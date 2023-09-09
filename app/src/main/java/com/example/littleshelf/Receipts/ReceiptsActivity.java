package com.example.littleshelf.Receipts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.littleshelf.R;

import java.util.ArrayList;

public class ReceiptsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_activity_receipts);

        RecyclerView recyclerView = findViewById(R.id.containerRecyclerViewReceipts);
        ReceiptsRecyclerViewAdapter receiptsRecyclerViewAdapter = new ReceiptsRecyclerViewAdapter();

        receiptsRecyclerViewAdapter.receipts.add(new Receipt());
        recyclerView.setAdapter(receiptsRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.btnAddNewReceipt).setOnClickListener(view -> {
            receiptsRecyclerViewAdapter.receipts.add(new Receipt());
            receiptsRecyclerViewAdapter.notifyDataSetChanged();
        });
    }
}
