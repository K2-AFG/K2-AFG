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
        sName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("querySearch", " visible");
                Intent intent = new Intent(getApplicationContext(), ShelterInput.class);
                startActivity(intent);
            }
        });
    }
}
