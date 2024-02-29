package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class DefaultQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_questions);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }
}