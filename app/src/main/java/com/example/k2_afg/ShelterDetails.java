package com.example.k2_afg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ShelterDetails extends AppCompatActivity {
    TextView nameBox, addressBox, emailBox, phoneBox, specificBox;
    Button button;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_details);

       nameBox = (TextView) findViewById(R.id.nameBox);
       addressBox = (TextView) findViewById(R.id.addressBox);
       phoneBox = (TextView) findViewById(R.id.phoneBox);
       specificBox = (TextView) findViewById(R.id.specificBox);
       button = (Button) findViewById(R.id.showData);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               reference = FirebaseDatabase.getInstance().getReference().child("Member").child("1");
               reference.addValueEventListener(new ValueEventListener() {

                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String address = dataSnapshot.child("address").getValue().toString();
                        String phone = dataSnapshot.child("phoneNum").getValue().toString();
                        String specifics = dataSnapshot.child("specifications").getValue().toString();

                        nameBox.setText(name);
                        addressBox.setText(address);
                        phoneBox.setText(phone);
                        specificBox.setText(specifics);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
           }
       });
    }
}
