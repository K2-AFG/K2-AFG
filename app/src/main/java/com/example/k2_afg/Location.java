package com.example.k2_afg;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Location extends AppCompatActivity{

    private FusedLocationProviderClient client;
    ListView lv;
    ArrayList<Shelter> shelters = new ArrayList<Shelter>();
    ArrayList<CalculatingLocation> locations = new ArrayList<>();
    ArrayList<String> names = new ArrayList<String>();
    SearchView sv;
    ArrayAdapter adapter;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;
    public static Context context;
    private R_Adapter rAdapter = new R_Adapter(context, arrayList);
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLocation();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        lv = (ListView) findViewById(R.id.searchpagelocation);
        sv = (SearchView) findViewById(R.id.searchlocation);

        databaseReference = FirebaseDatabase.getInstance().getReference("Shelter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    //Log.v("welcome", item.getKey());
                    String name; String specifics; int vacancies; double longitude; double latitude; String description; String website; int phoneNum; String address;
                    name = item.child("name").getValue().toString();
                    specifics = item.child("specifications").getValue().toString();
                    vacancies = ((Long) item.child("vacancies").getValue()).intValue();
                    longitude = ((Long) item.child("longitude").getValue()).doubleValue();
                    latitude = ((Long) item.child("latitude").getValue()).doubleValue();
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = ((Long)item.child("phoneNum").getValue()).intValue();
                    Shelter shelter = new Shelter(name,  null,  "phoneNum",  null, null, latitude,  longitude, "vacancies",  null);
                    arrayList.add(shelter);
                }
                Collections.sort(arrayList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Log.v("hello", String.valueOf(arrayList.size()));

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

        readData();
    }

    protected void onStart() {
        super.onStart();
    }

    private void readData() {
        SearchPage sp = new SearchPage();

        for(int i=0; i<arrayList.size(); i++) {
                CalculatingLocation c = new CalculatingLocation(arrayList.get(i).getLatitude(), arrayList.get(i).getLongitude(), latitude, longitude);
                Log.v("stupid", String.valueOf(c.calcDistance()));
                shelters.add(arrayList.get(i));
                locations.add(c);
        }

        while(shelters.size()>0) {
            int count = 0;
            for (int i = 0; i < shelters.size(); i++) {
                if (locations.get(i).calcDistance() < locations.get(count).calcDistance()) {
                    count = i;
                }
            }
            names.add(shelters.get(count).getName() + " " + locations.get(count).calcDistance()+" "+"miles");
            shelters.remove(count);
            locations.remove(count);
        }
    }

    private void getLocation(){
        GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(Location.this);

        /**
         * Set GPS Location fetched address
         */
        if (mGpsLocationTracker.canGetLocation())
        {
            latitude = mGpsLocationTracker.getLatitude();
            longitude = mGpsLocationTracker.getLongitude();
            Log.v("Latitude", String.format("latitude: %s", latitude));
            Log.v("Latitude", String.format("longitude: %s", longitude));

        }
        else
        {
            mGpsLocationTracker.showSettingsAlert();
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
