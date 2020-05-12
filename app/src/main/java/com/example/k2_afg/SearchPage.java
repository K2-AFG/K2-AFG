package com.example.k2_afg;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchPage extends AppCompatActivity {

    private EditText searchField;
    private RecyclerView rV;
    FirebaseRecyclerOptions<Shelter> options;
    FirebaseRecyclerAdapter<Shelter, SheltersViewHolder> adapter;
    ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        searchField = findViewById(R.id.search);
        rV = findViewById(R.id.searchRecycler);
        rV.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Shelter");

//        for(int i = 0; i < 10; i++){
//            Shelter shelter = new Shelter();
//            shelter.setName("Shelter " + i);
//            arrayList.add(shelter);
//        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                arrayList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name;
                    String specifics = null; int vacancies = 0; double longitude = 0; double latitude = 0; String description = null; String website = null; int phoneNum = 0; String address = null;
                    name = item.child("name").getValue().toString();
                    Shelter shelter = new Shelter( name,  null,  phoneNum,  null, null, latitude,  longitude, vacancies,  specifics);
                    arrayList.add(shelter);
                } rV.setAdapter(new R_Adapter(getApplicationContext(), arrayList));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



//        options = new FirebaseRecyclerOptions.Builder<Shelter>().setQuery(databaseReference, Shelter.class).build();
//        adapter = new FirebaseRecyclerAdapter<Shelter, SheltersViewHolder>(options) {
//
//            @Override
//            protected void onBindViewHolder(@NonNull SheltersViewHolder holder, int position, @NonNull Shelter model) {
//                holder.t1.setText(model.getName());
//            }
//            @NonNull
//            @Override
//            public SheltersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
//                return new SheltersViewHolder(view);
//            }
//        }; rV.setHasFixedSize(true);

        searchField = (EditText) findViewById(R.id.search);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    Log.v("welcome", "test1 " + s.toString());
                    //search(s.toString());
                } else {
                    //search("");
                }

            }
        });
    }

    private void search(String s) {
        Query query = databaseReference.orderByChild("name")
                .startAt(s)
                .endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    arrayList.clear();
                    for(DataSnapshot dss: dataSnapshot.getChildren()){
                        final Shelter shelter = dss.getValue(Shelter.class);
                        arrayList.add(shelter);
                    }
                    R_Adapter R_Adapter = new R_Adapter(getApplicationContext(), arrayList);
                    rV.setAdapter(R_Adapter);
                    R_Adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        adapter.startListening();
        rV.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop(){
        if(adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }


}