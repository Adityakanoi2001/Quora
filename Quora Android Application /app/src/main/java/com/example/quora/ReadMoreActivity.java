package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quora.adapter.HomeAdapter;
import com.example.quora.adapter.MoreAnswerAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.Vote;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadMoreActivity extends AppCompatActivity implements MoreAnswerAdapter.OnClickInterface {

    ApiInterface apiInterface;
    MoreAnswerAdapter moreAnswerAdapter;
    RecyclerView rv_read_more;
    List<AnswerEntity> answerEntityList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);
        Intent intent=getIntent();
        String questionId = intent.getStringExtra("questionId");
        String questionBody = intent.getStringExtra("questionBody");
        rv_read_more=findViewById(R.id.readmore_recycler_view);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        TextView q=findViewById(R.id.read_more_question);
        q.setText(questionBody);

        apiInterface.getAllAnswer(ApplicationClass.token,ApplicationClass.userId,questionId).enqueue(new Callback<List<AnswerEntity>>() {
            @Override
            public void onResponse(Call<List<AnswerEntity>> call, Response<List<AnswerEntity>> response) {
                answerEntityList=response.body();
                moreAnswerAdapter=new MoreAnswerAdapter(answerEntityList,ReadMoreActivity.this);
                rv_read_more.setAdapter(moreAnswerAdapter);
            }

            @Override
            public void onFailure(Call<List<AnswerEntity>> call, Throwable t) {
                Toast.makeText(ReadMoreActivity.this, "readmore failed", Toast.LENGTH_SHORT).show();
            }
        });
        rv_read_more.setLayoutManager(new LinearLayoutManager( rv_read_more.getContext()));

    }


    @Override
    public void onUpvote(AnswerEntity answerEntity, int position) {
        apiInterface.upVote(ApplicationClass.token,ApplicationClass.userId,answerEntity.getAnswerID(),answerEntity.getAnswerUserId()).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(Call<Vote> call, Response<Vote> response) {
                //Toast.makeText(ReadMoreActivity.this, "working", Toast.LENGTH_SHORT).show();

                //moreAnswerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Vote> call, Throwable t) {
                Toast.makeText(ReadMoreActivity.this, "not working", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onDownvote(AnswerEntity answerEntity, int position) {
        apiInterface.downVote(ApplicationClass.token,ApplicationClass.userId,answerEntity.getAnswerID(),answerEntity.getAnswerUserId()).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(Call<Vote> call, Response<Vote> response) {
                response.body();
                System.out.println("yeee");

                //moreAnswerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Vote> call, Throwable t) {
                System.out.println("noooooo");
            }
        });
    }

    @Override
    public void onComment(AnswerEntity answerEntity, int position) {
        Intent intent=new Intent(ReadMoreActivity.this,CommentActivity.class);
        intent.putExtra("answerId",answerEntity.getAnswerID());
        startActivity(intent);
    }
}