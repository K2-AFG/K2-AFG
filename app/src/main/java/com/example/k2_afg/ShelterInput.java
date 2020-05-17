package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This class describes how the shelter is inputted into the database
 */
public class ShelterInput extends AppCompatActivity {
EditText ShelterName2, vacancyDescription2, WebText, addressInput, phoneInput, SpecificText;
Button submitData;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference reference = database.getReference();
Shelter shelter1;

    /**
     * sets all the class variables equal to the specific elements from the corresponding layout
     * sets all the inputted data into a new shelter object in Firebase
     * @param savedInstanceState
     */
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
            /**
             * defines what happens when a user clicks the submit data button
             * @param v the inputted View object
             */
            @Override
            public void onClick(View v) {
                //defines what to do if the vacancy is not a number
                if (isStringInt(vacancyDescription2.getText().toString().trim()) == false){
                    vacancyDescription2.setText("");
                    vacancyDescription2.setHintTextColor(Color.RED);
                    vacancyDescription2.setHint("Please enter a number.");
                    return;
                }
                //defines what to do if the name field is empty
                if (TextUtils.isEmpty(ShelterName2.getText())) {
                    ShelterName2.setHintTextColor(Color.RED);
                    ShelterName2.setHint("Name of shelter is required.");
                    return;
                }
                //defines what to do if the phone input field has letter characters
                if (isNoLetters(phoneInput.getText().toString().trim()) == false) {
                    phoneInput.setText("");
                    phoneInput.setHintTextColor(Color.RED);
                    phoneInput.setHint("This field cannot have letters.");
                    return;
                }
                //makes a new shelter in Firebase with all the inputted data
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

    /**
     * checks whether a String parameter has characters that are not numbers
     * @param vacancy the String to be parsed
     * @return whether the String parameter has characters that are not numbers
     */
    public boolean isStringInt(String vacancy) {
        try {
            Integer.parseInt(vacancy);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * checks whether a String has letter characters
     * @param phoneN the String to be parsed
     * @return whether the String has letter characters
     */
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
