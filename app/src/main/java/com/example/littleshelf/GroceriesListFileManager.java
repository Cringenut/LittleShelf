package com.example.littleshelf;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.example.littleshelf.items.GroceryItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GroceriesListFileManager extends Application {

    // TEMPORARY CHANGE EVERYTHING LATER

    private Gson gson;
    private ArrayList<GroceryItem> groceryItemsArrayList;

    private static GroceriesListFileManager singleton;

    public GroceriesListFileManager getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        this.gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new JsonDeserializer<ArrayList>() {
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
    }

    public ArrayList<GroceryItem> getGroceryItemsArrayList() {
        return groceryItemsArrayList;
    }

    public void removeItemFromGroceryItemsArrayList(int index) {
        groceryItemsArrayList.remove(index);

        Gson gson = new Gson();
        String variableJson = gson.toJson(groceryItemsArrayList);

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.grocery_items_list); // Replace "your_file" with the actual name of your JSON file
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            inputStream.close();
            String json = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(json);
            jsonObject.put("arrayList", variableJson);

            String modifiedJson = jsonObject.toString();

            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput("your_file.json", getBaseContext().MODE_PRIVATE)); // Replace "your_file.json" with the actual name of your JSON file
            writer.write(modifiedJson);
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
