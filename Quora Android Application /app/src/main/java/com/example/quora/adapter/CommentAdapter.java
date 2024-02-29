package com.example.quora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quora.R;
import com.example.quora.model.CommentListItem;
import com.example.quora.model.ShowComments;
import com.example.quora.model.UserItem;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    //ShowComments showComments=new ShowComments();

    ImageView image_profile_comment;
    TextView tv_name,tv_comment;
    List<CommentListItem> commentListItemList=new ArrayList<>();
    OnClickInterface onClickInterface;

    public CommentAdapter(List<CommentListItem> commentListItemList1,OnClickInterface onClickInterface) {
        this.commentListItemList=commentListItemList1;
        this.onClickInterface=onClickInterface;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bind(commentListItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentListItemList.size();
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            LinearLayout comment=itemView.findViewById(R.id.comments);
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.onclick(commentListItemList.get(getAdapterPosition()),getAdapterPosition());
                }
            });

        }
        public void bind(CommentListItem commentListItem)
        {
            image_profile_comment=itemView.findViewById(R.id.image_of_commenter);
            tv_comment=itemView.findViewById(R.id.tv_commentbody);
            tv_name=itemView.findViewById(R.id.tv_commentername);
            Glide.with(image_profile_comment.getContext()).load(String.valueOf(commentListItem.getCommenterImage())).into(image_profile_comment);
            tv_name.setText(commentListItem.getUserName());
            tv_comment.setText(commentListItem.getCommentBody());

        }
    }

    public interface OnClickInterface
    {
        void onclick(CommentListItem commentListItem,int position);
////        void onFollowClick(AnswerEntity answerEntity,int position);
////        void onReadMoreClick(AnswerEntity answerEntity,int position);
    }
}