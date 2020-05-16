package com.example.k2_afg;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GetShelters extends AppCompatActivity {
    private DatabaseReference databaseReference;
    public static ArrayList<Shelter> arrayList1 = new ArrayList<Shelter>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("Shelter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList1.clear();
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
                    arrayList1.add(shelter);
                }
                Collections.sort(arrayList1);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
}
