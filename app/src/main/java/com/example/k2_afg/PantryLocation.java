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

public class PantryLocation extends AppCompatActivity{
    ArrayList<Pantry> pantries = new ArrayList<Pantry>();
    ArrayList<Pantry> names = new ArrayList<Pantry>();
    public ArrayList<Pantry> arrayList = new ArrayList<Pantry>();
    private DatabaseReference databaseReference;
    public static Context context;
    private RecyclerView rV;
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //gets location of device
        getLocation();

        super.onCreate(savedInstanceState);
        //sets view
        setContentView(R.layout.activity_pantry_location);

        context = this;
        rV = findViewById(R.id.searchPantryLocationRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
        //gets pantries from fire base
        databaseReference = FirebaseDatabase.getInstance().getReference("Pantry");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //loops through the pantries in fire base and store them in an Array List called arrayList
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name; String specifics; String vacancies; double longitude; double latitude; String description; String website; String phoneNum; String address;
                    name = item.child("name").getValue().toString();
                    address = item.child("address").getValue().toString();
                    longitude = (double) Float.valueOf(item.child("longitude").getValue().toString());
                    latitude =  (double) Float.valueOf(item.child("latitude").getValue().toString());
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = item.child("phoneNum").getValue().toString();
                    Log.v("hello", item.child("name").getValue().toString());
                    Pantry pantry = new Pantry(name, address ,  phoneNum,  website, description, latitude,  longitude);
                    arrayList.add(pantry);
                }

                //this for loop adds the items in arrayList to another arrayList called pantries. Distance between pantry and user is initialized to the actual value between them
                for(int i=0; i<arrayList.size(); i++) {
                    pantries.add(arrayList.get(i));
                    pantries.get(i).calcDistance(latitude, longitude);
                }

                //this while loop sorts the pantries in ascending order by comparing between the two distances and storing them in another arrayList called names. This process repeats
                //until pantries has no more shelters left in it
                while(pantries.size()>0) {
                    int count = 0;
                    for (int i = 0; i < pantries.size(); i++) {
                        if (Double.valueOf(pantries.get(i).getDistance()) < Double.valueOf(pantries.get(count).getDistance())) {
                            count = i;
                        }
                    }
                    names.add(pantries.get(count));
                    pantries.remove(count);
                }
                rV.setAdapter(new Pantry_LocationAdapter(getApplicationContext(), names));
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
        GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(PantryLocation.this);
        //request permission if permission not enabled
        requestPermission();
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

    /**
     * check if permission is enabled or not. If not, it will be enabled.
     */
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
