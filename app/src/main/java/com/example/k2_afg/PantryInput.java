package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

/**
 * This class describes how the pantry is inputted into the database
 */
public class PantryInput extends AppCompatActivity {
    EditText pantryName, WebText, addressInput, phoneInput, SpecificText;
    Button submitData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    Pantry pantry1;

    /**
     * sets all the class variables equal to the specific elements from the corresponding layout
     * sets all the inputted data into a new pantry object in Firebase
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_input);
        pantryName = (EditText) findViewById(R.id.pantryName);
        WebText = (EditText) findViewById(R.id.WebsiteText);
        addressInput = (EditText) findViewById(R.id.addressInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        SpecificText = (EditText) findViewById(R.id.SpecificText);
        submitData = (Button) findViewById(R.id.submitData);
        pantry1 = new Pantry();
        reference = FirebaseDatabase.getInstance().getReference().child("Pantry");
        submitData.setOnClickListener(new View.OnClickListener() {
            /**
             * defines what happens when a user clicks the submit data button
             * @param v the inputted View object
             */
            @Override
            public void onClick(View v) {
                //defines what to do if the name field is empty
                if (TextUtils.isEmpty(pantryName.getText())) {
                    pantryName.setHintTextColor(Color.RED);
                    pantryName.setHint("Name of pantry is required.");
                    return;
                }
                //defines what to do if the phone input field has letter characters
                if (isNoLetters(phoneInput.getText().toString().trim()) == false) {
                    phoneInput.setText("");
                    phoneInput.setHintTextColor(Color.RED);
                    phoneInput.setHint("This field cannot have letters.");
                    return;
                }
                //makes a new pantry in Firebase with all the inputted data
                pantry1.setName(pantryName.getText().toString().trim());
                pantry1.setWebsite(WebText.getText().toString().trim());
                pantry1.setAddress(addressInput.getText().toString().trim());
                pantry1.setPhoneNum(phoneInput.getText().toString().trim());
                pantry1.setDescription(SpecificText.getText().toString().trim());
                reference.push().setValue(pantry1);
                Intent intent = new Intent(getApplicationContext(), SearchByNamePantry.class);
                startActivity(intent);
                Toast.makeText(PantryInput.this, "Data Inserted Successfully!", Toast.LENGTH_LONG).show();
            }
        });
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
