package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quora.application.ApplicationClass;
import com.example.quora.model.BusinessRegistration;
import com.example.quora.model.BusinessRegistrationResponse;
import com.example.quora.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuisnessProfileRegistration extends AppCompatActivity {

    ApiInterface apiInterface;
    BusinessRegistration businessRegistration=new BusinessRegistration();
    String businessId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = ((ApplicationClass)getApplication()).retrofit3.create(ApiInterface.class);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        setContentView(R.layout.activity_buisness_profile_registration);
        EditText et_register_businessname=findViewById(R.id.et_register_businessname);
        EditText et_register_platformId=findViewById(R.id.et_register_platformId);
        Button business_registration=findViewById(R.id.business_registration);
        business_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessRegistration.setCorporateName(String.valueOf(et_register_businessname.getText()));
                businessRegistration.setPlatformId(String.valueOf(et_register_platformId.getText()));
                apiInterface.registerBusiness("Bearer " + ApplicationClass.token,ApplicationClass.userId,businessRegistration).enqueue(new Callback<BusinessRegistrationResponse>() {
                    @Override
                    public void onResponse(Call<BusinessRegistrationResponse> call, Response<BusinessRegistrationResponse> response) {
                        if(response.isSuccessful()) {
                            BusinessRegistrationResponse businessRegistrationResponse = response.body();
                            businessId = businessRegistrationResponse.getCorporateId();
                            editor.putString("businessId", businessId);
                            Toast.makeText(BuisnessProfileRegistration.this,"Registered",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BuisnessProfileRegistration.this, BusinessAdditionalDetails.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(BuisnessProfileRegistration.this,"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BusinessRegistrationResponse> call, Throwable t) {
                        Toast.makeText(BuisnessProfileRegistration.this,"enter correct details",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}