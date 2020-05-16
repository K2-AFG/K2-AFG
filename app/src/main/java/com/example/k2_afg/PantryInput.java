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

public class PantryInput extends AppCompatActivity {
    EditText pantryName, WebText, addressInput, phoneInput, SpecificText;
    Button submitData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    Pantry pantry1;

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
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(pantryName.getText())) {
                    Log.v("querySearch", "name is empty");
                    pantryName.setHintTextColor(Color.RED);
                    pantryName.setHint("Name of pantry is required.");
                    return;
                }
                if (isNoLetters(phoneInput.getText().toString().trim()) == false) {
                    phoneInput.setText("");
                    Log.v("querySearch", "phoneN has letters");
                    phoneInput.setHintTextColor(Color.RED);
                    phoneInput.setHint("This field cannot have letters.");
                    return;
                }
                pantry1.setName(pantryName.getText().toString().trim());
                pantry1.setWebsite(WebText.getText().toString().trim());
                pantry1.setAddress(addressInput.getText().toString().trim());
                pantry1.setPhoneNum(phoneInput.getText().toString().trim());
                pantry1.setDescription(SpecificText.getText().toString().trim());
                reference.push().setValue(pantry1);
                Toast.makeText(PantryInput.this, "data inserted successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }

    // goes to the home page
    public void performWelcome(View v){
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
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
}
