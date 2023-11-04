package com.example.littleshelf;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LittleShelfActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
    }
}
