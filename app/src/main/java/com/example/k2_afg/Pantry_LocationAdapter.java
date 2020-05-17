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

public class Pantry_LocationAdapter extends RecyclerView.Adapter<Pantry_LocationAdapter.ViewHolder> {
    public String name;
    public String address;
    public Context c;
    public ArrayList<Pantry> arrayList = new ArrayList<Pantry>();
    int itemNum;

    /**
     * Initializes this adapter by taking in:
     * @param c context
     * @param arrayList ArrayList of pantries
     */
    public Pantry_LocationAdapter(Context c, ArrayList<Pantry> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    /**
     * Gets how many item in arrayList
     * @return number of things in Array List
     */
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
                name = holder.t2.getText().toString();
                Intent intent = new Intent(c, PantryDetails.class);
                intent.putExtra("name", name);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                holder.t2.setText(arrayList.get(position).getName());
                holder.tLocation.setText(arrayList.get(position).getDistance());
            }
        });
    }

    public String getName() {
        return name;
    }

    /**
     * This loops through the arrayList of pantries passed in. Then it sets t2 to the name of the pantry and tLocation to the distance of the pantry
     * It repeats until there are no shelters left.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t2;
        private TextView tLocation;

        ViewHolder(ViewGroup container) {
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.activity_list_layout_pantries_location, container, false));
            t2 = itemView.findViewById(R.id.listPantryName);
            tLocation = itemView.findViewById(R.id.listPantryDistance);
        }

        public void bind(Pantry pantry) {
            t2.setText(pantry.getName());
            tLocation.setText(pantry.getDistance());
        }
    }

}