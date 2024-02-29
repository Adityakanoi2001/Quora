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
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.UserItem;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity implements FollowersFollowingAdapter.OnClickInterface {

    ApiInterface apiInterface;
    List<UserItem> userItems = new ArrayList<>();
    FollowersFollowingAdapter followersFollowingAdapter;
    RecyclerView userView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        Intent intent = getIntent();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        String searchValue = intent.getStringExtra("searchValue");
        SharedPreferences.Editor editor=sharedPreferences.edit();
        userView=findViewById(R.id.users_recycler_view);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        String userId=sharedPreferences.getString("userId",null);
        apiInterface.searchUser(ApplicationClass.token,ApplicationClass.userId,searchValue).enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());
                    userItems = response.body();
                    if(userItems.size()==0)
                        Toast.makeText(UsersActivity.this, "No Users", Toast.LENGTH_SHORT).show();
                    System.out.println("megana"+response.body());
                    userView.setAdapter(new FollowersFollowingAdapter(userItems,UsersActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {
                Log.i("fail", "vjhbkjkjbkj");
                System.out.println("followers failed");
            }
        });
        userView.setLayoutManager(new LinearLayoutManager(userView.getContext()));
    }

    @Override
    public void onclick(UserItem userItem, int position) {
        Intent intent=new Intent(UsersActivity.this,UserProfileActivity.class);
        intent.putExtra("id",userItem.getUserId());
        startActivity(intent);
        finish();
    }
}
