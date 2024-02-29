package com.example.UserMicroServices.service;


import com.example.UserMicroServices.entity.Business;
import com.example.UserMicroServices.entity.User;

import java.util.List;

public interface BusinessService {
    Business save(Business business);
    List<Business> findAllBusinessByModeratorId(String moderatorId);
    void addModeratorToBusinessId(String moderatorId,String businessId,String newModerator);
    void addFollower(String followerId,String businessId);
    void process(String followerId,String businessId,Boolean isAccepted);
    List<User> showFollowers(String businessId);


}
