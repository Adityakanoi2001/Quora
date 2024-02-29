package com.example.quora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quora.R;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AnswerEntity;

import java.util.List;

public class MoreAnswerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<AnswerEntity> answerEntityList;
    OnClickInterface onClickInterface;
    TextView tv_readmore_username, tv_readmore_answerbody, tv_readmore_vote;



    public MoreAnswerAdapter(List<AnswerEntity> answerEntityList, OnClickInterface onClickInterface) {
        this.answerEntityList = answerEntityList;
        this.onClickInterface=onClickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_answers_layout, parent, false);
        return new MoreAnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MoreAnswerViewHolder)holder).bind(answerEntityList.get(position));
    }

    @Override
    public int getItemCount() {
        return answerEntityList.size();
    }


    public class MoreAnswerViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MoreAnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;


            ImageView iv_readmore_upVote = itemView.findViewById(R.id.iv_readmore_upvote);
            ImageView iv_readmore_downvote = itemView.findViewById(R.id.iv_readmore_downvote);
            ImageView iv_readmore_comment=itemView.findViewById(R.id.iv_comment_read_more);

            iv_readmore_upVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1) {
                        onClickInterface.onUpvote(answerEntityList.get(getAdapterPosition()), getAdapterPosition());
                        if(answerEntityList.get(getAdapterPosition()).getUpVotersList().contains(ApplicationClass.userId)){
                            answerEntityList.get(getAdapterPosition()).getUpVotersList().remove(ApplicationClass.userId);
                            answerEntityList.get(getAdapterPosition()).setVote(answerEntityList.get(getAdapterPosition()).getVote()-1);
                        }
                        else {
                            answerEntityList.get(getAdapterPosition()).setVote(answerEntityList.get(getAdapterPosition()).getVote() + 1);
                            answerEntityList.get(getAdapterPosition()).getUpVotersList().add(ApplicationClass.userId);
                        }
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });
            iv_readmore_downvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1) {
                        onClickInterface.onDownvote(answerEntityList.get(getAdapterPosition()), getAdapterPosition());
                        if(answerEntityList.get(getAdapterPosition()).getDownVotersList().contains(ApplicationClass.userId)){
                            answerEntityList.get(getAdapterPosition()).getDownVotersList().remove(ApplicationClass.userId);
                            answerEntityList.get(getAdapterPosition()).setVote(answerEntityList.get(getAdapterPosition()).getVote()+1);
                        }
                        else {
                            answerEntityList.get(getAdapterPosition()).setVote(answerEntityList.get(getAdapterPosition()).getVote() - 1);
                            answerEntityList.get(getAdapterPosition()).getDownVotersList().add(ApplicationClass.userId);
                        }
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });

            iv_readmore_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                        onClickInterface.onComment(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }

        public void bind(AnswerEntity answerEntityList) {
            TextView username = itemView.findViewById(R.id.tv_moreanswer_username);
            TextView answer = itemView.findViewById(R.id.moreanswer_answerbody);
            ImageView image = itemView.findViewById(R.id.iv_readmore_profileImage);
            TextView vote = itemView.findViewById(R.id.tv_read_more_vote);
            username.setText(answerEntityList.getAnswerGiverName());
            vote.setText(String.valueOf(answerEntityList.getVote()));
            answer.setText(answerEntityList.getAnswerBody());
            Glide.with(image.getContext()).load(String.valueOf(answerEntityList.getAnswerGiverImage())).into(image);
        }
    }
    public interface OnClickInterface {

        void onUpvote(AnswerEntity answerEntity, int position);

        void onDownvote(AnswerEntity answerEntity, int position);

        void onComment(AnswerEntity answerEntity,int position);

    }
}





