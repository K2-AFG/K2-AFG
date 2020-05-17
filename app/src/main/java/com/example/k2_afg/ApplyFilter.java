package com.example.k2_afg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyFilter extends AppCompatActivity {
    Button sName, sDistance, sVacancy, pName, pDistance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets view to filter selection
        setContentView(R.layout.activity_filter_selection);
        //connects button with search shelters by name
        sName = (Button) findViewById(R.id.shelterNameFilter);
        //brings user to page when clicked
        sName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by name");
                Intent intent = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(intent);
            }
        });
        //connects button with search shelters by vacancy
        sVacancy = (Button) findViewById(R.id.shelterVacanciesFilter);
        // brings user to page when clicked
        sVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by vacancy");
                Intent intent = new Intent(getApplicationContext(), SearchByVacancy.class);
                startActivity(intent);
            }
        });

        //connects button with search shelters by distance
        sDistance = (Button) findViewById(R.id.shelterDistanceFilter);
        // brings user to page when clicked
        sDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by distance");
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        //connects button with search pantry by name
        pName = (Button) findViewById(R.id.pantryNameFilter);
        // brings user to page when clicked
        pName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by distance");
                Intent intent = new Intent(getApplicationContext(), SearchByNamePantry.class);
                startActivity(intent);
            }
        });

        //connects button with search pantry by distance
        pDistance = (Button) findViewById(R.id.pantryDistanceFilter);
        // brings user to page when clicked
        pDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by distance");
                Intent intent = new Intent(getApplicationContext(), PantryLocation.class);
                startActivity(intent);
            }
        });

    }
}
