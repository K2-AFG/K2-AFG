package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShelterInput extends AppCompatActivity {
EditText ShelterName2, vacancyDescription2, WebText, addressInput, phoneInput, SpecificText;
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
        WebText = (EditText) findViewById(R.id.WebsiteText);
        addressInput = (EditText) findViewById(R.id.addressInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        SpecificText = (EditText) findViewById(R.id.SpecificText);
        submitData = (Button) findViewById(R.id.submitData);
        shelter1 = new Shelter();
        reference = FirebaseDatabase.getInstance().getReference().child("Shelter");
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStringInt(vacancyDescription2.getText().toString().trim()) == false){
                    vacancyDescription2.setText("");
                    Log.v("querySearch", "vacancy is not numbers");
                    vacancyDescription2.setHintTextColor(Color.RED);
                    vacancyDescription2.setHint("Please enter a number.");
                    return;
                }
                if (TextUtils.isEmpty(ShelterName2.getText())) {
                    Log.v("querySearch", "name is empty");
                    ShelterName2.setHintTextColor(Color.RED);
                    ShelterName2.setHint("Name of shelter is required.");
                    return;
                }
                if (isNoLetters(phoneInput.getText().toString().trim()) == false || isNoLetters(shelter1.getPhoneNum())) {
                    phoneInput.setText("");
                    Log.v("querySearch", "phoneN has letters");
                    phoneInput.setHintTextColor(Color.RED);
                    phoneInput.setHint("This field cannot have letters.");
                    return;
                }
                shelter1.setName(ShelterName2.getText().toString().trim());
                shelter1.setVacancies(vacancyDescription2.getText().toString().trim());
                shelter1.setWebsite(WebText.getText().toString().trim());
                shelter1.setAddress(addressInput.getText().toString().trim());
                shelter1.setPhoneNum(phoneInput.getText().toString().trim());
                shelter1.setDescription(SpecificText.getText().toString().trim());
                reference.push().setValue(shelter1);
               Toast.makeText(ShelterInput.this, "data inserted successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isStringInt(String vacancy) {
        try {
            Integer.parseInt(vacancy);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean isNoLetters(String phoneN) {
        char[] characters = phoneN.toCharArray();
        for (char c : characters) {
            if(Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    // goes to the home page
    public void performWelcome(View v){
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }
}
