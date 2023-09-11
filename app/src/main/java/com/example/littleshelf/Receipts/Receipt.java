package com.example.littleshelf.Receipts;

import com.example.littleshelf.Main.GroceryItem.GroceryItem;

import java.util.ArrayList;

public class Receipt {

    private ArrayList<GroceryItem> receiptItems = new ArrayList<>();
        public ArrayList<GroceryItem> getReceiptItems() {
            return receiptItems;
    }
}
