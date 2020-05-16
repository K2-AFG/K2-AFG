package com.example.k2_afg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ShelterDetails extends AppCompatActivity {
    String clickName;
    SearchPage sp = new SearchPage();
    TextView name1, address1, email1, phone1, specifics1, vacancy1;
    Button editShelter, submitChanges;
    EditText ShelterName2, vacancyDescription2, EmailText, addressInput, phoneInput, SpecificText;
    DatabaseReference reference;
    Shelter shelter1;
    String key;
    boolean nameB = false; boolean vacancyB = false; boolean emailB = false; boolean addressB = false; boolean phoneB = false; boolean specificsB = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        clickName = intent.getStringExtra("name");
        Toast.makeText(ShelterDetails.this, clickName, Toast.LENGTH_LONG).show();
        name1 = (TextView) findViewById(R.id.nameBox);
        address1 = (TextView) findViewById(R.id.addressBox);
        phone1 = (TextView) findViewById(R.id.phoneBox);
        specifics1 = findViewById(R.id.specificBox);
        email1 = findViewById(R.id.emailBox);
        vacancy1 = findViewById(R.id.vacancyBox);
        editShelter = findViewById(R.id.editData);

        ShelterName2 = (EditText) findViewById(R.id.editName);
        ShelterName2.setVisibility(View.INVISIBLE);
        vacancyDescription2 = (EditText) findViewById(R.id.editVacancy);
        vacancyDescription2.setVisibility(View.INVISIBLE);
        EmailText = (EditText) findViewById(R.id.editEmail);
        EmailText.setVisibility(View.INVISIBLE);
        addressInput = (EditText) findViewById(R.id.editAddress);
        addressInput.setVisibility(View.INVISIBLE);
        phoneInput = (EditText) findViewById(R.id.editPhone);
        phoneInput.setVisibility(View.INVISIBLE);
        SpecificText = (EditText) findViewById(R.id.editSpecifics);
        SpecificText.setVisibility(View.INVISIBLE);
        submitChanges = (Button) findViewById(R.id.submitChanges);
        submitChanges.setVisibility(View.INVISIBLE);
        shelter1 = new Shelter();
        reference = FirebaseDatabase.getInstance().getReference().child("Shelter");


        if(welcome.ifClicked == true){
            editShelter.setVisibility(View.VISIBLE);
            Log.v("querySearch", "in edit shelter !");
            editShelter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    submitChanges.setVisibility(View.VISIBLE);
                    editShelter.setVisibility(View.INVISIBLE);

                    name1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            name1.setVisibility(View.INVISIBLE);
                            ShelterName2.setVisibility(View.VISIBLE);
                            nameB = true;
                        }
                    });

                    vacancy1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vacancy1.setVisibility(View.INVISIBLE);
                            vacancyDescription2.setVisibility(View.VISIBLE);
                            vacancyB = true;
                        }
                    });

                    email1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            email1.setVisibility(View.INVISIBLE);
                            EmailText.setVisibility(View.VISIBLE);
                            emailB = true;
                        }
                    });

                    address1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            address1.setVisibility(View.INVISIBLE);
                            addressInput.setVisibility(View.VISIBLE);
                            addressB = true;
                        }
                    });

                    phone1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            phone1.setVisibility(View.INVISIBLE);
                            phoneInput.setVisibility(View.VISIBLE);
                            phoneB = true;
                        }
                    });

                    specifics1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            specifics1.setVisibility(View.INVISIBLE);
                            SpecificText.setVisibility(View.VISIBLE);
                            specificsB = true;
                        }
                    });

                    submitChanges.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(nameB == true) {
                                Log.v("querySearch", "in if" + ShelterName2.getText().toString().trim().equals(name1.getText()));
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "name", ShelterName2.getText().toString().trim());
                                reference.updateChildren(map);
                            }

                            if(vacancyB == true) {
                                Log.v("querySearch", "in if" + vacancyDescription2.getText().toString().trim().equals(vacancy1.getText()));
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "vacancies", vacancyDescription2.getText().toString().trim());
                                reference.updateChildren(map);
                            }
//
                            if(addressB == true) {
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "address", addressInput.getText().toString().trim());
                                reference.updateChildren(map);
                            }
//
                            if(phoneB == true) {
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "phoneNum", phoneInput.getText().toString().trim());
                                reference.updateChildren(map);
                            }
//
                            if(specificsB == true) {
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "specifications", SpecificText.getText().toString().trim());
                                reference.updateChildren(map);
                            }

                            Toast.makeText(ShelterDetails.this, "data inserted successfully!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } else{
            editShelter.setVisibility(View.INVISIBLE);
        }

        reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Shelter").orderByChild("name").equalTo(clickName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                        Shelter shelter = dataSnapshot1.getValue(Shelter.class);
                        key = dataSnapshot1.getKey();
                        Log.v("welcome", "shelter is " + shelter.getName());
                        name1.setText(shelter.getName());
                        address1.setText(shelter.getAddress());
                        phone1.setText(shelter.getPhoneNum());
                        specifics1.setText(shelter.getSpecifications());
                        vacancy1.setText(shelter.getVacancies());
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

