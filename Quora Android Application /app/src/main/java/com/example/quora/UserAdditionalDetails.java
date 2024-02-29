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
import com.example.quora.model.UserDto;
import com.example.quora.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdditionalDetails extends AppCompatActivity {
    ApiInterface apiInterface;
    Boolean isPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_additional_details);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        EditText etUserName=findViewById(R.id.et_userdetail_name);
        EditText etImg=findViewById(R.id.et_image);
        EditText etBio=findViewById(R.id.et_bio);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Intent intent=getIntent();
        isPrivate=intent.getBooleanExtra("isPrivate",true);

        Button submit=findViewById(R.id.submit);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId=sharedPreferences.getString("userId",null);
                UserDto userDto=new UserDto();
                userDto.setUserId(userId);
                userDto.setUserName(String.valueOf(etUserName.getText()));
                userDto.setBio(String.valueOf(etBio.getText()));
                userDto.setImg(String.valueOf(etImg.getText()));

                userDto.setIsPublic(!isPrivate);
                apiInterface.saveUser(ApplicationClass.token,ApplicationClass.userId,userDto).enqueue(new Callback<UserDto>() {
                    @Override
                    public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                        Toast.makeText(UserAdditionalDetails.this, "details saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserAdditionalDetails.this,LoginActivity.class));
                    }

                    @Override
                    public void onFailure(Call<UserDto> call, Throwable t) {
                        Toast.makeText(UserAdditionalDetails.this, "problem", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}