package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AdsDto;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ads extends AppCompatActivity {

    ApiInterface apiInterface;
    List<AdsDto> adsDtoList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        //apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);

        apiInterface=((ApplicationClass)getApplication()).retrofit4.create(ApiInterface.class);

        apiInterface.showAds(ApplicationClass.userId).enqueue(new Callback<List<AdsDto>>() {
            @Override
            public void onResponse(Call<List<AdsDto>> call, Response<List<AdsDto>> response) {
                adsDtoList=response.body();
                System.out.println(adsDtoList);
            }

            @Override
            public void onFailure(Call<List<AdsDto>> call, Throwable t) {

                System.out.println("problem");
            }
        });

    }
}