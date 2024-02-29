package com.example.UserMicroServices.service.impl;

import com.example.UserMicroServices.entity.Business;
import com.example.UserMicroServices.entity.Follow;
import com.example.UserMicroServices.entity.User;
import com.example.UserMicroServices.repository.BusinessRepo;
import com.example.UserMicroServices.repository.FollowRepo;
import com.example.UserMicroServices.repository.UserRepo;
import com.example.UserMicroServices.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepo businessRepo;

    @Autowired
    FollowRepo followRepo;

    @Autowired
    UserRepo userRepo;


    @Override
    public Business save(Business business) {

        return businessRepo.save(business);
    }

    @Override
    public List<Business> findAllBusinessByModeratorId(String moderatorId) {
        List<String> moderatorList=new ArrayList<>();
        List<Business> business=new ArrayList<>();
        List<Business> businessList=businessRepo.findAll();
        for(Business b:businessList)
        {
            moderatorList=b.getModeratorIds();
            if(moderatorList.contains(moderatorId))
                business.add(b);
        }
        return business;
    }

    @Override
    public void addModeratorToBusinessId(String moderatorId, String businessId, String newModerator) {

        try {
            // todo: write native query to handle this scenario in single query
            Optional<Business> business = businessRepo.findById(businessId);
            // todo: handle null pointer expection
            List<String> moderatorList = business.get().getModeratorIds();
           // moderatorList.stream().filter(moderator -> Objects.equals(moderator, newModerator)).collect(Collectors.toList()).size() == 1
            if (moderatorList.contains(moderatorId)) {
                moderatorList.add(newModerator);
                business.get().setModeratorIds(moderatorList);
                businessRepo.save(business.get());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addFollower(String followerId, String BusinessId) {
        Follow follow=new Follow();
        Optional<Business> business=businessRepo.findById(BusinessId);
            follow.setFollowerId(followerId);
            follow.setFollowingId(BusinessId);
            follow.setConnection("pending");
            followRepo.save(follow);

    }

    @Override
    public void process(String followerId, String businessId, Boolean isAccepted) {
        if(isAccepted)
        {
            Follow follow=followRepo.findByFollowerIdAndFollowingId(followerId,businessId);
            follow.setConnection("connected");
            follow.setUpdatedDate(new Date());
            followRepo.save(follow);
        }
        else {
            followRepo.deleteByFollowerIdAndFollowingId(followerId,businessId);
        }
    }

    @Override
    public List<User> showFollowers(String businessId) {
        //Optional<Business> business=businessRepo.findById(businessId);
        List<Follow> followList=followRepo.findByFollowingIdAndConnection(businessId,"connected");
        List<User> userList=new ArrayList<>();
        for(Follow f:followList)
        {
            //User user=new User();
            String followerId=f.getFollowerId();
            Optional<User> user=userRepo.findById(followerId);
            userList.add(user.get());
        }
        return userList;
    }

}
