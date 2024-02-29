package com.example.quora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quora.adapter.FollowersFollowingAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.User;
import com.example.quora.model.UserItem;
import com.example.quora.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    public ApiInterface apiInterface;
    FollowersFollowingAdapter followersFollowingAdapter;
    UserItem userItems=new UserItem();
    List<QuestionEntityListItem> questionEntityListItems=new ArrayList<>();
    TextView questions,answers,following,followers,name,bio,score,classification,typeofprofile;
    ImageView profileImage;
    String userId;
    String userId2;
    String loggedIn;
    UserItem userItems1;
    Boolean flag;
    List<UserItem> userItemList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        SharedPreferences sharedPreferences=getSharedPreferences("cache",MODE_PRIVATE);
        Button follow;
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        questions=findViewById(R.id.questions);
        answers=findViewById(R.id.answers);
        following=findViewById(R.id.following);
        followers=findViewById(R.id.followers);
        name=findViewById(R.id.profile_username);
        bio=findViewById(R.id.profile_userbio);
        profileImage=findViewById(R.id.iv_profile_image);
        follow=findViewById(R.id.Follow);
        score=findViewById(R.id.profile_userscore);
        classification =findViewById(R.id.profile_userclassification);
        typeofprofile=findViewById(R.id.tv_profile_private);

        loggedIn=sharedPreferences.getString("userId",null);
        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

//        if(userId!=null) {
//            userId2 = userId;
//            loggedIn = sharedPreferences.getString("userId", null);
//        }

        if(userId==null) {
//            loggedIn=sharedPreferences.getString("userId",null);
            userId = sharedPreferences.getString("userId", null);
        }


        apiInterface.validateUser(ApplicationClass.token,ApplicationClass.userId,loggedIn,userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                flag=response.body();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

        // to show follow button
        apiInterface.showFollow(ApplicationClass.token,ApplicationClass.userId,loggedIn,userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body()==false)
                    follow.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("l");

                System.out.println("fail");
            }
        });

        // follow someone
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.followUser(ApplicationClass.token,ApplicationClass.userId,loggedIn,userId).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UserProfileActivity.this,"donee",Toast.LENGTH_SHORT).show();
                        apiInterface.findUserById(ApplicationClass.token,ApplicationClass.userId,userId).enqueue(new Callback<UserItem>() {
                            @Override
                            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                                UserItem userItem=response.body();
                                if (userItem.isIsPublic())
                                    follow.setText("Following");
                                else
                                    follow.setText("Requested");
                            }

                            @Override
                            public void onFailure(Call<UserItem> call, Throwable t) {

                                System.out.println("kbfkawev");
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UserProfileActivity.this," not donee",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        // for show profile of user
        apiInterface.findUserById(ApplicationClass.token,ApplicationClass.userId,userId).enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());
                    userItems = response.body();
                    System.out.println("megana"+response.body());
                    name.setText(String.valueOf(userItems.getUserName()));
                    bio.setText(String.valueOf(userItems.getBio()));
                    score.setText(String.valueOf(userItems.getScore()));
                    classification.setText(String.valueOf(userItems.getClassification()));
                    if(userItems.isIsPublic()){
                        typeofprofile.setText("public");
                    }
                    else{
                        typeofprofile.setText("private");
                    }
                    Glide.with(profileImage.getContext()).load(String.valueOf(userItems.getImg())).into(profileImage);
                }
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                Log.i("fail", "vjhbkjkjbkj");
                System.out.println(" home failed");
            }
        });


        // for visibility of private user
//        apiInterface.findFollowingByUserId(ApplicationClass.token,ApplicationClass.userId,loggedIn).enqueue(new Callback<List<UserItem>>() {
//            @Override
//            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
//                userItemList=response.body();
//                apiInterface.findUserById(ApplicationClass.token,ApplicationClass.userId,userId).enqueue(new Callback<UserItem>() {
//                    @Override
//                    public void onResponse(Call<UserItem> call, Response<UserItem> response) {
//                        userItems1=response.body();
//                        if(!userItemList.contains(userItems1))
//                        {
//                            flag=1;
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserItem> call, Throwable t) {
//                        System.out.println("problem");
//                    }
//                });
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<UserItem>> call, Throwable t) {
//
//            }
//        });


        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                {
                    Toast.makeText(UserProfileActivity.this, "can't show this profile", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(UserProfileActivity.this, GetAllQuestionUserActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                }

            }
        });

        answers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                    Toast.makeText(UserProfileActivity.this, "can't show this profile", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(UserProfileActivity.this, GetAllAnswersUser.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                }

            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                    Toast.makeText(UserProfileActivity.this, "can't show this profile", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(UserProfileActivity.this, FollowingActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                }

            }
        });
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                    Toast.makeText(UserProfileActivity.this, "can't show this profile", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(UserProfileActivity.this, FollowerActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_search_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CharSequence searchValue = searchView.getQuery();
                Intent intent = new Intent(UserProfileActivity.this,UsersActivity.class);
                intent.putExtra("searchValue", String.valueOf(searchValue));
                startActivity(intent);
                //Toast.makeText(HomePageActivity.this, "Searched", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}