package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quora.application.ApplicationClass;
import com.example.quora.model.RegistrationResponse;
import com.example.quora.model.UserRegistration;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView login;
    EditText firstname,lastname,email,password,username,phonenumber,dob,platformId,publicOrPrivate;
    CheckBox music,food,sports,travel,entertainment,fashion;

    Button register;
    Boolean isPrivate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname=findViewById(R.id.et_first_name);
        lastname=findViewById(R.id.et_last_name);
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        username=findViewById(R.id.et_user_name);
        phonenumber=findViewById(R.id.et_phone_number);
        dob=findViewById(R.id.et_date_of_birth);
        platformId=findViewById(R.id.et_platform_id);
        login=findViewById(R.id.tv_login);
        register=findViewById(R.id.sign_up);
        publicOrPrivate=findViewById(R.id.et_public_or_private);
        music =findViewById(R.id.music);
        food=findViewById(R.id.food);
        sports=findViewById(R.id.sports);
        travel=findViewById(R.id.travel);
        entertainment=findViewById(R.id.entertainment);
        fashion=findViewById(R.id.fashion);

        if(ApplicationClass.userId!=null)
        {
            Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);

            startActivity(intent);
            finish();
        }
        ApiInterface apiInterface;
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistration userRegistration=new UserRegistration();
                userRegistration.setFirstName(String.valueOf(firstname.getText()));
                userRegistration.setLastName(String.valueOf(lastname.getText()));
                userRegistration.setEmail(String.valueOf(email.getText()));
                userRegistration.setPassword(String.valueOf(password.getText()));
                userRegistration.setPhoneNumber(String.valueOf(phonenumber.getText()));
                userRegistration.setUsername(String.valueOf(username.getText()));

                List<String> categories=new ArrayList<>();

                if(fashion.isChecked()){
                    categories.add("fashion");
                }
                if(food.isChecked()){
                    categories.add("food");
                }
                if(sports.isChecked()){
                    categories.add("sports");
                }
                if(music.isChecked()){
                    categories.add("music");
                }
                if(travel.isChecked()){
                    categories.add("travel");
                }
                if(entertainment.isChecked()){
                    categories.add("entertainment");
                }
                if(String.valueOf(publicOrPrivate.getText()).equals("private")) {
                    userRegistration.setPrivate(true);
                    isPrivate = true;
                }
                else {
                    userRegistration.setPrivate(false);
                    isPrivate = false;
                }
                userRegistration.setDob(String.valueOf(dob.getText()));
                userRegistration.setPlatformId(String.valueOf(platformId.getText()));
                userRegistration.setCategories(categories);

                apiInterface.register(userRegistration).enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        RegistrationResponse registrationResponse=response.body();
                        String userId=registrationResponse.getUserId();
                        editor.putString("userId",userId);
                        editor.commit();
                        //Toast.makeText( RegisterActivity.this,"kcgskvcksjbckljsbc", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.putExtra("isPrivate",isPrivate);
                        startActivity(new Intent(RegisterActivity.this,UserAdditionalDetails.class));
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        System.out.println("no response");
                    }
                });


            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}