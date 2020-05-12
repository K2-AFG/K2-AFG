package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PantryInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_input);
    }

    // goes to the home page
    public void performWelcome(View v){
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }
}
