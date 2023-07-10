package com.example.littleshelf;

import android.app.Application;

import com.example.littleshelf.items.GroceryItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GroceriesListFileManager extends Application {

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

        InputStream inputStream = getResources().openRawResource(R.raw.grocery_items_list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String json = stringBuilder.toString();


        groceryItemsArrayList = gson.fromJson(json, ArrayList.class);
    }

    public ArrayList<GroceryItem> getGroceryItemsArrayList() {
        return groceryItemsArrayList;
    }
}
