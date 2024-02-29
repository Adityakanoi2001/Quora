package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AddAnswerDto;
import com.example.quora.model.QuestionInputResponse;
import com.example.quora.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnsweringQuestionActivity extends AppCompatActivity {

    EditText ansText;
    Button postAns;
    String qid,userId,ansBody;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_following);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        userId=sharedPreferences.getString("userId",null);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        Intent intent=getIntent();
        qid=intent.getStringExtra("qid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answering_question);
        ansText=findViewById(R.id.et_answer);
        postAns=findViewById(R.id.bt_post_answer);

        postAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansBody=String.valueOf(ansText.getText());
                AddAnswerDto addAnswerDto=new AddAnswerDto();
                addAnswerDto.setAnswerBody(ansBody);
                addAnswerDto.setAnswerUserId(userId);
                addAnswerDto.setQuestionId(qid);

                apiInterface.addAnswer(ApplicationClass.token,ApplicationClass.userId,addAnswerDto).enqueue(new Callback<QuestionInputResponse>() {
                    @Override
                    public void onResponse(Call<QuestionInputResponse> call, Response<QuestionInputResponse> response) {
                        response.body();
                        Toast.makeText(AnsweringQuestionActivity.this, "Answer added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AnsweringQuestionActivity.this,HomeActivity.class));
                    }

                    @Override
                    public void onFailure(Call<QuestionInputResponse> call, Throwable t) {
                        System.out.println("not done");
                    }
                });
            }
        });
    }
}