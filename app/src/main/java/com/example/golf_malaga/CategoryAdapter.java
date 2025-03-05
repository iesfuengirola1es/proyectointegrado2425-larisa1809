package com.example.golf_malaga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golf_malaga.AllCampsGolf.CampsGolfList;
import com.example.golf_malaga.AllCampsGolf.CampsGolfList_Model;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
Context context;
List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems= LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);

        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.categoryTitle.setText(categoryModelList.get(position).getTitleTown());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                CampsGolfList.showCampsByTown(categoryModelList.get(holder.getAdapterPosition()).getId());



            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryTitle=itemView.findViewById(R.id.categoryCity);
        }
    }
}
