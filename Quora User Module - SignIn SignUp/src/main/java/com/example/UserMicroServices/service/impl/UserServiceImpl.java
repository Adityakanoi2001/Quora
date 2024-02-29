package com.example.UserMicroServices.service.impl;

import com.example.UserMicroServices.dto.FollowDto;
import com.example.UserMicroServices.entity.Business;
import com.example.UserMicroServices.entity.Follow;
import com.example.UserMicroServices.entity.User;
import com.example.UserMicroServices.repository.BusinessRepo;
import com.example.UserMicroServices.repository.FollowRepo;
import com.example.UserMicroServices.repository.UserRepo;
import com.example.UserMicroServices.service.UserService;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FollowRepo followRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BusinessRepo businessRepo;


    @Override
    public User saveUser(User user)
    {
        user.setScore(0);
        user.setClassification("beginner");
        return userRepo.save(user);
    }

    @Override
    public String deleteById(String userId) {
        userRepo.deleteById(userId);
        return "deleted";
    }

    @Override
    public Optional<User> findUserById(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public String deleteAllUsers() {
        userRepo.deleteAll();
        return "All users deleted";
    }

    @Override
    public void follow(String followerId, String followingId)
    {
        Follow follow=new Follow();
        Optional<User> user=userRepo.findById(followingId);
        if(user.isPresent() && user.get().getIsPublic())
        {
            follow.setFollowerId(followerId);
            follow.setFollowingId(followingId);
            follow.setConnection("connected");
            follow.setCreatedDate(new Date());
            followRepo.save(follow);

        }
        else
        {
            follow.setFollowerId(followerId);
            follow.setFollowingId(followingId);
            follow.setConnection("pending");
            followRepo.save(follow);
        }
    }

    @Override
    public void unfollow(String followerId, String followingId) {
        followRepo.deleteByFollowerIdAndFollowingId(followerId,followingId);
    }

    @Override
    public List<User> showFollowersByUserId(String userId) {

        List<Follow> followers=followRepo.findByFollowingIdAndConnection(userId,"connected");
        List<FollowDto>followDtoList=new ArrayList<>();
        List<User> userList=new ArrayList<>();
        for(Follow f:followers)
        {
            FollowDto followDto=new FollowDto();
            Optional<User> user=userRepo.findById(f.getFollowerId());
            if(user==null)
                continue;
            userList.add(user.get());

        }
        return userList;
        //return followRepo.findByFollowingId(userId);
    }



    @Override
    public List<User> showFollowingByUserId(String userId) {

        List<Follow> following=followRepo.findByFollowerIdAndConnection(userId,"connected");
        //List<FollowDto>followDtoList=new ArrayList<>();
        List<User> userList=new ArrayList<>();
        List<FollowDto> followDtoList=new ArrayList<>();
        for(Follow f:following)
        {
            FollowDto followDto=new FollowDto();
            Optional<User> user=userRepo.findById(f.getFollowingId());
            if(user!=null)
            userList.add(user.get());
        }
        return userList;

    }

    @Override
    public Boolean increaseScoreByTen(String userId) {
        Optional<User> user=userRepo.findById(userId);
        if(user==null)
            return false;
        Integer score=user.get().getScore();
        score+=10;
        user.get().setScore(score);
        userRepo.save(user.get());
        return true;
    }

    @Override
    public Boolean decreaseScoreByOne(String userId) {
        Optional<User> user=userRepo.findById(userId);
        if(user==null)
            return false;
        Integer score=user.get().getScore();
        score-=1;
        user.get().setScore(score);
        userRepo.save(user.get());
        return true;

    }

    @Override
    public Boolean increaseScoreByOne(String userId) {
        Optional<User> user=userRepo.findById(userId);
        if(user==null)
            return false;
        Integer score=user.get().getScore();
        score+=1;
        user.get().setScore(score);
        userRepo.save(user.get());
        return true;
    }

    @Override
    public List<User> showAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String findUserNameById(String userId)
    {

        User user=userRepo.findUserNameByUserId(userId);
        return user.getUserName();
    }

    @Override
    public List<User> findAllPendingRequestForUser(String userId) {
        List<Follow> followList;
        followList= followRepo.findByFollowingIdAndConnection(userId,"pending");

        List<User> userList=new ArrayList<>();
        for(Follow f:followList)
        {
            String id=f.getFollowerId();
            System.out.println(id);
            Optional<User> user=userRepo.findById(id);
            User user1;
            user1=user.get();
            System.out.println(user);
            userList.add(user1);
        }return userList;
    }

    @Override
    public void process(String followerId, String followingId, Boolean isAccepted) {
        if(isAccepted==true)
        {
            Follow follow=followRepo.findByFollowerIdAndFollowingId(followerId,followingId);
            follow.setConnection("connected");
            follow.setUpdatedDate(new Date());
            followRepo.save(follow);
        }
        else {
            followRepo.deleteByFollowerIdAndFollowingId(followerId,followingId);
        }

    }


    @Override
    public List<User> userSearch(String searchTerm)
    {
        Pattern regs = Pattern.compile(searchTerm, Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").regex(regs));
        List<User> userList = mongoTemplate.find(query,User.class);
        return userList;
    }

    @Override
    public Boolean showFollowButton(String followerId, String followingId) {
        if(followerId.equals(followingId))
            return false;
        List<User> userList=showFollowingByUserId(followerId);


        if(userList==null || userList.size()==0)
            return true;
        if(followerId.equals(followingId))
            return false;
        for(User u:userList)
        {
            if(u.getUserId().equals(followingId))
                return false;
        }
        return true;
    }

    @Override
    public String userImgByUserId(String userId)
    {
        Optional<User> user=userRepo.findById(userId);
        return user.get().getImg();
    }

    @Override
    public Boolean changeProfileType(String userId)
    {
        Optional<User> user=userRepo.findById(userId);
        if(user==null)
            return false;
        user.get().setIsPublic(!user.get().getIsPublic());
        return true;
    }



}
