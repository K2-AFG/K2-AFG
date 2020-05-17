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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Location extends AppCompatActivity{
    ArrayList<Shelter> shelters = new ArrayList<Shelter>();
    ArrayList<Shelter> names = new ArrayList<Shelter>();
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;
    public static Context context;
    private RecyclerView rV;
    double longitude;
    double latitude;

    /**
     * This method sorts shelters so that the distances will be in ascending orders. Then it puts it into the Adapter so it can be displayed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //gets the location of the device
        getLocation();

        super.onCreate(savedInstanceState);
        //sets layout to recycler view activity location
        setContentView(R.layout.activity_location);

        context = this;
        rV = findViewById(R.id.searchLocationRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
        //get the shelters from data base
        databaseReference = FirebaseDatabase.getInstance().getReference("Shelter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Using iterator, loop through the database and constructing shelter object for each shelter stored in there and put it into an Arraylist called arrayList
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name; String specifics; String vacancies; double longitude; double latitude; String description; String website; String phoneNum; String address;
                    name = item.child("name").getValue().toString();
                    address = item.child("address").getValue().toString();
                    vacancies = item.child("vacancies").getValue().toString();
                    longitude = (double) Float.valueOf(item.child("longitude").getValue().toString());
                    latitude =  (double) Float.valueOf(item.child("latitude").getValue().toString());
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = item.child("phoneNum").getValue().toString();
                    Log.v("hello", item.child("name").getValue().toString());
                    Shelter shelter = new Shelter(name,  address,  phoneNum,  website, description, latitude,  longitude, vacancies);
                    arrayList.add(shelter);
                }
                //this for loop stores all the shelters stored in the arrayList and add them to a new arrayList shelters. During the process, the initially distance between user and
                //shelter (which is 0) is now initialized to the actual amount by getting the user's longitude and latitude.
                for(int i=0; i<arrayList.size(); i++) {
                    shelters.add(arrayList.get(i));
                    shelters.get(i).calcDistance(latitude, longitude);
                }

                //By looping through shelter, the minimum distance would be found and added to another arrayList called names. This process repeats until there are no shelters left
                //in the ArrayList shelters
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
                //After the shelters are stored in the arrayList in ascending orders, it is now thrown to the adapter.
                rV.setAdapter(new R_LocationAdapter(getApplicationContext(), names));
                Log.v("querySearch", "array " + names.size());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    protected void onStart() {
        super.onStart();
    }

    /**
     * This method gets the location of the user
     */
    private void getLocation(){
        GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(Location.this);
        //if permission for accessing location is not allowed yet, this will request permission
        requestPermission();

        /**
         * Set GPS Location fetched address
         */
        if (mGpsLocationTracker.canGetLocation()) {
            latitude = mGpsLocationTracker.getLatitude();
            longitude = mGpsLocationTracker.getLongitude();
            Log.v("Latitude", String.format("latitude: %s", latitude));
            Log.v("Latitude", String.format("longitude: %s", longitude));
        } else {
            mGpsLocationTracker.showSettingsAlert();
        }
    }

    /**
     * Asks for permission if not allowed yet
     */
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
