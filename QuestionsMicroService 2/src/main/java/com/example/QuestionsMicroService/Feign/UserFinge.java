package com.example.QuestionsMicroService.Feign;

import com.example.QuestionsMicroService.DTO.FollowersDto;
import com.example.QuestionsMicroService.DTO.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "UserFinge" , url = "http://10.20.5.7:9080/")
public interface UserFinge
{
    @RequestMapping(method = RequestMethod.POST, value = "User/increaseScoreByOne/{userId}")
    Boolean increaseScoreByOne(@PathVariable("userId") String userId);

    @RequestMapping(method = RequestMethod.POST, value = "User/decreaseScoreByOne/{userId}")
    Boolean decreaseByOne(@PathVariable("userId") String userId);

    @PostMapping("/User/increaseScoreByTen/{userId}")
    Boolean increaseByTen(@PathVariable("userId") String userId);

    @GetMapping("/User/findUserNameByUserId/{userId}")
    String findUserNameByUserId(@PathVariable("userId")  String userId);

    @GetMapping("/User/findFollowerByUserId/{userId}")
    List<FollowersDto> findFollowerByUserId(@PathVariable("userId") String userId);

    @GetMapping("/User/findUserImgByUserId/{userId}")
    String findUserImgByUserId(@PathVariable("userId") String userId);

    @GetMapping("/User/findFollowingByUserId/{userId}")
    List<User> findFollowing(@PathVariable("userId") String userId);
}
