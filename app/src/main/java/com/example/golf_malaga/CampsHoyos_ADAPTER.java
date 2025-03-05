package com.example.golf_malaga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CampsHoyos_ADAPTER extends RecyclerView.Adapter<CampsHoyos_ADAPTER.ViewHolder>{

    Context mycontext;
    ArrayList<CampsHoyos_MODEL> model;

    public CampsHoyos_ADAPTER(Context mycontext, ArrayList<CampsHoyos_MODEL> model) {
        this.mycontext = mycontext;
        this.model = model;
    }

    @NonNull
    @Override
    public CampsHoyos_ADAPTER.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mycontext).inflate(R.layout.home_recyclerview_layout,null, true);
        return new CampsHoyos_ADAPTER.ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull CampsHoyos_ADAPTER.ViewHolder holder, int position) {
        CampsHoyos_MODEL model2 = model.get(position);
        holder.image.setImageResource(model2.image);
        holder.name.setText(model2.name);
        holder.state.setText(model2.city);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mycontext,CampDetails.class);

                intent.putExtra("putextra_city", model2.name);
                intent.putExtra("putextra_state", model2.city);
                intent.putExtra("putextra_picture", model2.image);


                mycontext.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return model.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.camp_image);
            name = itemView.findViewById(R.id.camp_name);
            state = itemView.findViewById(R.id.town_name);



        }
    }


}
