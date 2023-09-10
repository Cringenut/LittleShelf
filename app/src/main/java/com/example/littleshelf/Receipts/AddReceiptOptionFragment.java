package com.example.littleshelf.Receipts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.littleshelf.Main.Sort.SortTypesEnum;
import com.example.littleshelf.R;

public class AddReceiptOptionFragment extends Fragment {

    private AddReceiptOptionInterface addReceiptOptionInterface;
    public void setAddReceiptOptionInterface(AddReceiptOptionInterface addReceiptOptionInterface) {
        this.addReceiptOptionInterface = addReceiptOptionInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.rec_add_receipt_option, container, false);

        // Call sort function inside interface
        v.findViewById(R.id.btnAddNew).setOnClickListener(btnUnsorted -> addReceiptOptionInterface.onAddReceiptOptionClicked(AddReceiptOptionTypesEnum.AddNewReceipt));
        v.findViewById(R.id.btnUploadPDF).setOnClickListener(btnItemNameASC -> addReceiptOptionInterface.onAddReceiptOptionClicked(AddReceiptOptionTypesEnum.UploadPDFReceipt));
        v.findViewById(R.id.btnScan).setOnClickListener(btnItemNameDESC -> addReceiptOptionInterface.onAddReceiptOptionClicked(AddReceiptOptionTypesEnum.ScanReceipt));

        return v;
    }
}
