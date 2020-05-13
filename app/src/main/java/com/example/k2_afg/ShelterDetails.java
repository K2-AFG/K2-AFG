package com.example.k2_afg;

import android.content.Intent;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ShelterDetails extends AppCompatActivity {
    String clickName;
    SearchPage sp = new SearchPage();
    TextView name1, address1, email1, phone1, specifics1;

    DatabaseReference reference;

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
//           show = (Button) findViewById(R.id.showData);
//
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("welcome", "test1");
//                reference = FirebaseDatabase.getInstance().getReference().child("Shelter").child("");
//                Log.v("welcome", "test2");
//                reference.addValueEventListener(new ValueEventListener() {
        reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Shelter").orderByChild("name").equalTo(clickName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                //if (dataSnapshot.exists()) {
                        Shelter shelter = dataSnapshot1.getValue(Shelter.class);
                        name1.setText(shelter.getName());
                       address1.setText(shelter.getAddress());
                        phone1.setText(Integer.toString(shelter.getPhoneNum()));
                        specifics1.setText(shelter.getSpecifications());
 //                       Log.v("welcome", "dataSnapshot " + dataSnapshot.getValue());

//                        Log.v("welcome","name "+ shelter);

                        //String name = (String) dataSnapshot.child("Shelter").child("name").getValue();
                        //Log.v("welcome", "name " + name);

//                        String address = dataSnapshot.child("address").getValue().toString();
//                        String phone = dataSnapshot.child("phoneNum").getValue().toString();
//                        String specifics = dataSnapshot.child("specifications").getValue().toString();

                        //name1.setText(name);

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//                            for(int i = 0; i<sp.arrayList.size(); i++){
//                                if(sp.arrayList.get(i).getName().equals(clickName)){
//                                    name1.setText(sp.arrayList.get(i).getName());
//                                    address1.setText(sp.arrayList.get(i).getAddress());
//                                    phone1.setText(sp.arrayList.get(i).getPhoneNum());
//                                    specifics1.setText(sp.arrayList.get(i).getSpecifications());
//                                    break;
//                                }
//                            }
                    }

//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
