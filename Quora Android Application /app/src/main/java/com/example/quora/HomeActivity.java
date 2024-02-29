package com.example.quora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.quora.adapter.HomeAdapter;
import com.example.quora.application.ApplicationClass;
import com.example.quora.model.AdsDto;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.Posts;
import com.example.quora.model.PostsItem;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.Questions;
import com.example.quora.model.Vote;
import com.example.quora.network.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnClickInterface{


    List<PostsItem> postsItems=new ArrayList<>();
    List<AnswerEntity> answerEntityList = new ArrayList<>();
    RecyclerView home_recycler_view;
    List<AdsDto> adsDtoList=new ArrayList<>();
    HomeAdapter homeAdapter;
    Toolbar toolbar;
    ApiInterface apiInterface;
    ApiInterface apiInterfaceAds;
    BottomNavigationView bottomNavigationView;

//    ApiInterface apiInterfaceQusenAndAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#AA2200"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);
        home_recycler_view=findViewById(R.id.home_recycler_view);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.ask:
                        Intent intent=new Intent(HomeActivity.this,AskQuestionActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.answer:
                        Intent intent1=new Intent(HomeActivity.this,GetAllQuestionsActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.notification:
                        Intent intent2=new Intent(HomeActivity.this,NotificationActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.user:
                        Intent intent3=new Intent(HomeActivity.this,UserProfileActivity.class);
                        startActivity(intent3);
                        return true;
                }
                return false;
            }
        });



            //apiInterface = ((ApplicationClass)getApplication()).retrofit2.create(ApiInterface.class);

            apiInterfaceAds=((ApplicationClass)getApplication()).retrofit4.create(ApiInterface.class);

            apiInterfaceAds.showAds(ApplicationClass.userId).enqueue(new Callback<List<AdsDto>>() {
                @Override
                public void onResponse(Call<List<AdsDto>> call, Response<List<AdsDto>> response) {
                    adsDtoList=response.body();

                    System.out.println("kabka");
                    //System.out.println(adsDtoList);
                }

                @Override
                public void onFailure(Call<List<AdsDto>> call, Throwable t) {

                    System.out.println("problem");
                }
            });





        apiInterface.getPostsByUserId(ApplicationClass.token,ApplicationClass.userId,ApplicationClass.userId).enqueue(new Callback<List<PostsItem>>() {

            @Override
            public void onResponse(Call<List<PostsItem>> call, Response<List<PostsItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("successallhome", "" + response.isSuccessful());
                    postsItems= response.body();
                    for(PostsItem p:postsItems)
                    {
                        answerEntityList.add(p.getAnswerEntity());
                    }
                  System.out.print("Megana"+response.body());
                    homeAdapter=new HomeAdapter(answerEntityList,HomeActivity.this,adsDtoList);
                    home_recycler_view.setAdapter(homeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<PostsItem>> call, Throwable t) {
                Log.i("fail", "home failed");
            }
        });
        home_recycler_view.setLayoutManager(new LinearLayoutManager( home_recycler_view.getContext()));
}

    @Override
    public void onFollowClick(AnswerEntity answerEntity, int position) {

    }

    @Override
    public void onReadMoreClick(AnswerEntity answerEntity, int position) {
        Intent intent=new Intent(HomeActivity.this,ReadMoreActivity.class);
        intent.putExtra("questionId",answerEntity.getQuestionId());
        intent.putExtra("questionBody",answerEntity.getQuestionBody());
        startActivity(intent);
    }

    @Override
    public void onUpvote(AnswerEntity answerEntity, int position) {
        apiInterface.upVote(ApplicationClass.token,ApplicationClass.userId,answerEntity.getAnswerID(),answerEntity.getAnswerUserId()).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(Call<Vote> call, Response<Vote> response) {
                //Toast.makeText(HomeActivity.this, "working", Toast.LENGTH_SHORT).show();
//                answerEntity.setVote(answerEntity.getVote()+1);
                //homeAdapter.notifyDataSetChanged();
//                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
//                finish();

            }

            @Override
            public void onFailure(Call<Vote> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "not working", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onDownvote(AnswerEntity answerEntity, int position) {
        apiInterface.downVote(ApplicationClass.token,ApplicationClass.userId,answerEntity.getAnswerID(),answerEntity.getAnswerUserId()).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(Call<Vote> call, Response<Vote> response) {
                response.body();
              //  answerEntity.setVote(answerEntity.getVote()-1);
                //System.out.println("yeee");
                //homeAdapter.notifyDataSetChanged();
//                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
//                finish();

            }

            @Override
            public void onFailure(Call<Vote> call, Throwable t) {
                System.out.println("noooooo");
            }
        });
    }

    @Override
    public void onComment(AnswerEntity answerEntity, int position) {
        Intent intent=new Intent(HomeActivity.this,CommentActivity.class);
        intent.putExtra("answerId",answerEntity.getAnswerID());
        startActivity(intent);
    }

    @Override
    public void onAnswer(AnswerEntity answerEntity, int position) {
        Intent intent=new Intent(HomeActivity.this,AnsweringQuestionActivity.class);
        intent.putExtra("qid",answerEntity.getQuestionId());
        startActivity(intent);
    }

    @Override
    public void toProfile(AnswerEntity answerEntity, int position) {
        Intent intent=new Intent(HomeActivity.this,UserProfileActivity.class);
        intent.putExtra("id",answerEntity.getAnswerUserId());
        startActivity(intent);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                Intent intent=new Intent(HomeActivity.this,RegisterActivity.class);
                ApplicationClass.userId=null;
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav_menu, menu);
        getMenuInflater().inflate(R.menu.top_search_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CharSequence searchValue = searchView.getQuery();
                Intent intent = new Intent(HomeActivity.this,SearchQuestionActivity.class);
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