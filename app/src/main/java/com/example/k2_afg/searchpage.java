package com.example.k2_afg;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class searchpage extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<String> shelters = new ArrayList<String>();
    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        lv = (ListView) findViewById(R.id.searchpage);
        sv = (SearchView) findViewById(R.id.search);
        adapter = new ArrayAdapter(this,android.R.layout. simple_list_item_1, shelters);
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
                Log.v("welcomeTag", value);
                shelters.add(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            Log.wtf("welcomeTag", "Error reading data on line");
        }
    }
}


