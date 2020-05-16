package com.example.k2_afg;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Location extends AppCompatActivity{
    private FusedLocationProviderClient client;
    ListView lv;
    ArrayList<Shelter> shelters = new ArrayList<Shelter>();
    ArrayList<CalculatingLocation> locations = new ArrayList<>();
    ArrayList<Shelter> names = new ArrayList<Shelter>();
    public static ArrayList<CalculatingLocation> calc = new ArrayList<CalculatingLocation>();
    SearchView sv;
    ArrayAdapter adapter;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;
    public static Context context;
    private RecyclerView rV;
    private R_LocationAdapter rAdapter = new R_LocationAdapter(context, arrayList);
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLocation();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        context = this;
        rV = findViewById(R.id.searchLocationRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
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
                    String name; String specifics; String vacancies; double longitude; double latitude; String description; String website; String phoneNum; String address;
                    name = item.child("name").getValue().toString();
                    specifics = item.child("specifications").getValue().toString();
                    vacancies = item.child("vacancies").getValue().toString();
                    longitude = ((Long) item.child("longitude").getValue()).doubleValue();
                    latitude = ((Long) item.child("latitude").getValue()).doubleValue();
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = item.child("phoneNum").getValue().toString();
                    Log.v("hello", item.child("name").getValue().toString());
                    Shelter shelter = new Shelter(name,  null,  phoneNum,  null, null, latitude,  longitude, vacancies,  null);
                    arrayList.add(shelter);
                }
                for(int i=0; i<arrayList.size(); i++) {
                    shelters.add(arrayList.get(i));
                    shelters.get(i).calcDistance(latitude, longitude);
                }

                while(shelters.size()>0) {
                    int count = 0;
                    for (int i = 0; i < shelters.size(); i++) {
                        if (Double.valueOf(shelters.get(i).getDistance()) < Double.valueOf(shelters.get(count).getDistance())) {
                            count = i;
                        }
                    }
                    names.add(shelters.get(count));
                    shelters.remove(count);
                }

                rV.setAdapter(new R_LocationAdapter(getApplicationContext(), names));
                Log.v("querySearch", "array " + names.size());
                Collections.sort(arrayList);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    protected void onStart() {
        super.onStart();
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
