package com.example.quora.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.quora.constants.ConstantClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application{
    public Retrofit retrofit;
    public  Retrofit retrofit1;
    public  Retrofit retrofit2;
    public  Retrofit retrofit3;
    public static String userId;
    public static String token;
    public Retrofit retrofit4;

    @Override
    public void onCreate()
    {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ConstantClass.BASE_URL)
                .client(new OkHttpClient())
                .build();

        retrofit1=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ConstantClass.BASE_URL1)
                .client(new OkHttpClient())
                .build();
        retrofit2=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ConstantClass.BASE_URL2)
                .client(new OkHttpClient())
                .build();
        retrofit3=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ConstantClass.BASE_URL3)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient())
                .build();

        retrofit4=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ConstantClass.BASE_URL4)
                .client(new OkHttpClient())
                .build();

        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        userId=sharedPreferences.getString("userId",null);
        token=sharedPreferences.getString("token",null);


    }}
