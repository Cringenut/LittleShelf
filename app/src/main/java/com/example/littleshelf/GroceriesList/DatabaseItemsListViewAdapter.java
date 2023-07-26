package com.example.littleshelf.GroceriesList;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.littleshelf.Objects.GroceryItem;
import com.example.littleshelf.R;

import java.util.ArrayList;
import java.util.List;

public class DatabaseItemsListViewAdapter extends ArrayAdapter<GroceryItem>  {

    private Context context;
    private int resource;
    private ArrayList<GroceryItem> objects;
    private ArrayList<GroceryItem> filteredObjects;

    public DatabaseItemsListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GroceryItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.filteredObjects = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return itemsFilter;
    }

    public Filter itemsFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<GroceryItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(objects);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GroceryItem item : objects) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            objects.clear();
            addAll((List<GroceryItem>) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((GroceryItem) resultValue).getName();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate resource fragment
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ((TextView) convertView.findViewById(R.id.itemName)).setText(objects.get(position).getName());

        return convertView;
    }
}
