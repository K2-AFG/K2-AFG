package com.example.k2_afg;

import android.provider.ContactsContract;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SearchPage extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Shelter> shelters = new ArrayList<Shelter>();
    ArrayList<String> names = new ArrayList<String>();
    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);


        lv = (ListView) findViewById(R.id.searchpage);
        sv = (SearchView) findViewById(R.id.search);
        adapter = new ArrayAdapter(this,android.R.layout. simple_list_item_1, names);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    protected void onStart() {
        super.onStart();
        readData();
    }

    private void readData() {
        InputStream is = getResources().openRawResource(R.raw.shelters);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String value = reader.readLine();
            while (value != null) {
                String[] fields = value.split(",");
                int values1 = Integer.parseInt(fields[1]);
                Shelter nextShelter = new Shelter(fields[0], "address",
                        123, "website", "description", 1234, 4321, values1, "specifics");
                shelters.add(nextShelter);
                names.add(nextShelter.getName());
                value = reader.readLine();
            }
        } catch (IOException e) {
            Log.wtf("welcomeTag", "Error reading data on line");
        }
    }
}


