package com.example.littleshelf.GroceriesList.Main;

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

import com.example.littleshelf.R;
import com.example.littleshelf.Objects.GroceryItem;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate resource fragment
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(resource, parent, false);

        // Change data in list item fragment
        ((TextView) convertView.findViewById(R.id.itemName)).setText(objects.get(position).getName());

        ((TextView) convertView.findViewById(R.id.itemDate)).setText(objects.get(position).getExpirationDate());
        if (objects.get(position).isFresh(null)) {
            ((TextView) convertView.findViewById(R.id.itemDate)).setTextColor(context.getResources().getColor(R.color.green));
        }
        else {
            ((TextView) convertView.findViewById(R.id.itemDate)).setTextColor(context.getResources().getColor(R.color.red));
        }

        return convertView;
    }
}
