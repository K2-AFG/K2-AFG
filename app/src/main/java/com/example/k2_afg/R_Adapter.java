package com.example.k2_afg;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class R_Adapter extends RecyclerView.Adapter<R_Adapter.ViewHolder> {
    public String name;
    public String address;
    public Context c;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    public ArrayList<CalculatingLocation> locations = new ArrayList<CalculatingLocation>();


    public R_Adapter(Context c, ArrayList<Shelter> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public void filterList(ArrayList<Shelter> cList) {
        arrayList = cList;
        for(int i = 0; i < arrayList.size(); i ++) {
            Log.v("querySearch", "array list is " + arrayList.get(i).getName());
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(this.arrayList.get(position));
        holder.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("welcome","onClick: clicked on");
                name = holder.t2.getText().toString();
                Log.v("welcome", name);
                Intent intent = new Intent(c, ShelterDetails.class);
                intent.putExtra("name",name);
                Log.v("welcome", "after intent " + name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                holder.t2.setText(arrayList.get(position).getName());
                holder.tVacancy.setText(arrayList.get(position).getVacancies());
            }
        });
    }

    public String getName(){
        Log.v("welcome", "getName method" + name);
        return name;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t2;
        private TextView tVacancy;

        ViewHolder(ViewGroup container) {
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.list_layout, container, false));
            t2 = itemView.findViewById(R.id.listName);
            tVacancy = itemView.findViewById(R.id.listVacancy);
        }
        public void bind(Shelter shelter){
            t2.setText(shelter.getName());
            tVacancy.setText(shelter.getVacancies());
        }
    }

    class ViewLocation extends RecyclerView.ViewHolder{
        private TextView t3;
        private TextView tLocation;

        ViewLocation(ViewGroup container){
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.activity_list_layout_location, container, false));
            t3 = itemView.findViewById(R.id.listShelterName);
            tLocation = itemView.findViewById(R.id.listShelterDistance);
        }
        public void bind(Shelter shelter){
            t3.setText(shelter.getName());
            tLocation.setText()
        }
    }

    private void getLocation(){
        GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(Location.this);

        /**
         * Set GPS Location fetched address
         */
        if (mGpsLocationTracker.canGetLocation())
        {
            latitude = mGpsLocationTracker.getLatitude();
            longitude = mGpsLocationTracker.getLongitude();
            Log.v("Latitude", String.format("latitude: %s", latitude));
            Log.v("Latitude", String.format("longitude: %s", longitude));

        }
        else
        {
            mGpsLocationTracker.showSettingsAlert();
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}