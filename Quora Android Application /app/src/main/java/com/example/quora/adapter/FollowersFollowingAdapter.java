package com.example.quora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quora.R;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.User;
import com.example.quora.model.UserItem;

import java.util.List;

public class FollowersFollowingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<UserItem> userItems;
    OnClickInterface onClickInterface;
    public FollowersFollowingAdapter(List<UserItem> userItems,OnClickInterface onClickInterface) {
        this.userItems = userItems;
        this.onClickInterface=onClickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.followers_following_layout, parent, false);
        return new FollowerFollowingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FollowerFollowingViewHolder)holder).bind(userItems.get(position));
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    public class FollowerFollowingViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;

        public FollowerFollowingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            LinearLayout layout=itemView.findViewById(R.id.take_to_user);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.onclick(userItems.get(getAdapterPosition()),getAdapterPosition());
                    //Toast.makeText(FollowersFollowingAdapter.this, "problem", Toast.LENGTH_SHORT).show();
                    System.out.println("something");
                }
            });
//            tv_follow=itemView.findViewById(R.id.tv_follow);
//            tv_readmore=itemView.findViewById(R.id.tv_read_more);
//            tv_follow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onClickInterface.onFollowClick(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
//                }
//            });
//            tv_readmore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onClickInterface.onReadMoreClick(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
//                }
//            });
        }
        public void bind(UserItem userItem)
        {
            TextView name = itemView.findViewById(R.id.tv_username);
            TextView bio = itemView.findViewById(R.id.tv_userbio);
            ImageView iv=itemView.findViewById(R.id.image_for_user_search);
            name.setText(userItem.getUserName());
            bio.setText(userItem.getBio());
            Glide.with(iv.getContext()).load(String.valueOf(userItem.getImg())).into(iv);

        }
    }

    public interface OnClickInterface
    {
        void onclick(UserItem userItem,int position);
//        void onFollowClick(AnswerEntity answerEntity,int position);
//        void onReadMoreClick(AnswerEntity answerEntity,int position);
    }
}
