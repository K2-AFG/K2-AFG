package com.example.k2_afg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    private Scanner x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

//        myRef.setValue("Hello, World!");

        Log.v("myTag", "Here");

        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("MainActivity", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("MainActivity", "Failed to read value.", error.toException());
//            }
//        });
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        InputStream is = getResources().openRawResource(R.raw.shelters);
//        String searchTerm = "Abby's House";
//        readRecord(searchTerm, is);
//    }

//    public void readRecord(String searchTerm, InputStream is) {
//        //BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        boolean found = false;
//        String line = "";
//        String name = "";
//        String address = "";
//        String number = "1";
//
//        try {
//            x = new Scanner(is);
//            x.useDelimiter("[,\n]");
//            while(x.hasNext() && !found) {
//                name = x.next();
//                address = x.next();
//                number = x.next();
//
//                if(name.equals(searchTerm)) {
//                    found = true;
//                }
//            }
//            if(found) {
//                Log.v("MainActivity", "name " + name);
//            } else {
//                Log.v("MainActivity", "error ");
//            }
//        }
//        catch(Exception e) {
//
//        }
//    }


}
