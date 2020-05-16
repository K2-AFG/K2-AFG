package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PantryInput extends AppCompatActivity {
    EditText pantryName, EmailText, addressInput, phoneInput, SpecificText;
    Button submitData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    Pantry pantry1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_input);
        pantryName = (EditText) findViewById(R.id.pantryName);
        EmailText = (EditText) findViewById(R.id.EmailText);
        addressInput = (EditText) findViewById(R.id.addressInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        SpecificText = (EditText) findViewById(R.id.SpecificText);
        submitData = (Button) findViewById(R.id.submitData);
        pantry1 = new Pantry();
        reference = FirebaseDatabase.getInstance().getReference().child("Pantry");
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantry1.setName(pantryName.getText().toString().trim());
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
}
