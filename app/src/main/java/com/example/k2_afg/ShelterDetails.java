package com.example.k2_afg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * This class allows shelters to update all fields of their profile
 */
public class ShelterDetails extends AppCompatActivity {
    String clickName;
    TextView name1, address1, website1, phone1, specifics1, vacancy1;
    Button editShelter, submitChanges;
    EditText ShelterName2, vacancyDescription2, WebText, addressInput, phoneInput, SpecificText;
    DatabaseReference reference;
    Shelter shelter1;
    String key;
    boolean nameB = false; boolean vacancyB = false; boolean websiteB = false; boolean addressB = false; boolean phoneB = false; boolean specificsB = false;

    /**
     * defines what to do when this page is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        //set values for all class variables
        clickName = intent.getStringExtra("name");
        Toast.makeText(ShelterDetails.this, clickName, Toast.LENGTH_LONG).show();
        name1 = (TextView) findViewById(R.id.nameBox);
        address1 = (TextView) findViewById(R.id.addressBox);
        phone1 = (TextView) findViewById(R.id.phoneBox);
        specifics1 = findViewById(R.id.specificBox);
        website1 = findViewById(R.id.websiteBox);
        vacancy1 = findViewById(R.id.vacancyBox);
        editShelter = findViewById(R.id.editData);

        ShelterName2 = (EditText) findViewById(R.id.editName);
        ShelterName2.setVisibility(View.INVISIBLE);
        vacancyDescription2 = (EditText) findViewById(R.id.editVacancy);
        vacancyDescription2.setVisibility(View.INVISIBLE);
        WebText = (EditText) findViewById(R.id.editWebsite);
        WebText.setVisibility(View.INVISIBLE);
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

        //if the "For Shelters" button is clicked (if the shelter is the one updating)
        if(welcome.ifClicked == true){
            editShelter.setVisibility(View.VISIBLE);
            editShelter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitChanges.setVisibility(View.VISIBLE);
                    editShelter.setVisibility(View.INVISIBLE);

                    //if the operator attempts to change name
                    name1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            name1.setVisibility(View.INVISIBLE);
                            ShelterName2.setVisibility(View.VISIBLE);
                            nameB = true;
                        }
                    });

                    //if the operator attempts to change vacancy
                    vacancy1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vacancy1.setVisibility(View.INVISIBLE);
                            vacancyDescription2.setVisibility(View.VISIBLE);
                            vacancyB = true;
                        }
                    });

                    //if the operator attempts to change website
                    website1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            website1.setVisibility(View.INVISIBLE);
                            WebText.setVisibility(View.VISIBLE);
                            websiteB = true;
                        }
                    });

                    //if the operator attempts to change address
                    address1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            address1.setVisibility(View.INVISIBLE);
                            addressInput.setVisibility(View.VISIBLE);
                            addressB = true;
                        }
                    });

                    //if the operator attempts to change phone number
                    phone1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            phone1.setVisibility(View.INVISIBLE);
                            phoneInput.setVisibility(View.VISIBLE);
                            phoneB = true;
                        }
                    });

                    //if the operator attempts to change specifics
                    specifics1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            specifics1.setVisibility(View.INVISIBLE);
                            SpecificText.setVisibility(View.VISIBLE);
                            specificsB = true;
                        }
                    });

                    //if the submit changes button is clicked on
                    submitChanges.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //set the new name
                            if(nameB == true) {
                                //throw an error if the name is empty
                                if (TextUtils.isEmpty(ShelterName2.getText()) && TextUtils.isEmpty(shelter1.getName())) {
                                    Log.v("querySearch", "name is empty");
                                    ShelterName2.setHintTextColor(Color.RED);
                                    ShelterName2.setHint("Name of shelter is required.");
                                    return;
                                } HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "name", ShelterName2.getText().toString().trim());
                                reference.updateChildren(map);
                            }
                            //set new vacancy
                            if(vacancyB == true) {
                                //throw an error if the vacancy is not a number
                                if (isStringInt(vacancyDescription2.getText().toString().trim()) == false && TextUtils.isEmpty(shelter1.getVacancies())){
                                    vacancyDescription2.setText("");
                                    Log.v("querySearch", "vacancy is not numbers");
                                    vacancyDescription2.setHintTextColor(Color.RED);
                                    vacancyDescription2.setHint("Please enter a number.");
                                    return;
                                } HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "vacancies", vacancyDescription2.getText().toString().trim());
                                reference.updateChildren(map);
                            }
                            //set new website
                            if(websiteB == true) {
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "website", ShelterName2.getText().toString().trim());
                                reference.updateChildren(map);
                            }
                            //set new address
                            if(addressB == true) {
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "address", addressInput.getText().toString().trim());
                                reference.updateChildren(map);
                            }
                            //set new phone number
                            if(phoneB == true) {
                                //throw an error if phone number contains letters
                                if (isNoLetters(phoneInput.getText().toString().trim()) == false && isNoLetters(shelter1.getPhoneNum())) {
                                    phoneInput.setText("");
                                    Log.v("querySearch", "phoneN has letters");
                                    phoneInput.setHintTextColor(Color.RED);
                                    phoneInput.setHint("This field cannot have letters.");
                                    return;
                                } HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "phoneNum", phoneInput.getText().toString().trim());
                                reference.updateChildren(map);
                            }
                            //set new description
                            if(specificsB == true) {
                                HashMap map = new HashMap();
                                map.put("Shelter/" + key + "/" + "description", SpecificText.getText().toString().trim());
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

        //displays all data
        reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Shelter").orderByChild("name").equalTo(clickName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Shelter shelter = dataSnapshot1.getValue(Shelter.class);
                        key = dataSnapshot1.getKey();
                        name1.setText(shelter.getName());
                        address1.setText(shelter.getAddress());
                        phone1.setText(shelter.getPhoneNum());
                        specifics1.setText(shelter.getDescription());
                        vacancy1.setText(shelter.getVacancies());
                        website1.setText(shelter.getWebsite());
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    //checks if phone number has characters that are letters
    public boolean isNoLetters(String phoneN) {
        char[] characters = phoneN.toCharArray();
        for (char c : characters) {
            if(Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}

