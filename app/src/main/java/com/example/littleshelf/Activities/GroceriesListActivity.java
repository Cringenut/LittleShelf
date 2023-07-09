package com.example.littleshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.littleshelf.GroceriesListViewAdapter;
import com.example.littleshelf.items.GroceryItem;
import com.example.littleshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class GroceriesListActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);

        loadNavMenu();

        Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new JsonDeserializer<ArrayList>() {
                    @Override
                    public ArrayList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        ArrayList list = new ArrayList();
                        JsonArray jsonArray = json.getAsJsonArray();

                        for (JsonElement element : jsonArray) {
                            // Deserialize each element and add it to the list
                            Object obj = context.deserialize(element, GroceryItem.class);
                            list.add(obj);
                        }

                        return list;
                    }
                })
                .create();
        String json = getPreferences(MODE_PRIVATE).getString("ArrayList", "");
        ArrayList<GroceryItem> arrayList = gson.fromJson(json, ArrayList.class);


        GroceriesListViewAdapter groceriesListViewAdapter = new GroceriesListViewAdapter(this, R.layout.list_item, arrayList);
        listView = getSupportFragmentManager().findFragmentById(R.id.ListView).getView().findViewById(R.id.idListView);
        listView.setAdapter(groceriesListViewAdapter);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new GroceryItem("Cheese"));
                listView.invalidateViews();

                SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(arrayList);
                prefsEditor.putString("ArrayList", json);
                prefsEditor.apply();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                listView.invalidateViews();

                SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(arrayList);
                prefsEditor.putString("ArrayList", json);
                prefsEditor.apply();
            }
        });
    }
    private void loadNavMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.GroceriesList);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.HomePage)
            {
                startActivity(new Intent(this, HomePageActivity.class));
                finish();
            }
            else if (item.getItemId() == R.id.GroceriesList)
            {
                return true;
            }
            else if (item.getItemId() == R.id.ReceiptsList)
            {
                startActivity(new Intent(this, ReceiptsListActivity.class));
                finish();
            }
            return false;
        });
    }
}