package com.example.k2_afg;

import android.content.Context;
import android.provider.ContactsContract;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SearchPage extends AppCompatActivity {
    private EditText searchField;

    private RecyclerView rV;
    FirebaseRecyclerOptions<Shelter> options;
    FirebaseRecyclerAdapter<Shelter, SheltersViewHolder> adapter;
    ArrayList<Shelter> arrayList = new ArrayList<Shelter>();

    private DatabaseReference databaseReference;
//    ListView lv;
//    ArrayAdapter adapter;
//    ArrayList<Shelter> shelters = new ArrayList<Shelter>();
//    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        databaseReference = FirebaseDatabase.getInstance().getReference("Shelter");
        options = new FirebaseRecyclerOptions.Builder<Shelter>()
                .setQuery(databaseReference, Shelter.class).build();
        adapter = new FirebaseRecyclerAdapter<Shelter, SheltersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SheltersViewHolder holder, int position, @NonNull Shelter model) {
                holder.t1.setText(model.getName());
            }

            @NonNull
            @Override
            public SheltersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
                return new SheltersViewHolder(view);
            }
        };
        searchField = findViewById(R.id.search);

        rV = findViewById(R.id.searchRecycler);
        rV.setHasFixedSize(true);

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
                if(!s.toString().isEmpty()){
                    Log.v("welcome", "test1 " + s.toString());
                    search(s.toString());
                } else {
                    search("");
                }

            }
        });


//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(SearchPage.this, "test", Toast.LENGTH_LONG).show();
//  //              String searchText = searchField.getQuery().toString();
//                String searchText = "Shelter1";
//                //firebaseSearch(searchText);
//            }
//        });
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rV.setLayoutManager(linearLayoutManager);
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

//    private void firebaseSearch(String searchText) {
//        Toast.makeText(SearchPage.this, "Started Search", Toast.LENGTH_LONG).show();
//        Log.v("welcome", "test1");
//        Query firebaseSearchQuery = dataReference.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
//        Log.v("welcome", "query" + firebaseSearchQuery);
//        FirebaseRecyclerAdapter<Shelter, SheltersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Shelter, SheltersViewHolder>(
//
//                Shelter.class,
//                R.layout.list_layout,
//                SheltersViewHolder.class,
//                firebaseSearchQuery
//        ) {
//            @Override
//            protected void populateViewHolder(SheltersViewHolder viewHolder, Shelter s, int position) {
//                Log.v("welcome", "test2");
//                viewHolder.setDetails(getApplicationContext(),s.getName());
//            }
//        };
//        rV.setAdapter(firebaseRecyclerAdapter);
//    }
//
//

}