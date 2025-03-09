package com.example.golf_malaga.Chat;

//import static com.av.avmessenger.chatwindo.reciverIImg;
//import static com.av.avmessenger.chatwindo.senderImg;

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

//import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAadpter extends RecyclerView.Adapter {
    Context context;
    ArrayList<MsgModelClass> messagesAdpterArrayList;
    int ITEM_SEND=1;
    int ITEM_RECIVE=2;

    public MessagesAadpter(Context context, ArrayList<MsgModelClass> messagesAdpterArrayList) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);
            return new senderVierwHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new reciverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MsgModelClass messages = messagesAdpterArrayList.get(position);

        if (holder.getClass()==senderVierwHolder.class){
            senderVierwHolder viewHolder = (senderVierwHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());

        }else { reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());


        }
    }

    @Override
    public int getItemCount() {
        return messagesAdpterArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MsgModelClass messages = messagesAdpterArrayList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderid())) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    class  senderVierwHolder extends RecyclerView.ViewHolder {

        TextView msgtxt;
        public senderVierwHolder(@NonNull View itemView) {
            super(itemView);

            msgtxt = itemView.findViewById(R.id.msgsendertyp);

        }
    }
    class reciverViewHolder extends RecyclerView.ViewHolder {

        TextView msgtxt;
        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);

            msgtxt = itemView.findViewById(R.id.recivertextset);
        }
    }
}