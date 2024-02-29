package com.example.quora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quora.R;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.UserItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PendingRequestsAdapter extends RecyclerView.Adapter<PendingRequestsAdapter.CustomViewHolder> {

    PendingRequestOnclick pendingRequestOnclick;
    public PendingRequestsAdapter(List<UserItem> userItemList,PendingRequestOnclick pendingRequestOnclick) {
        this.userItemList = userItemList;
        this.pendingRequestOnclick=pendingRequestOnclick;
    }

    List<UserItem> userItemList=new ArrayList<>();




    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingrequest_layout,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(userItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        View view;

        public CustomViewHolder(@NonNull View itemView) {

            super(itemView);
            view=itemView;
            Button bt_Accept=itemView.findViewById(R.id.bt_accept);
            Button bt_reject=itemView.findViewById(R.id.bt_reject);
            bt_Accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                        pendingRequestOnclick.OnAcceptClick(userItemList.get(getAdapterPosition()),getAdapterPosition());
                }
            });
            bt_reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                        pendingRequestOnclick.OnRejectClick(userItemList.get(getAdapterPosition()),getAdapterPosition());

                }
            });
        }
        void bind(UserItem userItem)
        {
            ImageView iv=view.findViewById(R.id.pendingrequest_userimage);
            TextView tv_username=view.findViewById(R.id.tv_pendingrequest_username);
            Glide.with(iv.getContext()).load(String.valueOf(userItem.getImg())).into(iv);
            tv_username.setText(userItem.getUserName());
        }
    }

    public interface PendingRequestOnclick{
        void OnAcceptClick(UserItem userItem,int position);
        void OnRejectClick(UserItem userItem,int position);

    }

}
