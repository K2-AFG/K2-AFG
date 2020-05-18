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
import java.util.Collections;
import java.util.Iterator;

/**
 * this class implements a way to search through all shelters in the database by vacancy
 */
public class SearchByVacancy extends AppCompatActivity {

    private EditText searchField;
    private RecyclerView rV;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;
    public static Context context;
    private R_Adapter rAdapter = new R_Adapter(context, arrayList);
    Button addShelter;

    /**
     * describes what to do when the search page is first created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyvacancy);

        // sets all the class variables equal to the specific elements from the corresponding layout
        context = this;
        searchField = findViewById(R.id.search);
        searchField.setHint("Search Shelters by Vacancy");

        //if the "For Shelters" button was clicked, then show the "Add Shelter" button and enable it
        addShelter = (Button) findViewById(R.id.addShelter);
        if(Welcome.ifClicked == true){
            addShelter.setVisibility(View.VISIBLE);
            addShelter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.v("querySearch", " visible");
                    Intent intent = new Intent(getApplicationContext(), ShelterInput.class);
                    startActivity(intent);
                }
            });
        }

        //if the "For Users" button was clicked, then do not show the "Add Shelter" button
        else{
            addShelter.setVisibility(View.INVISIBLE);
        }

        /**
         * iterates through Firebase database, gets all shelters, and puts them in an ArrayList
         */
        rV = findViewById(R.id.searchRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Shelter");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name; String vacancies; double longitude; double latitude; String description; String website; String phoneNum; String address;
                    name = item.child("name").getValue().toString();
                    address = item.child("address").getValue().toString();
                    vacancies = item.child("vacancies").getValue().toString();
                    longitude = (double) Float.valueOf(item.child("longitude").getValue().toString());
                    latitude =  (double) Float.valueOf(item.child("latitude").getValue().toString());
                    description = item.child("description").getValue().toString();
                    website = item.child("website").getValue().toString();
                    phoneNum = item.child("phoneNum").getValue().toString();
                    //Log.v("hello", item.child("name").getValue().toString());
                    Shelter shelter = new Shelter(name,  address,  phoneNum,  website, description, latitude,  longitude, vacancies);
                    arrayList.add(shelter);
                }
                rV.setAdapter(new R_Adapter(getApplicationContext(), arrayList));
                Collections.sort(arrayList);

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
                    search("0");
                    rV.setAdapter(new R_Adapter(getApplication(), arrayList));
                }
            }
        });
    }

    /**
     * searches all shelters based on what the user is typing and sends it to the adapter
     * @param text the String that contains the number that will be compared
     */
    private void search(String text) {
        ArrayList<Shelter> cList = new ArrayList<>();
        for (Shelter item : arrayList) {
            if(isStringInt(text) == false){
                searchField.setText("");
                searchField.setHintTextColor(Color.RED);
                searchField.setHint("Please enter a number.");
            } else{
                if (Integer.valueOf(item.getVacancies()) >= Integer.valueOf(text) ) {
                    cList.add(item);
                    rAdapter.filterList(cList);
                } else {
                    rAdapter.filterList(cList);
                }
            }
        } rV.setAdapter(new R_Adapter(getApplicationContext(), cList));
    }

    //checks if vacancy has non-numerical characters
    public boolean isStringInt(String vacancy) {
        try {
            Integer.parseInt(vacancy);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}