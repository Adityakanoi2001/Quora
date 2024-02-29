package com.example.UserMicroServices.controller;


import com.example.UserMicroServices.entity.Business;
import com.example.UserMicroServices.entity.User;
import com.example.UserMicroServices.repository.BusinessRepo;
import com.example.UserMicroServices.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BusinessUser")
public class BusinessProfileController {
    @Autowired
    BusinessService businessService;

    @PostMapping("/saveBusinessProfile")
    public Business saveBusinessProfile(@RequestBody Business business)
    {
        return businessService.save(business);
    }

    @GetMapping("/findAllBusinessByModeratorId/{moderatorId}")
    public List<Business> findAllBusinessByModeratorId(@PathVariable("moderatorId") String moderatorId)
    {
        return businessService.findAllBusinessByModeratorId(moderatorId);
    }

    @GetMapping("/addModeratorToBusinessId{moderatorId}/{businessId}/{newModerator}")
    public void addModeratorToBusinessId(@PathVariable("moderatorId") String moderatorId,@PathVariable("businessId") String businessId,@PathVariable("newModerator") String newModerator)
    {
        businessService.addModeratorToBusinessId(moderatorId,businessId,newModerator);
    }

    @GetMapping("/addFollower/{followerId}/{businessId}")
    public void addFollower(@PathVariable("followerId") String followerId,@PathVariable("businessId") String businessId)
    {
        businessService.addFollower(followerId,businessId);
    }

    @GetMapping("/process/{followerId}/{businessId}/{isAccepted}")
    public void process(@PathVariable("followerId") String followerId,@PathVariable("businessId") String businessId,@PathVariable("isAccepted") Boolean isAccepted)
    {
        businessService.process(followerId,businessId,isAccepted);
    }

    @GetMapping("/showFollower/{businessId}")
    public List<User> showFollower(@PathVariable("businessId") String businessId)
    {
        return businessService.showFollowers(businessId);
    }



}
