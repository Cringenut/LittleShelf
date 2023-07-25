package com.example.littleshelf.GroceriesList;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.littleshelf.Objects.GroceryItem;
import com.example.littleshelf.R;

import java.util.ArrayList;

public class DatabaseItemsListViewAdapter extends ArrayAdapter<GroceryItem>  {

    private Context context;
    private int resource;
    private ArrayList<GroceryItem> objects;

    public DatabaseItemsListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GroceryItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate resource fragmento
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(resource, parent, false);

        ((TextView) convertView.findViewById(R.id.itemName)).setText(objects.get(position).getName());

        return convertView;
    }
}
