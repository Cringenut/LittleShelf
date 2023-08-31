package com.example.littleshelf.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;

public class HomePage extends AppCompatActivity {

    private GroceriesDataBaseHelper groceriesDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hp_activity_home_page);

        // Creating database for current groceries
        groceriesDataBaseHelper = new GroceriesDataBaseHelper(this);

        int freshAmount = 0;
        int expiredAmount = 0;

        for (GroceryItem groceryItem : groceriesDataBaseHelper.getAllItems()) {
            if (groceryItem.isFresh()) {
                ++freshAmount;
            }
            else {
                ++expiredAmount;
            }
        }

        ((TextView)findViewById(R.id.textViewFresh)).setText(String.valueOf(freshAmount));
        ((TextView)findViewById(R.id.textViewExpired)).setText(String.valueOf(expiredAmount));

    }


}