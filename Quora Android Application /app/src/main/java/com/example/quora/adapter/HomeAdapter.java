package com.example.quora.adapter;
import android.annotation.SuppressLint;
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
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AdsDto;
import com.example.quora.model.AnswerEntity;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<AnswerEntity>  answerEntityList;
    List<AdsDto> adsDtoList;
    TextView tv_follow,tv_readmore,vote;
    ImageView iv_upVote,iv_downvote,iv_answer;
    OnClickInterface onClickInterface;
    int i=0;
    int k=0;

    ImageView iv_comment;


    public HomeAdapter(List<AnswerEntity> answerEntityList,OnClickInterface onClickInterface,List<AdsDto> adsDtoList) {
        this.answerEntityList = answerEntityList;
        this.onClickInterface=onClickInterface;
        this.adsDtoList=adsDtoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.answer_layout, parent, false);
            return new HomeViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ((HomeViewHolder) holder).bind(answerEntityList.get(position),position);




    }

    @Override
    public int getItemCount() {
        return answerEntityList.size();
    }




    public class HomeViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        private ImageView ivAds;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;


            tv_readmore=itemView.findViewById(R.id.tv_read_more);
            iv_upVote=itemView.findViewById(R.id.iv_upvote);
            iv_downvote=itemView.findViewById(R.id.iv_downvote);
            iv_comment=itemView.findViewById(R.id.iv_comment);
            iv_answer=itemView.findViewById(R.id.answer_in_home);
            ivAds = itemView.findViewById(R.id.iv_ads_image);
            LinearLayout profile=itemView.findViewById(R.id.home_to_profile);
            iv_upVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(getAdapterPosition()!=-1) {
                        onClickInterface.onUpvote(answerEntityList.get(getAdapterPosition()), getAdapterPosition());

                        if(answerEntityList.get(getAdapterPosition()).getDownVotersList().contains(ApplicationClass.userId)){
                            answerEntityList.get(getAdapterPosition()).getDownVotersList().remove(ApplicationClass.userId);
                            answerEntityList.get(getAdapterPosition()).setVote(answerEntityList.get(getAdapterPosition()).getVote()+1);
                        }

                        else if(answerEntityList.get(getAdapterPosition()).getUpVotersList().contains(ApplicationClass.userId)){
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
            iv_downvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                    { onClickInterface.onDownvote(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
                        if(answerEntityList.get(getAdapterPosition()).getUpVotersList().contains(ApplicationClass.userId)){
                            answerEntityList.get(getAdapterPosition()).getUpVotersList().remove(ApplicationClass.userId);
                            answerEntityList.get(getAdapterPosition()).setVote(answerEntityList.get(getAdapterPosition()).getVote()-1);
                        }
                        else if(answerEntityList.get(getAdapterPosition()).getDownVotersList().contains(ApplicationClass.userId)){
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

            tv_readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                        onClickInterface.onReadMoreClick(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
                }
            });
            iv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                        onClickInterface.onComment(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
                }
            });

            iv_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                    {
                        onClickInterface.onAnswer(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
                    }
                }
            });

            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.toProfile(answerEntityList.get(getAdapterPosition()),getAdapterPosition());
                }
            });

        }


        public void bind(AnswerEntity answerEntityList, int position)
        {
            ivAds.setVisibility(View.GONE);
            if((getAdapterPosition()+1)%3==0){
                if (adsDtoList!=null){
                    if(adsDtoList.size()!=0){
                        ivAds.setVisibility(View.VISIBLE);
                        int i = getAdapterPosition()%(adsDtoList.size());
                        Glide.with(ivAds.getContext()).load(adsDtoList.get(i).getUrl()).into(ivAds);
                    }
                    else{
                        ivAds.setVisibility(View.GONE);
                    }
                }
                else{
                    ivAds.setVisibility(View.GONE);
                }
            }
            TextView username = itemView.findViewById(R.id.answer_giver);
            TextView question = itemView.findViewById(R.id.tv_question);
            TextView answer = itemView.findViewById(R.id.tv_answer);
            ImageView image=itemView.findViewById(R.id.iv_home_profile_image);

            vote=itemView.findViewById(R.id.vote);
            username.setText(answerEntityList.getAnswerGiverName());
            question.setText(answerEntityList.getQuestionBody());
            vote.setText(String.valueOf(answerEntityList.getVote()));
            answer.setText(answerEntityList.getAnswerBody());
            Glide.with(image.getContext()).load(String.valueOf(answerEntityList.getAnswerGiverImage())).into(image);
        }
    }






    public interface OnClickInterface
    {
        void onFollowClick(AnswerEntity answerEntity,int position);
        void onReadMoreClick(AnswerEntity answerEntity,int position);

        void onUpvote(AnswerEntity answerEntity,int position);

        void onDownvote(AnswerEntity answerEntity,int position);

        void onComment(AnswerEntity answerEntity,int position);

        void onAnswer(AnswerEntity answerEntity,int position);

        void toProfile(AnswerEntity answerEntity,int position);

    }
}
