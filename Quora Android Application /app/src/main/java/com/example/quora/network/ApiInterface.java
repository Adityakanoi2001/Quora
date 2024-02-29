package com.example.quora.network;

import com.example.quora.model.AddAnswerDto;
import com.example.quora.model.AddCommentDto;
import com.example.quora.model.AdsDto;
import com.example.quora.model.AnswerEntity;
import com.example.quora.model.BusinessRegistration;
import com.example.quora.model.BusinessRegistrationResponse;
import com.example.quora.model.CommentResponse;
import com.example.quora.model.PostsItem;
import com.example.quora.model.QuestionEntityListItem;
import com.example.quora.model.QuestionInput;
import com.example.quora.model.QuestionInputResponse;
import com.example.quora.model.Questions;
import com.example.quora.model.RegistrationResponse;
import com.example.quora.model.ShowComments;
import com.example.quora.model.UserDto;
import com.example.quora.model.UserItem;
import com.example.quora.model.UserLogin;
import com.example.quora.model.UserRegistration;
import com.example.quora.model.Vote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/quoraUser/User/findUserById/{userId}")
    Call<UserItem>findUserById(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userId") String userId);

    @GET("/quoraQues/question/getPosts")
    Call<List<PostsItem>>getPosts(@Header ("Authorization") String token,@Header("userId") String userid);

    @GET("/quoraUser/User/findFollowerByUserId/{userId}")
    Call<List<UserItem>>findFollowerByUserId(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userId") String userId);

    @GET("/quoraUser/User/findFollowingByUserId/{userId}")
    Call<List<UserItem>>findFollowingByUserId(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userId") String userId);

    @GET("/quoraQues/question/getQuestionByQuestionUserId/{userId}")
    Call<Questions>getQuestionByQuestionUserId(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userId") String userId);

    @POST("/oauth/api/auth/register")
    Call<RegistrationResponse> register(@Body UserRegistration userRegistration);

    @POST("/oauth/api/auth/login")
    Call<RegistrationResponse> login(@Body UserLogin userLogin);


    @POST("/quoraUser/User/saveUser")
    Call<UserDto> saveUser(@Header ("Authorization") String token,@Header("userId") String userid,@Body UserDto userDto);

    @POST("/quoraQues/answer/upVote/{answerId}/{userId}")
    Call<Vote> upVote(@Header ("Authorization") String token,@Header("userId") String userid,@Path("answerId") String answerId,@Path("userId") String userId);


    @POST("/quoraQues/answer/downVote/{answerId}/{userId}")
    Call<Vote> downVote(@Header ("Authorization") String token,@Header("userId") String userid,@Path("answerId") String answerId,@Path("userId") String userId);


    @GET("/quoraQues/answer/getListOfAnswersWithQuestionId/{questionId}")
    Call<List<AnswerEntity>> getAllAnswer(@Header ("Authorization") String token,@Header("userId") String userid,@Path("questionId") String questionId);

    @POST("/quoraUser/User/follow/{followerId}/{followingId}")
    Call<Void> followUser(@Header ("Authorization") String token,@Header("userId") String userid,@Path("followerId") String followerId,@Path("followingId") String followingId);


    @GET("/quoraUser/User/searchUser/{userName}")
    Call<List<UserItem>>searchUser(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userName") String userName);

    @GET("/quoraQues/question/getAllQuestions")
    Call<List<QuestionEntityListItem>>getAllQuestion(@Header ("Authorization") String token,@Header("userId") String userid);
    @POST("/quoraQues/question/addQuestion")
    Call<QuestionInputResponse> addQuestion(@Header ("Authorization") String token,@Header("userId") String userid,@Body QuestionInput questionInput);

    @POST("/quoraQues/answer/addAnswer")
    Call<QuestionInputResponse> addAnswer(@Header ("Authorization") String token,@Header("userId") String userid,@Body AddAnswerDto addAnswerDto);

    @GET("/quoraQues/answer/getAllAnswerByAnswerUserId/{answerUserId}")
    Call<List<AnswerEntity>> showAllAnswerByUserId(@Header ("Authorization") String token,@Header("userId") String userid,@Path("answerUserId") String answerUserId);


    @GET("/quoraUser/User/showFollowButton/{followerId}/{followingId}")
    Call<Boolean> showFollow(@Header ("Authorization") String token,@Header("userId") String userid,@Path("followerId") String followerId,@Path("followingId") String followingId);



    @POST("/notify/saveDeviceDetails/{userId}/{token}/{topic}")
    Call<String>saveDeviceDetails(@Path("userId") String userName,@Path("token") String token,@Path("topic") String topic);

    @POST("/notify/notifyLogin/{userId}")
    Call<String>notifyLogin(@Path("userId") String userId);

    @GET("/quoraUser/User/findAllPendingRequest/{userId}")
    Call<List<UserItem>> findAllPendingRequest(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userId") String userId);

    @POST("/quoraUser/User/process/{followerId}/{followingId}")
    Call<Void> processRequest(@Header ("Authorization") String token,@Header("userId") String userid,@Path("followerId") String followerId,@Path("followingId") String followingId,@Query("isAccepted") Boolean isAccepted);



    @GET("/quoraQues/comment/getcommentbyanswer/{answerId}")
    Call<ShowComments>showAllComments(@Header ("Authorization") String token, @Header("userId") String userid , @Path("answerId") String answerId);

    @POST("/quoraQues/comment/addcomment")
    Call<CommentResponse> addcomment(@Header ("Authorization") String token, @Header("userId") String userid, @Body AddCommentDto addCommentDto);

    @POST("/api/users/registerCorporate/{id}")
    Call<BusinessRegistrationResponse> registerBusiness(@Header ("Authorization") String token,@Path("id") String id,@Body BusinessRegistration businessRegistration);

    @GET("/ads/sendAds/{userId}")
    Call<List<AdsDto>> showAds(@Path("userId") String userId);

    @GET("/quoraUser/User/validateUser/{followerId}/{followingId}")
    Call<Boolean>validateUser(@Header ("Authorization") String token,@Header("userId") String userid,@Path("followerId") String followerId,@Path("followingId") String followingId);

    @GET("/quoraQues/comment/getcommentbyparent/{parentId}")
    Call<ShowComments>showSecondLevelComment(@Header ("Authorization") String token, @Header("userId") String userid , @Path("parentId") String parentId);

    @GET("/quoraQues/question/searchQuestion/{questionsearch}")
    Call<List<QuestionEntityListItem>>searchQues(@Header ("Authorization") String token,@Header("userId") String userid,@Path("questionsearch") String questionsearch);

    @GET("/quoraQues/question/getPostsbyUserId/{userId}")
    Call<List<PostsItem>>getPostsByUserId(@Header ("Authorization") String token,@Header("userId") String userid,@Path("userId") String userId);
}
