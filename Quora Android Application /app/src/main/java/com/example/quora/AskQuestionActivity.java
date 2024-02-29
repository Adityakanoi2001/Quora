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
import android.widget.TextView;
import android.widget.Toast;

import com.example.quora.application.ApplicationClass;
import com.example.quora.model.QuestionInput;
import com.example.quora.model.QuestionInputResponse;
import com.example.quora.model.RegistrationResponse;
import com.example.quora.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AskQuestionActivity extends AppCompatActivity {

    String  cat,quest;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        String userId=sharedPreferences.getString("userId",null);
        EditText question,category;
        Button askQuestion;
        question=findViewById(R.id.et_ask_question);
        category=findViewById(R.id.et_category);
        askQuestion=findViewById(R.id.bt_ask_question);
        askQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat=String.valueOf(category.getText());
                quest=String.valueOf(question.getText());
                QuestionInput questionInput=new QuestionInput();
                questionInput.setQuestionBody(quest);
                questionInput.setQuestionCategory(cat);
                questionInput.setQuestionUserID(userId);
                apiInterface.addQuestion(ApplicationClass.token,ApplicationClass.userId,questionInput).enqueue(new Callback<QuestionInputResponse>() {
                    @Override
                    public void onResponse(Call<QuestionInputResponse> call, Response<QuestionInputResponse> response) {
                        QuestionInputResponse questionInputResponse=new QuestionInputResponse();
                        questionInputResponse=response.body();
                        Toast.makeText(AskQuestionActivity.this, "question added successfully", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<QuestionInputResponse> call, Throwable t) {
                        Toast.makeText(AskQuestionActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}