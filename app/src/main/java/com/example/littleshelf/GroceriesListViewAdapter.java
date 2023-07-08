package com.example.littleshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GroceriesListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] testList;

    GroceriesListViewAdapter(Context ctx) {
        this.context = ctx;
        this.testList = new String[]{"item-1", "item-2", "item-3"};
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return testList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
