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

import java.util.ArrayList;

public class R_Adapter extends RecyclerView.Adapter<R_Adapter.ViewHolder> {

    public Context c;
    public ArrayList<Shelter> arrayList = new ArrayList<Shelter>();

    public R_Adapter(Context c, ArrayList<Shelter> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }
//
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        //ViewHolder holder = new ViewHolder(v);
        //Log.v("welcome", "holder: " + holder.t2.toString());
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(this.arrayList.get(position));
        holder.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("welcome","onClick: clicked on");
                Toast.makeText(c, arrayList.get(position).getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(c, ShelterDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                holder.t2.setText(arrayList.get(position).getName());
            }
        });
    }

//    public class R_AdapterViewHolder extends ViewHolder {
//
//        public TextView t1;
//        RelativeLayout parent_layout;
//
//        public R_AdapterViewHolder(View itemView) {
//            super(itemView);
//            t1 = (TextView)itemView.findViewById(R.id.listName);
//            parent_layout = itemView.findViewById(R.id.parent_layout);
//        }
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t2;

        ViewHolder(ViewGroup container) {
            super(LayoutInflater.from(c.getApplicationContext()).inflate(R.layout.list_layout, container, false));
            t2 = itemView.findViewById(R.id.listName);
        }
        public void bind(Shelter shelter){
            t2.setText(shelter.getName());
        }
    }
}