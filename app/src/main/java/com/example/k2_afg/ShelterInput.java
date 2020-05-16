package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShelterInput extends AppCompatActivity {
EditText ShelterName2, vacancyDescription2, EmailText, addressInput, phoneInput, SpecificText;
Button submitData;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference reference = database.getReference();
Shelter shelter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_input);

        ShelterName2 = (EditText) findViewById(R.id.ShelterName2);
        vacancyDescription2 = (EditText) findViewById(R.id.vacancyDescription2);
        EmailText = (EditText) findViewById(R.id.EmailText);
        addressInput = (EditText) findViewById(R.id.addressInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        SpecificText = (EditText) findViewById(R.id.SpecificText);
        submitData = (Button) findViewById(R.id.submitData);
        shelter1 = new Shelter();
        reference = FirebaseDatabase.getInstance().getReference().child("Shelter");
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vacancyD = vacancyDescription2.getText().toString().trim();
                String phoneN = phoneInput.getText().toString().trim();
                shelter1.setName(ShelterName2.getText().toString().trim());
                shelter1.setVacancies(vacancyD);
                shelter1.setAddress(addressInput.getText().toString().trim());
                shelter1.setPhoneNum(phoneN);
                shelter1.setSpecifications(SpecificText.getText().toString().trim());
                reference.push().setValue(shelter1);
               Toast.makeText(ShelterInput.this, "data inserted successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }

    // goes to the home page
    public void performWelcome(View v){
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }
}
