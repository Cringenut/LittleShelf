package com.example.littleshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.littleshelf.items.GroceryItem;

import java.util.ArrayList;

public class GroceriesListViewAdapter extends ArrayAdapter<GroceryItem> {

    private Context context;
    private int resource;
    private ArrayList<GroceryItem> objects;

    public GroceriesListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GroceryItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(resource, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.itemName);
        textView.setText(objects.get(position).getName());
        textView = (TextView) convertView.findViewById(R.id.itemDate);
        textView.setText(objects.get(position).getExpirationDate());

        return convertView;
    }
}
