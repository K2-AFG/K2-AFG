package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class welcome extends AppCompatActivity {
    public static boolean ifClicked;
    public static boolean ifClickedPantry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    // changes the page to the pantry input page
    public void performPantryInput(View v) {
        Intent intent = new Intent(this, SearchByNamePantry.class);
        ifClicked = false;
        ifClickedPantry = true;
        startActivity(intent);
    }

    // goes to the shelter input page
    public void performShelterInput(View v) {
        Intent intent = new Intent(this, SearchPage.class);
        Log.v("querySearch", "clicked Shelter");
        ifClickedPantry = false;
        ifClicked = true;
        Log.v("querySearch", "ifClicked is " + ifClickedPantry);
        startActivity(intent);
    }

    // goes to the search page
    public void performSearchPage(View v){
        Intent intent = new Intent(this, ApplyFilter.class);
        ifClicked = false;
        ifClickedPantry = false;
        startActivity(intent);
    }
}
