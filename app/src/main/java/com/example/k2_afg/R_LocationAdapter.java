package com.example.k2_afg;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import java.util.ArrayList;

public class R_LocationAdapter extends RecyclerView.Adapter<R_LocationAdapter.ViewHolder> {
    public String name;
    public String address;
    public Context c;

    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();
    public ArrayList<CalculatingLocation> locations = new ArrayList<CalculatingLocation>();
    int itemNum;

    public R_LocationAdapter(Context c, ArrayList<Shelter> arrayList) {
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

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(this.arrayList.get(position));
        holder.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("welcome", "onClick: clicked on");
                name = holder.t3.getText().toString();
                Log.v("welcome", name);
                Intent intent = new Intent(c, ShelterDetails.class);
                intent.putExtra("name", name);
                Log.v("welcome", "after intent " + name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                holder.t3.setText(arrayList.get(position).getName());
                holder.tLocation.setText(arrayList.get(position).getDistance());
            }
        });
    }

    public String getName() {
        Log.v("welcome", "getName method" + name);
        return name;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t3;
        private TextView tLocation;

        ViewHolder(ViewGroup container) {
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.activity_list_layout_location, container, false));
            t3 = itemView.findViewById(R.id.listShelterName);
            tLocation = itemView.findViewById(R.id.listShelterDistance);
        }

        public void bind(Shelter shelter) {
            t3.setText(shelter.getName());
            tLocation.setText(shelter.getDistance());
        }
    }
}