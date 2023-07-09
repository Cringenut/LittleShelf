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

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GroceriesListFileManager extends Application {

    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

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
}
