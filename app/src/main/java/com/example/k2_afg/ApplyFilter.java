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
        setContentView(R.layout.activity_filter_selection);
        sName = (Button) findViewById(R.id.shelterNameFilter);
        sName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by name");
                Intent intent = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(intent);
            }
        });

        sVacancy = (Button) findViewById(R.id.shelterVacanciesFilter);
        sVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by vacancy");
                Intent intent = new Intent(getApplicationContext(), SearchByVacancy.class);
                startActivity(intent);
            }
        });

        sDistance = (Button) findViewById(R.id.shelterDistanceFilter);
        sDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", "click search by distance");
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });
    }
}
