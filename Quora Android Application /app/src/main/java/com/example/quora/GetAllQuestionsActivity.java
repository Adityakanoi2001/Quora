package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.quora.adapter.QuestionAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllQuestionsActivity extends AppCompatActivity implements QuestionAdapter.OnClickInterface{


    ApiInterface apiInterface;
    List<QuestionEntityListItem> questionEntityListItems;
    QuestionAdapter questionAdapter;
    RecyclerView getallquestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_questions);
        getallquestions=findViewById(R.id.get_all_question_recycler_view);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        apiInterface.getAllQuestion(ApplicationClass.token,ApplicationClass.userId).enqueue(new Callback<List<QuestionEntityListItem>>() {
            @Override
            public void onResponse(Call<List<QuestionEntityListItem>> call, Response<List<QuestionEntityListItem>> response) {
                questionEntityListItems=response.body();
                getallquestions.setAdapter(new QuestionAdapter(questionEntityListItems,GetAllQuestionsActivity.this));
            }

            @Override
            public void onFailure(Call<List<QuestionEntityListItem>> call, Throwable t) {
                System.out.println("noo");
            }
        });
        getallquestions.setLayoutManager(new LinearLayoutManager(getallquestions.getContext()));

    }

    @Override
    public void clickOnQuestion(QuestionEntityListItem questionEntityListItem, int position) {
        Intent intent=new Intent(GetAllQuestionsActivity.this,AnsweringQuestionActivity.class);
        intent.putExtra("qid",questionEntityListItem.getQuestionId());
        startActivity(intent);
    }
}