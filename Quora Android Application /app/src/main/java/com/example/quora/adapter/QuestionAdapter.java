package com.example.quora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quora.R;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.Questions;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<QuestionEntityListItem> questionEntityListItems;
    OnClickInterface onClick;
    public QuestionAdapter(List<QuestionEntityListItem> questionEntityListItems,OnClickInterface onClick) {
        this.questionEntityListItems = questionEntityListItems;
        this.onClick=onClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.questions_layout, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((QuestionViewHolder)holder).bind(questionEntityListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return questionEntityListItems.size();
    }

    public  class QuestionViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()!=-1)
                        onClick.clickOnQuestion(questionEntityListItems.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
        public void bind(QuestionEntityListItem questionEntityListItem)
        {
            TextView question = itemView.findViewById(R.id.question_body);
            question.setText(questionEntityListItem.getQuestionBody());

        }
    }

    public interface OnClickInterface
    {
        public void clickOnQuestion(QuestionEntityListItem questionEntityListItem,int position);

    }
}
