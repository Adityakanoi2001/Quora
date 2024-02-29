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
import com.example.quora.model.AddCommentDto;
import com.example.quora.model.CommentListItem;
import com.example.quora.model.CommentResponse;
import com.example.quora.model.ShowComments;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondLevelComment extends AppCompatActivity  implements CommentAdapter.OnClickInterface{

    ShowComments showCommentsSecond;
    CommentAdapter commentAdapter;

    RecyclerView secondLevelRecyclerView;
    ApiInterface apiInterface;
    String userId;
    String commentBody;

    List<CommentListItem>commentListItemList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level_comment);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        secondLevelRecyclerView=findViewById(R.id.second_comment_recyler_view);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        userId=sharedPreferences.getString("userId",null);
        EditText comment=findViewById(R.id.et_comment_second);
        Button addComment=findViewById(R.id.bt_add_second);
        Intent intent=getIntent();
        String answerId=intent.getStringExtra("answerId");
        Intent intent1=getIntent();
        String commentId=intent1.getStringExtra("commentId");
        apiInterface.showSecondLevelComment(ApplicationClass.token,ApplicationClass.userId,commentId).enqueue(new Callback<ShowComments>() {
            @Override
            public void onResponse(Call<ShowComments> call, Response<ShowComments> response) {
                showCommentsSecond=response.body();
                commentListItemList = showCommentsSecond.getCommentList();
                commentAdapter = new CommentAdapter(commentListItemList,SecondLevelComment.this);
                secondLevelRecyclerView.setAdapter(commentAdapter);

            }

            @Override
            public void onFailure(Call<ShowComments> call, Throwable t) {

                System.out.println("isbvakbv");
            }
        });
        secondLevelRecyclerView.setLayoutManager(new LinearLayoutManager(secondLevelRecyclerView.getContext()));

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentBody=String.valueOf(comment.getText());
                AddCommentDto addCommentDto=new AddCommentDto();
                addCommentDto.setCommentBody(commentBody);
                addCommentDto.setAnswerId(answerId);
                addCommentDto.setUserId(userId);
                addCommentDto.setParentId(commentId);
                apiInterface.addcomment(ApplicationClass.token,ApplicationClass.userId,addCommentDto).enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {

                        CommentResponse commentResponse=new CommentResponse();
                        commentResponse= response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(SecondLevelComment.this, "Comment added", Toast.LENGTH_SHORT).show();}
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
        finish();
    }
}