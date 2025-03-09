package com.example.golf_malaga.AllCampsGolf;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golf_malaga.CampDetails;
import com.example.golf_malaga.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
public class CampsGolfList_Adapter extends RecyclerView.Adapter<CampsGolfList_Adapter.ViewHolder> {
    Context context;
    ArrayList<CampsGolfList_Model> arrayList;

    public CampsGolfList_Adapter(Context context, ArrayList<CampsGolfList_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CampsGolfList_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View showview = LayoutInflater.from(context).inflate(R.layout.camp_advance_list_layout, null, true);
        return new CampsGolfList_Adapter.ViewHolder(showview);
    }

    @Override
    public void onBindViewHolder(@NonNull CampsGolfList_Adapter.ViewHolder holder, int position) {

        CampsGolfList_Model model = arrayList.get(position);

        holder.city.setText(model.camps);
        holder.state.setText(model.towns);
        holder.picture.setImageResource(model.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CampDetails.class);

                intent.putExtra("putextra_city", model.camps);
                intent.putExtra("putextra_state", model.towns);
                intent.putExtra("putextra_picture", model.image);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static final class ViewHolder extends RecyclerView.ViewHolder{

        TextView city, state;
        ShapeableImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.campgolf_name);
            state = itemView.findViewById(R.id.campgolf_town);
            picture = itemView.findViewById(R.id.campgolf_image);

        }
    }


}
