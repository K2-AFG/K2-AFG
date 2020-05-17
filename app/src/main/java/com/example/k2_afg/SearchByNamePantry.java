package com.example.k2_afg;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class implements a way to search through all pantries in the database by name
 */
public class SearchByNamePantry extends AppCompatActivity {

    private EditText searchField;
    private RecyclerView rV;
    public ArrayList<Pantry> arrayList = new ArrayList<Pantry>();
    private DatabaseReference databaseReference;
    public Context context;
    private Pantry_Adapter pAdapter = new Pantry_Adapter(context, arrayList);
    Button addPantry;

    /**
     * describes what to do when the search page is first created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpantry);

        // sets all the class variables equal to the specific elements from the corresponding layout
        context = this;
        searchField = findViewById(R.id.search);

        //if the "For Pantries" button was clicked, then show the "Add Pantry" button and enable it
        addPantry = (Button) findViewById(R.id.addPantry);
        if(welcome.ifClickedPantry == true){
            addPantry.setVisibility(View.VISIBLE);
            addPantry.setText("Add Pantry");
            addPantry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PantryInput.class);
                    startActivity(intent);
                }
            });
        }

        //if the "For Users" button was clicked, then do not show the "Add Pantry" button
        else{
            addPantry.setVisibility(View.INVISIBLE);
        }

        /**
         * iterates through Firebase database, gets all pantries, and puts them in an ArrayList
         */
        rV = findViewById(R.id.searchRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Pantry");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name; double longitude; double latitude; String description; String website; String phoneNum; String address; String foods;
                    name = item.child("name").getValue().toString();
                    address = item.child("address").getValue().toString();
                    longitude = (double) Float.valueOf(item.child("longitude").getValue().toString());
                    latitude =  (double) Float.valueOf(item.child("latitude").getValue().toString());
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = item.child("phoneNum").getValue().toString();
                    Pantry pantry = new Pantry(name, address ,  phoneNum,  website, description, longitude,  latitude);
                    arrayList.add(pantry);
                }
                rV.setAdapter(new Pantry_Adapter(getApplicationContext(), arrayList));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        /**
         * defines what to do when the user is typing in the search bar
         */
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

    /**
     * searches all shelters based on what the user is typing and sends it to the adapter
     * @param text the String that is going to be searched for
     */
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