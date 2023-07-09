package com.example.littleshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.littleshelf.R;
import com.example.littleshelf.items.GroceryItem;
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

public class AddGroceryItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);









        /*setContentView(R.layout.activity_add_grocery_item);

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

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText itemNameEditText = (EditText) findViewById(R.id.textInputEditText);
                if (itemNameEditText.toString().length() > 0) {
                    arrayList.add(new GroceryItem(itemNameEditText.toString()));

                    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(arrayList);
                    prefsEditor.putString("ArrayList", json);
                    prefsEditor.apply();
                }
            }
        });*/
    }
}