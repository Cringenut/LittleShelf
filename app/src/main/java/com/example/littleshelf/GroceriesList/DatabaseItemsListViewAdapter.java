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

public class DatabaseItemsListViewAdapter extends ArrayAdapter<GroceryItem> {

    private Context context;
    private int resource;
    private ArrayList<GroceryItem> objects; // Original unfiltered list
    private ArrayList<GroceryItem> allObjects; // Filtered list

    public DatabaseItemsListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GroceryItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.allObjects = new ArrayList<>(objects); // Initialize filtered list with original data
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return itemsFilter;
    }

    private final Filter itemsFilter = new Filter() {

        String currentConstraint = "";
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<GroceryItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(allObjects); // Use the original list when the constraint is empty
            } else {
                // Change to lambda later
                String filterPattern = constraint.toString().toLowerCase();
                    for (GroceryItem item : filterPattern.startsWith(currentConstraint) ? allObjects : objects) {
                        if (item.getName().toLowerCase().startsWith(filterPattern))
                            suggestions.add(item);
                    }

                currentConstraint = filterPattern;

                if (suggestions.size() == 0) {
                    // Change to lambda later
                    for (GroceryItem item : allObjects) {
                        for (String filterPatternWord : filterPattern.split(" ")) {
                            for (String itemName : item.getName().split(" ")) {
                                if (itemName.toLowerCase().startsWith(filterPatternWord)) {
                                    suggestions.add(item);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear(); // Clear the filtered list before adding new filtered items
            addAll((List<GroceryItem>) results.values); // Add the filtered items to the list
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

        GroceryItem groceryItem = objects.get(position);

        if (groceryItem != null) {
            ((TextView) convertView.findViewById(R.id.itemName)).setText(groceryItem.getName());
        }

        return convertView;
    }
}