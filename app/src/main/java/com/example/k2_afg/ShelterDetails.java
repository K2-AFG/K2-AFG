package com.example.k2_afg;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ShelterDetails extends AppCompatActivity {
TextView name1, address1, email1, phone1, specifics1;
Button show;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_details);
       Toast.makeText(ShelterDetails.this, "success", Toast.LENGTH_LONG).show();
       name1 = (TextView) findViewById(R.id.nameBox);
       address1 = (TextView) findViewById(R.id.addressBox);
       phone1 = (TextView) findViewById(R.id.phoneBox);
       specifics1 = (TextView) findViewById(R.id.specificBox);
       show = (Button) findViewById(R.id.showData);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("welcome", "test1");
                reference = FirebaseDatabase.getInstance().getReference().child("Shelter").child("-M70RSZHMqX6CfpicTf7");
                Log.v("welcome", "test2");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name = (String) dataSnapshot.child("name").getValue();
                        Log.v("welcome", "test3");
                        Log.v("welcome", "name is" + name);
                        String address = dataSnapshot.child("address").getValue().toString();
                        String phone = dataSnapshot.child("phoneNum").getValue().toString();
                        String specifics = dataSnapshot.child("specifications").getValue().toString();
                        Log.v("welcome", "test4");
                        name1.setText(name);
                        address1.setText(address);
                        phone1.setText(phone);
                        specifics1.setText(specifics);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}