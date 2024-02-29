package com.example.UserMicroServices.controller;

import com.example.UserMicroServices.dto.FollowDto;
import com.example.UserMicroServices.dto.UserDTO;
import com.example.UserMicroServices.entity.Follow;
import com.example.UserMicroServices.entity.User;
import com.example.UserMicroServices.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://10.20.5.66:8082")
@RestController
@RequestMapping("/User")
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/saveUser")
    public ResponseEntity<UserDTO> saveUserDetails(@RequestBody UserDTO userDTO)
    {

        User user=new User();
        UserDTO u=new UserDTO();
        BeanUtils.copyProperties(userDTO,user);
        user=userService.saveUser(user);
        BeanUtils.copyProperties(user,u);
        return new ResponseEntity<UserDTO>(u,HttpStatus.OK);
    }

    @GetMapping("/findUserById/{userId}")
    public User findUserById(@PathVariable("userId") String userId)
    {
        return userService.findUserById(userId).get();
    }

    @GetMapping("/findAllUser")
    public List<User> findAllUsers()
    {
        return userService.showAllUsers();
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public String deleteUserById(@PathVariable("userId") String userId)
    {
        return userService.deleteById(userId);

    }

//    @DeleteMapping("/deleteAllUser")
//    public String deleteAllUser()
//    {
//        return userService.deleteAllUsers();
//
//    }

//    @GetMapping("findFollowersByUserId")
//    public List<User> findFollowersByUserId(String userId)
//    {
//        return userService.showFollowersByUsedId(userId);
//    }
//
//    @GetMapping("findFollowingByUserId")
//    public List<User> findFollowingByUserId(String userId)
//    {
//        return userService.showFollowingByUsedId(userId);
//    }

//    @GetMapping("addFolllowerFollowing")
//    public void findFollowingByUserId(String userIdOne,String  userIdTwo)
//    {
//        userService.addFollowingFollower(userIdOne,userIdTwo);
//    }

    @PostMapping("/follow/{followerId}/{followingId}")
    public void follow(@PathVariable("followerId") String followerId,@PathVariable("followingId") String followingId)
    {
        userService.follow(followerId,followingId);
    }

    @DeleteMapping("/unfollow/{followerId}/{followingId}")
    public void unfollow(@PathVariable("followerId") String followerId,@PathVariable("followingId") String followingId)
    {
        userService.unfollow(followerId,followingId);
    }

    @GetMapping("/findFollowerByUserId/{userId}")
    public List<User> findFollowerById(@PathVariable("userId") String userId)
    {
        return userService.showFollowersByUserId(userId);
    }

    @GetMapping("/findFollowingByUserId/{userId}")
    public List<User> findFollowingById(@PathVariable("userId") String userId)
    {
        return userService.showFollowingByUserId(userId);
    }

    @PostMapping("/increaseScoreByTen/{userId}")
    public Boolean increaseScoreByTen(@PathVariable("userId") String userId)
    {
        return userService.increaseScoreByTen(userId);
    }

    @PostMapping("/increaseScoreByOne/{userId}")
    public Boolean increaseScoreByOne(@PathVariable("userId") String userId)
    {
        return userService.increaseScoreByOne(userId);
    }

    @PostMapping("/decreaseScoreByOne/{userId}")
    public Boolean decreaseScoreByOne(@PathVariable("userId") String userId)
    {
        return userService.decreaseScoreByOne(userId);
    }

    @GetMapping("/findUserNameByUserId/{userId}")
    public String  findUserNameById(@PathVariable("userId") String userId)
    {
        return userService.findUserNameById(userId);
    }

    @GetMapping("/findUserImgByUserId/{userId}")
    public String  findUserImgById(@PathVariable("userId") String userId)
    {
        return userService.userImgByUserId(userId);
    }

    @GetMapping("/findAllPendingRequest/{userId}")
    public List<User>  findAllPendingRequest(@PathVariable("userId") String userId)
    {
        return userService.findAllPendingRequestForUser(userId);
    }

    @PostMapping("/process/{followerId}/{followingId}")
    public void processRequest(@PathVariable("followerId") String followerId,@PathVariable("followingId") String followingId,@RequestParam Boolean isAccepted)
    {
        userService.process(followerId,followingId,isAccepted);
    }


    @GetMapping("/searchUser/{userName}")
    public List<User>  seacrhUser(@PathVariable("userName") String userName)
    {
        return userService.userSearch(userName);
    }

    @GetMapping("/showFollowButton/{followerId}/{followingId}")
    public Boolean showFollowButton(@PathVariable("followerId") String followerId,@PathVariable("followingId") String followingId)
    {
        return userService.showFollowButton(followerId,followingId);
    }



}
