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
import android.widget.Toast;

import com.example.quora.adapter.FollowersFollowingAdapter;
import com.example.quora.adapter.HomeAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.UserItem;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerActivity extends AppCompatActivity implements FollowersFollowingAdapter.OnClickInterface{

    ApiInterface apiInterface;
    List<UserItem> userItems = new ArrayList<>();
    FollowersFollowingAdapter followersFollowingAdapter;
    String userId;
    RecyclerView followerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);
        Intent intent = getIntent();
        userId = intent.getStringExtra("id");



        if(userId==null) {
//           loggedIn=sharedPreferences.getString("userId",null);
            userId = sharedPreferences.getString("userId", null);
        }
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        followerView=findViewById(R.id.follower_recycler_view);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        apiInterface.findFollowerByUserId(ApplicationClass.token,ApplicationClass.userId,userId).enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());
                    userItems = response.body();
                    if(userItems.size()==0)
                        Toast.makeText(FollowerActivity.this, "No Followers", Toast.LENGTH_SHORT).show();
                    System.out.println("megana"+response.body());
                    followerView.setAdapter(new FollowersFollowingAdapter(userItems,FollowerActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {
                Log.i("fail", "vjhbkjkjbkj");
                System.out.println("followers failed");
            }
        });
        followerView.setLayoutManager(new LinearLayoutManager(followerView.getContext()));
    }

    @Override
    public void onclick(UserItem userItem, int position) {
        Intent intent=new Intent(FollowerActivity.this,UserProfileActivity.class);
        intent.putExtra("id",userItem.getUserId());
        startActivity(intent);
    }
}