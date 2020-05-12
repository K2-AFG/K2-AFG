package com.example.k2_afg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class R_Adapter extends RecyclerView.Adapter<R_Adapter.R_AdapterViewHolder> {

    public Context c;
    public ArrayList<Shelter> arrayList;
    public R_Adapter(Context c, ArrayList<Shelter> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public R_Adapter.R_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new R_AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull R_Adapter.R_AdapterViewHolder holder, int position) {
        Shelter shelter = arrayList.get(position);

        holder.t1.setText(shelter.getName());
    }

    public class R_AdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView t1;

        public R_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.listName);
        }
    }
}
