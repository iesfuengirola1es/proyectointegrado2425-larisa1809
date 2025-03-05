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
import com.example.golf_malaga.AllCampsGolf.CampsGolfList_Model;
public class CampsAdvance_ADAPTER extends RecyclerView.Adapter<CampsAdvance_ADAPTER.ViewHolder> {

    Context mycontext;
    ArrayList<CampsAdvance_MODEL> model;

    public CampsAdvance_ADAPTER(Context mycontext, ArrayList<CampsAdvance_MODEL> model) {
        this.mycontext = mycontext;
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mycontext).inflate(R.layout.home_recyclerview_layout,null, true);
        return new ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CampsAdvance_MODEL model2 = model.get(position);

        holder.image.setImageResource(model2.image);
        holder.name.setText(model2.name);
        holder.townname.setText(model2.town);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mycontext,CampDetails.class);

                intent.putExtra("putextra_city", model2.name);
                intent.putExtra("putextra_state", model2.town);
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
        TextView name, townname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.camp_image);
            name = itemView.findViewById(R.id.camp_name);
            townname = itemView.findViewById(R.id.town_name);



        }
    }


}
