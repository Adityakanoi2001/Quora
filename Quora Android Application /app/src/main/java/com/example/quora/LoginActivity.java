package com.example.quora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quora.application.ApplicationClass;
import com.example.quora.model.RegistrationResponse;
import com.example.quora.model.UserLogin;
import com.example.quora.network.ApiInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button logIN;
    EditText userName,password;
    ApiInterface apiInterface;
    String token;
    RegistrationResponse registrationResponse=new RegistrationResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        logIN=findViewById(R.id.bt_log_in);
        userName=findViewById(R.id.et_log_username);
        password=findViewById(R.id.et_log_password);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin userLogin=new UserLogin();
                userLogin.setUsername(String.valueOf(userName.getText()));
                userLogin.setPassword(String.valueOf(password.getText()));
                userLogin.setPlatformId("quora");
                FirebaseMessaging.getInstance().subscribeToTopic("quora");
                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }
                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        System.out.println(token);
                        Log.e("TOKEN",token);
                    }
                });
                apiInterface.login(userLogin).enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if(response.isSuccessful() && response.body()!=null) {
                            registrationResponse = response.body();
                            editor.putString("userId", registrationResponse.getUserId());
                            editor.putString("token", registrationResponse.getAccessToken());
                            ApplicationClass.userId = registrationResponse.getUserId();
                            ApplicationClass.token = registrationResponse.getAccessToken();
                            editor.commit();


                            apiInterface.saveDeviceDetails(registrationResponse.getUserId(), token, "quora")
                                    .enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Log.i("TOKENonresponce", "fvvv");
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.i("TOKENonfail", "fvvv");
                                        }
                                    });
                            apiInterface.notifyLogin(registrationResponse.getUserId()).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Log.i("notifysuccess", "fvvvttt");
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Log.i("notifyfail", "fvvvttt");
                                }
                            });

                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Enter correct details", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
