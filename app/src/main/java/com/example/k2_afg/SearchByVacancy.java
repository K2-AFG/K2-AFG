package com.example.k2_afg;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;
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

public class SearchByVacancy extends AppCompatActivity {

    private EditText searchField;
    private RecyclerView rV;
    FirebaseRecyclerOptions<Shelter> options;
    FirebaseRecyclerAdapter<Shelter, SheltersViewHolder> adapter;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;
    public static Context context;
    private R_Adapter rAdapter = new R_Adapter(context, arrayList);
    Button addShelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.);

        context = this;
        searchField = findViewById(R.id.search);

        addShelter = (Button) findViewById(R.id.addShelter);
        if(welcome.ifClicked == true){
            addShelter.setVisibility(View.VISIBLE);
            addShelter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("querySearch", " visible");
                    Intent intent = new Intent(getApplicationContext(), ShelterInput.class);
                    startActivity(intent);
                }
            });
        } else{
            addShelter.setVisibility(View.INVISIBLE);
        }

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
                    search(s.toString());
                } else {
                    search("");
                }
            }
        });
    }

    private void search(String text) {
        ArrayList<Shelter> cList = new ArrayList<>();
        searchField.setHint("Search Shelters by Vacancy");
        searchField.setTextColor(Color.parseColor("#FF0000"));
        for (Shelter item : arrayList) {
            if (Integer.valueOf(item.getVacancies()) <= Integer.valueOf(text) ) {
                cList.add(item);
                rAdapter.filterList(cList);
            }
        } rV.setAdapter(new R_Adapter(getApplicationContext(), cList));
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
}