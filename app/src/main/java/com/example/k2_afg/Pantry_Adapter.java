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

public class Pantry_Adapter extends RecyclerView.Adapter<Pantry_Adapter.ViewHolder> {
    public String name;
    public String address;
    public Context c;
    public ArrayList<Pantry> arrayList = new ArrayList<Pantry>();
    public ArrayList<CalculatingLocation> locations = new ArrayList<CalculatingLocation>();
    int itemNum;

    public Pantry_Adapter(Context c, ArrayList<Pantry> arrayList) {
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

    public void filterList(ArrayList<Pantry> cList) {
        arrayList = cList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(this.arrayList.get(position));
        holder.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("welcome", "onClick: clicked on");
                name = holder.t2.getText().toString();
                Log.v("welcome", name);
                Intent intent = new Intent(c, ShelterDetails.class);
                intent.putExtra("name", name);
                Log.v("welcome", "after intent " + name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                holder.t2.setText(arrayList.get(position).getName());
            }
        });
    }

    public String getName() {
        Log.v("welcome", "getName method" + name);
        return name;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t2;

        ViewHolder(ViewGroup container) {
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.list_layout, container, false));
            t2 = itemView.findViewById(R.id.listName);
        }

        public void bind(Pantry pantry) {
            t2.setText(pantry.getName());
        }
    }

}