package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class welcome extends AppCompatActivity {
    public static boolean ifClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    // changes the page to the pantry input page
    public void performPantryInput(View v) {
        Intent intent = new Intent(this, PantryInput.class);
        startActivity(intent);
    }

    // goes to the shelter input page
    public void performShelterInput(View v) {
        Intent intent = new Intent(this, SearchPage.class);
        Log.v("querySearch", "clicked Shelter");
        ifClicked = true;
        Log.v("querySearch", "ifClicked is " + ifClicked);
        startActivity(intent);
    }

    // goes to the search page
    public void performSearchPage(View v){
        Intent intent = new Intent(this, SearchPage.class);
        Log.v("querySearch","clicked User");
        ifClicked = false;
        startActivity(intent);
    }

}
