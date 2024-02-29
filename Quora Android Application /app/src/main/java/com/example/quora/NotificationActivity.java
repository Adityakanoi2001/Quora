package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.quora.adapter.FollowersFollowingAdapter;
import com.example.quora.adapter.PendingRequestsAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AdsDto;
import com.example.quora.model.UserItem;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity implements PendingRequestsAdapter.PendingRequestOnclick{

    PendingRequestsAdapter pendingRequestsAdapter;
    RecyclerView recyclerViewPendingRequest;

    List<UserItem> userItemList=new ArrayList<>();
    ApiInterface apiInterface;
    List<AdsDto> adsDtoList=new ArrayList<>();
    ApiInterface apiInterfaceAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        recyclerViewPendingRequest=findViewById(R.id.pendingrequest_recycler_view);

        apiInterfaceAds=((ApplicationClass)getApplication()).retrofit4.create(ApiInterface.class);

        apiInterfaceAds.showAds(ApplicationClass.userId).enqueue(new Callback<List<AdsDto>>() {
            @Override
            public void onResponse(Call<List<AdsDto>> call, Response<List<AdsDto>> response) {
                adsDtoList=response.body();
                Random rand = new Random();
                AdsDto randomElement = adsDtoList.get(rand.nextInt(adsDtoList.size()));


                System.out.println("kabka");
                //System.out.println(adsDtoList);
            }

            @Override
            public void onFailure(Call<List<AdsDto>> call, Throwable t) {

                System.out.println("problem");
            }
        });



        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        apiInterface.findAllPendingRequest(ApplicationClass.token,ApplicationClass.userId,ApplicationClass.userId).enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                userItemList=response.body();
                pendingRequestsAdapter=new PendingRequestsAdapter(userItemList,NotificationActivity.this);
                recyclerViewPendingRequest.setAdapter(pendingRequestsAdapter);
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {

                System.out.println("problem");
            }
        });
        recyclerViewPendingRequest.setLayoutManager(new LinearLayoutManager(recyclerViewPendingRequest.getContext()));

    }

    @Override
    public void OnAcceptClick(UserItem userItem, int position) {
        apiInterface.processRequest(ApplicationClass.token,ApplicationClass.userId,userItem.getUserId(),ApplicationClass.userId,true).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                System.out.println("domee");
                userItemList.remove(position);
                pendingRequestsAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                System.out.println("not doneee");
            }
        });

    }

    @Override
    public void OnRejectClick(UserItem userItem, int position) {

        apiInterface.processRequest(ApplicationClass.token,ApplicationClass.userId,userItem.getUserId(),ApplicationClass.userId,false).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                System.out.println("domee");
                userItemList.remove(position);
                pendingRequestsAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                System.out.println("not doneee");
            }
        });



    }
}