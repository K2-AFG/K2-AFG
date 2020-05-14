package com.example.k2_afg;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SearchPage extends AppCompatActivity {

    private EditText searchField;
    private RecyclerView rV;
    FirebaseRecyclerOptions<Shelter> options;
    FirebaseRecyclerAdapter<Shelter, SheltersViewHolder> adapter;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;
    public static Context context;
    private R_Adapter rAdapter = new R_Adapter(context, arrayList);
    private RecyclerView.LayoutManager xLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        context = this;
        searchField = findViewById(R.id.search);
        rV = findViewById(R.id.searchRecycler);
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
                    String name; String specifics; int vacancies; double longitude; double latitude; String description; String website; int phoneNum; String address;
                    name = item.child("name").getValue().toString();
                    specifics = item.child("specifications").getValue().toString();
                    vacancies = ((Long) item.child("vacancies").getValue()).intValue();
                    longitude = ((Long) item.child("longitude").getValue()).doubleValue();
                    latitude = ((Long) item.child("latitude").getValue()).doubleValue();
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = ((Long)item.child("phoneNum").getValue()).intValue();


                    Shelter shelter = new Shelter(name,  null,  phoneNum,  null, null, latitude,  longitude, vacancies,  null);
                    arrayList.add(shelter);
                } buildRV();

                rV.setAdapter(new R_Adapter(getApplicationContext(), arrayList));
                Collections.sort(arrayList);
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        searchField = (EditText) findViewById(R.id.search);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    Log.v("welcome", "test1 " + s.toString());
                    search(s.toString());
                } else {
                    search("");
                }
            }
        });
    }

    private void search(String text) {
        ArrayList<Shelter> cList = new ArrayList<>();
        for (Shelter item : arrayList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                cList.add(item);
                Log.v("querySearch", "item is " + item.getName());
            }
        }
        rAdapter.filterList(cList);
    }


    @Override
    protected void onStart(){
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop(){
        if(adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }

    private void buildRV() {
        rV = findViewById(R.id.searchRecycler);
        rV.setHasFixedSize(true);
        xLayoutManager = new LinearLayoutManager(this);
        rAdapter = new R_Adapter(getApplicationContext(),arrayList);
        rV.setLayoutManager(xLayoutManager);
        rV.setAdapter(rAdapter);
    }
}