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

public class SearchByNamePantry extends AppCompatActivity {

    private EditText searchField;
    private RecyclerView rV;
    FirebaseRecyclerOptions<Pantry> options;
    FirebaseRecyclerAdapter<Pantry, ViewHolder> adapter;
    public ArrayList<Pantry> arrayList = new ArrayList<Pantry>();
    private DatabaseReference databaseReference;
    public Context context;
    private Pantry_Adapter pAdapter = new Pantry_Adapter(context, arrayList);
    Button addPantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpantry);

        context = this;
        searchField = findViewById(R.id.search);

        addPantry = (Button) findViewById(R.id.addPantry);
        if(welcome.ifClickedPantry == true){
            Log.v("querySearch","clicked pantry");
            addPantry.setVisibility(View.VISIBLE);
            addPantry.setText("Add Pantry");
            addPantry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PantryInput.class);
                    startActivity(intent);
                }
            });
        } else{
            addPantry.setVisibility(View.INVISIBLE);
        }


        rV = findViewById(R.id.searchRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Pantry");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    Log.v("querySearch", "test");
                    DataSnapshot item = items.next();
                    String name; double longitude; double latitude; String description; String website; String phoneNum; String address; String foods;
                    name = item.child("name").getValue().toString();
                    address = item.child("address").getValue().toString();
                    longitude = ((Long) item.child("longitude").getValue()).doubleValue();
                    latitude = ((Long) item.child("latitude").getValue()).doubleValue();
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = item.child("phoneNum").getValue().toString();
                    Log.v("hello", item.child("name").getValue().toString());
                    Pantry pantry = new Pantry(name, address ,  phoneNum,  website, description, latitude,  longitude);
                    arrayList.add(pantry);
                }
                rV.setAdapter(new Pantry_Adapter(getApplicationContext(), arrayList));

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
        ArrayList<Pantry> cList = new ArrayList<>();
        searchField.setHint("Search Shelters by Name");
        searchField.setTextColor(Color.parseColor("#FF0000"));
        for (Pantry item : arrayList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                cList.add(item);
                pAdapter.filterList(cList);
            }
        } rV.setAdapter(new Pantry_Adapter(getApplicationContext(), cList));
    }
}