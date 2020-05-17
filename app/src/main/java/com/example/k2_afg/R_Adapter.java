package com.example.k2_afg;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import java.util.ArrayList;

/**
 * the adapter class connects the layout files with the data stored in Firebase
 */
public class R_Adapter extends RecyclerView.Adapter<R_Adapter.ViewHolder> {
    public String name;
    public String address;
    public Context c;
    public ArrayList<Shelter> arrayList;

    //constructs a new adapter
    public R_Adapter(Context c, ArrayList<Shelter> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    //gets the size of the ArrayList
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //describes what to do when the View Holder class is created
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    //gets the array list with the filtered shelters and puts them in a new array list
    public void filterList(ArrayList<Shelter> cList) {
        arrayList = cList;
        notifyDataSetChanged();
    }

    //describes what to do when a specific shelter is clicked on from the recyclerView
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(this.arrayList.get(position));
        holder.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = holder.t2.getText().toString();
                Intent intent = new Intent(c, ShelterDetails.class);
                intent.putExtra("name", name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                holder.t2.setText(arrayList.get(position).getName());
                holder.tVacancy.setText(arrayList.get(position).getVacancies());
            }
        });
    }

    //gets the name of the shelter
    public String getName() {
        return name;
    }

    /**
     * This class inflates the recycler view as objects get added
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t2;
        private TextView tVacancy;

        ViewHolder(ViewGroup container) {
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.list_layout, container, false));
            t2 = itemView.findViewById(R.id.listName);
            tVacancy = itemView.findViewById(R.id.listVacancy);
        }

        public void bind(Shelter shelter) {
            t2.setText(shelter.getName());
            tVacancy.setText(shelter.getVacancies());
        }
    }

}