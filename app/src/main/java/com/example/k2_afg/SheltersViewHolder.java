package com.example.k2_afg;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SheltersViewHolder extends RecyclerView.ViewHolder {
        public TextView t1;

        public SheltersViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.listName);
        }
    }
