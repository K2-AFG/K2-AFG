package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Location extends AppCompatActivity{

    private FusedLocationProviderClient client;
    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Shelter> shelters = new ArrayList<Shelter>();
    ArrayList<String> names = new ArrayList<String>();
    SearchView sv;
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        lv = (ListView) findViewById(R.id.searchpagelocation);
        sv = (SearchView) findViewById(R.id.searchlocation);
        adapter = new ArrayAdapter(this,android.R.layout. simple_list_item_1, names);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    protected void onStart() {
        super.onStart();
        getLocation();
        readData();
    }

    private void readData() {

        InputStream is = getResources().openRawResource(R.raw.shelters);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String value = reader.readLine();
            while (value != null) {
                String[] fields = value.split(",");
                int values1 = Integer.parseInt(fields[1]);
                double value2 = Double.parseDouble(fields[2]);
                double value3 =Double.parseDouble(fields[3]);
                Shelter nextShelter = new Shelter(fields[0], "address",
                        123, "website", "description", value2, value3, values1, "specifics");
                CalculatingLocation c = new CalculatingLocation(nextShelter.getLatitude(), nextShelter.getLongitude(), latitude, longitude);
                shelters.add(nextShelter);
                names.add(nextShelter.getName()+" "+nextShelter.getLatitude()+" "+nextShelter.getLongitude()+" "+latitude+" "+longitude);
                value = reader.readLine();
            }
        } catch (IOException e) {
            Log.wtf("welcomeTag", "Error reading data on line");
        }
    }

    private void getLocation(){
        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(Location.this, ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return;
        }

        client.getLastLocation().addOnSuccessListener(Location.this, new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                if (location != null) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    Log.v("hi", String.valueOf(location.getLatitude()));
                }
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
