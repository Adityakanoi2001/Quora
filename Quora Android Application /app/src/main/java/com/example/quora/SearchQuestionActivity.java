package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quora.adapter.FollowersFollowingAdapter;
import com.example.quora.adapter.QuestionAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.UserItem;
import com.example.quora.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchQuestionActivity extends AppCompatActivity implements QuestionAdapter.OnClickInterface{

    RecyclerView questionView;
    ApiInterface apiInterface;
    List<QuestionEntityListItem> questionEntityListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_question);
        Intent intent=getIntent();
        String searchValue = intent.getStringExtra("searchValue");
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        questionView=findViewById(R.id.searchquestions_recycler_view);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        String userId=sharedPreferences.getString("userId",null);
        apiInterface.searchQues(ApplicationClass.token,ApplicationClass.userId,searchValue).enqueue(new Callback<List<QuestionEntityListItem>>() {
            @Override
            public void onResponse(Call<List<QuestionEntityListItem>> call, Response<List<QuestionEntityListItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());
                    questionEntityListItems = response.body();
                    if(questionEntityListItems.size()==0)
                        Toast.makeText(SearchQuestionActivity.this, "No Ques", Toast.LENGTH_SHORT).show();
                    System.out.println("megana"+response.body());
                    questionView.setAdapter(new QuestionAdapter(questionEntityListItems,SearchQuestionActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<QuestionEntityListItem>> call, Throwable t) {
                Log.i("fail", "vjhbkjkjbkj");
                System.out.println("followers failed");
            }
        });
        questionView.setLayoutManager(new LinearLayoutManager(questionView.getContext()));
    }

    @Override
    public void clickOnQuestion(QuestionEntityListItem questionEntityListItem, int position) {
        Intent intent=new Intent(SearchQuestionActivity.this,ReadMoreActivity.class);
        intent.putExtra("questionId",questionEntityListItem.getQuestionId());
        intent.putExtra("questionBody",questionEntityListItem.getQuestionBody());
        startActivity(intent);
    }
}
