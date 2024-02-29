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
import com.example.quora.adapter.HomeAdapter;
import com.example.quora.adapter.QuestionAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AdsDto;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.Questions;
import com.example.quora.model.Vote;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllAnswersUser extends AppCompatActivity implements HomeAdapter.OnClickInterface{

    ApiInterface apiInterface;
    List<AnswerEntity> answerEntityList;
    RecyclerView answerView;
    ApiInterface apiInterfaceAds;
    List<AdsDto> adsDtoList;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_answers_user);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        apiInterfaceAds=((ApplicationClass)getApplication()).retrofit4.create(ApiInterface.class);

        apiInterfaceAds.showAds(ApplicationClass.userId).enqueue(new Callback<List<AdsDto>>() {
            @Override
            public void onResponse(Call<List<AdsDto>> call, Response<List<AdsDto>> response) {
                adsDtoList=response.body();
                //System.out.println(adsDtoList);
            }

            @Override
            public void onFailure(Call<List<AdsDto>> call, Throwable t) {

                System.out.println("problem");
            }
        });

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

        if(userId==null)
            userId=sharedPreferences.getString("userId",null);

        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        answerView=findViewById(R.id.answer_recycler_view);
        apiInterface.showAllAnswerByUserId(ApplicationClass.token,ApplicationClass.userId,userId).enqueue(new Callback<List<AnswerEntity>>() {
            @Override
            public void onResponse(Call<List<AnswerEntity>> call, Response<List<AnswerEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());

                    answerEntityList= response.body();
                    List<QuestionEntityListItem> questionEntityListItems=new ArrayList<>();
                    //QuestionEntityListItem questionEntityListItem=new QuestionEntityListItem();
                    System.out.println("megana"+response.body());
                    answerView.setAdapter(new HomeAdapter(answerEntityList,GetAllAnswersUser.this,adsDtoList));
                }
            }

            @Override
            public void onFailure(Call<List<AnswerEntity>> call, Throwable t) {
                Log.i("fail", "vjhbkjkjbkj");
                System.out.println("question failed");
            }
        });
        answerView.setLayoutManager(new LinearLayoutManager(answerView.getContext()));
    }


    @Override
    public void onFollowClick(AnswerEntity answerEntity, int position) {

    }

    @Override
    public void onReadMoreClick(AnswerEntity answerEntity, int position) {

        Intent intent=new Intent(GetAllAnswersUser.this,ReadMoreActivity.class);
        intent.putExtra("questionId",answerEntity.getQuestionId());
        intent.putExtra("questionBody",answerEntity.getQuestionBody());
        startActivity(intent);
    }

    @Override
    public void onUpvote(AnswerEntity answerEntity, int position) {
        apiInterface.upVote(ApplicationClass.token,ApplicationClass.userId,answerEntity.getAnswerID(),ApplicationClass.userId).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(Call<Vote> call, Response<Vote> response) {
                response.body();

            }

            @Override
            public void onFailure(Call<Vote> call, Throwable t) {

                System.out.println("noooooo");
            }
        });
    }

    @Override
    public void onDownvote(AnswerEntity answerEntity, int position) {

        apiInterface.downVote(ApplicationClass.token,ApplicationClass.userId,answerEntity.getAnswerID(),ApplicationClass.userId).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(Call<Vote> call, Response<Vote> response) {
                response.body();

            }

            @Override
            public void onFailure(Call<Vote> call, Throwable t) {

                System.out.println("noooooo");
            }
        });
    }

    @Override
    public void onComment(AnswerEntity answerEntity, int position) {

        Intent intent=new Intent(GetAllAnswersUser.this,CommentActivity.class);
        intent.putExtra("answerId",answerEntity.getAnswerID());
        startActivity(intent);
    }

    @Override
    public void onAnswer(AnswerEntity answerEntity, int position) {

    }

    @Override
    public void toProfile(AnswerEntity answerEntity, int position) {

    }
}
