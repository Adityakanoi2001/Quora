package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.quora.adapter.FollowersFollowingAdapter;
import com.example.quora.adapter.QuestionAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.Questions;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetAllQuestionUserActivity extends AppCompatActivity implements QuestionAdapter.OnClickInterface{

    ApiInterface apiInterface;
    List<QuestionEntityListItem> questionEntityListItems;
    QuestionAdapter questionAdapter;
    RecyclerView questionView;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_question_user);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

        if(userId==null)
            userId=sharedPreferences.getString("userId",null);

        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        questionView=findViewById(R.id.question_recycler_view);
        apiInterface.getQuestionByQuestionUserId(ApplicationClass.token,ApplicationClass.userId,userId).enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());
                    Questions questions=new Questions();
                    questions= response.body();
                    List<QuestionEntityListItem> questionEntityListItems=new ArrayList<>();
                    //QuestionEntityListItem questionEntityListItem=new QuestionEntityListItem();
                    questionEntityListItems=questions.getQuestionEntityList();
                    System.out.println("megana"+response.body());
                    questionView.setAdapter(new QuestionAdapter(questionEntityListItems,GetAllQuestionUserActivity.this));
                }
            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                Log.i("fail", "vjhbkjkjbkj");
                System.out.println("question failed");
            }
        });
        questionView.setLayoutManager(new LinearLayoutManager(questionView.getContext()));
    }


    @Override
    public void clickOnQuestion(QuestionEntityListItem questionEntityListItem, int position) {

    }
}