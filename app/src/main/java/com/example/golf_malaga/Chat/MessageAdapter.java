package com.example.golf_malaga.Chat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golf_malaga.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<MessageModel> messageModelList;
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;
    public MessageAdapter(Context context,ArrayList<MessageModel> messageModelList) {
        this.context = context;
        this.messageModelList = messageModelList;
    }


    public void add(MessageModel messageModel){

        messageModelList.add(messageModel);
        notifyDataSetChanged();
    }
    public void clear(){

        messageModelList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.message_row_sent, parent, false);
            return new senderVierwHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.message_row_received, parent, false);
            return new reciverViewHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messages = messageModelList.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete this message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

                return false;
            }
        });
        if (holder.getClass()==senderVierwHolder.class){
            senderVierwHolder viewHolder = (senderVierwHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());

        }else { reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());

        }
    }


    @Override
    public int getItemCount() {
        return messageModelList.size();
    }


    @Override
    public int getItemViewType(int position) {

        if(messageModelList.get(position).getSenderId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return VIEW_TYPE_SENT;
        }
        else
        {
            return VIEW_TYPE_RECEIVED;
        }

    }



    public class  senderVierwHolder extends RecyclerView.ViewHolder {

        TextView msgtxt;
        public senderVierwHolder(@NonNull View itemView) {
            super(itemView);

            msgtxt = itemView.findViewById(R.id.textViewSentMessage);

        }
    }
    public class reciverViewHolder extends RecyclerView.ViewHolder {

        TextView msgtxt;
        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);

            msgtxt = itemView.findViewById(R.id.textViewReceivedMessage);
        }
    }

}



