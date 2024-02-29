package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quora.adapter.CommentAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AddAnswerDto;
import com.example.quora.model.AddCommentDto;
import com.example.quora.model.CommentListItem;
import com.example.quora.model.CommentResponse;
import com.example.quora.model.QuestionInputResponse;
import com.example.quora.model.ShowComments;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity implements CommentAdapter.OnClickInterface{

    ApiInterface apiInterface;
    ShowComments showComments,showCommentsSecond;
    RecyclerView commentRecyclerView;
    CommentAdapter commentAdapter;

    String commentBody,userId;
    List<CommentListItem> commentListItemList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        userId=sharedPreferences.getString("userId",null);
        EditText comment=findViewById(R.id.et_comment);
        Button addComment=findViewById(R.id.bt_add);
        Intent intent=getIntent();
        String answerId=intent.getStringExtra("answerId");
        System.out.println(answerId);
        commentRecyclerView=findViewById(R.id.comment_recyler_view);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        apiInterface.showAllComments(ApplicationClass.token, ApplicationClass.userId, answerId).enqueue(new Callback<ShowComments>() {
            @Override
            public void onResponse(Call<ShowComments> call, Response<ShowComments> response) {
                showComments = response.body();
                commentListItemList = showComments.getCommentList();
                commentAdapter = new CommentAdapter(commentListItemList,CommentActivity.this);
                commentRecyclerView.setAdapter(commentAdapter);

            }

            @Override
            public void onFailure(Call<ShowComments> call, Throwable t) {

                System.out.println("mhfngc");
            }
        });
//        addComment.setOnClickListener(new View.OnClickListener() {
//                                             @Override
//                                          public void onClick(View v) {
//                                               commentBody = String.valueOf(comment.getText());
//                                              AddCommentDto addCommentDto = new AddCommentDto();
//                                              addCommentDto.setCommentBody(commentBody);
//                                              addCommentDto.setAnswerId(answerId);
//                                              addCommentDto.setUserId(userId);
//                                              addCommentDto.setParentId(" ");
//
//                                          }
//                                      });

        commentRecyclerView.setLayoutManager(new LinearLayoutManager(commentRecyclerView.getContext()));

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentBody=String.valueOf(comment.getText());
                AddCommentDto addCommentDto=new AddCommentDto();
                addCommentDto.setCommentBody(commentBody);
                addCommentDto.setAnswerId(answerId);
                addCommentDto.setUserId(userId);
                addCommentDto.setParentId("");
                apiInterface.addcomment(ApplicationClass.token,ApplicationClass.userId,addCommentDto).enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {

                        CommentResponse commentResponse=new CommentResponse();
                        commentResponse= response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(CommentActivity.this, "Comment added", Toast.LENGTH_SHORT).show();}
                        finish();
                        //startActivity(new Intent(CommentActivity.this,HomeActivity.class));
                    }

                    @Override
                    public void onFailure(Call<CommentResponse> call, Throwable t) {
                        System.out.println("comment not added");
                    }
                });
            }
        });
    }

    @Override
    public void onclick(CommentListItem commentListItem, int position) {
        Intent intent = new Intent(CommentActivity.this, SecondLevelComment.class);
        intent.putExtra("commentId", commentListItem.getCommentId());
        intent.putExtra("answerId", commentListItem.getAnswerId());
        startActivity(intent);
    }

}