package com.example.UserMicroServices.service;

import com.example.UserMicroServices.dto.FollowDto;
import com.example.UserMicroServices.dto.UserDTO;
import com.example.UserMicroServices.entity.Follow;
import com.example.UserMicroServices.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    String deleteById(String userId);
    Optional<User> findUserById(String userId);
    String deleteAllUsers();
//    List<User> showFollowersByUsedId(String userId);
//    List<User> showFollowingByUsedId(String userId);
    //void addFollowingFollower(String followerId,String followingId);
    void follow(String followerId,String  followingId);
    void unfollow(String followerId,String followingId);
    List<User> showFollowersByUserId(String userId);
    List<User> showFollowingByUserId(String userId);
    Boolean increaseScoreByTen(String userId);
    Boolean decreaseScoreByOne(String userId);
    Boolean increaseScoreByOne(String userId);
    List<User> showAllUsers();
    String findUserNameById(String  userId);
    List<User> findAllPendingRequestForUser(String userId);
    void process(String followerId,String followingId,Boolean isAccepted);
    List<User> userSearch(String searchTerm);
    Boolean showFollowButton(String followerId,String followingId);
    String userImgByUserId(String userId);

    Boolean changeProfileType(String userId);


}
