package com.example.k2_afg;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class R_Adapter extends RecyclerView.Adapter<R_Adapter.ViewHolder> {
    public String name;
    public String address;
    public Context c;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();


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
                holder.tVacancy.setText(Integer.toString(arrayList.get(position).getVacancies()));
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
            tVacancy.setText(Integer.toString(shelter.getVacancies()));
        }
    }
}