package com.example.golf_malaga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golf_malaga.Chat.ListUsersActivity;
import com.example.golf_malaga.Chat.Chatwindo;

import java.util.ArrayList;

//import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.viewholder> {
    Context mainActivity;
    ArrayList<Users> usersArrayList;
    public UsersAdapter(ListUsersActivity mainActivity, ArrayList<Users> usersArrayList) {
        this.mainActivity=mainActivity;
        this.usersArrayList=usersArrayList;
    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.user_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        Users users = usersArrayList.get(position);
        holder.username.setText(users.userName);
        holder.userstatus.setText(users.campo);
        //Picasso.get().load(users.profilepic).into(holder.userimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, Chatwindo.class);
                intent.putExtra("nameeee",users.getUserName());

                intent.putExtra("uid",users.getUserId());
                mainActivity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public ArrayList<Users> getUserModelList()
    {
        return usersArrayList;
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView username;
        TextView userstatus;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            userstatus = itemView.findViewById(R.id.userstatus);
        }
    }
}