package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

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
        Intent intent = new Intent(this, ShelterInput.class);
        startActivity(intent);
    }

    // goes to the search page
    public void performSearchPage(View v){
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }

}
