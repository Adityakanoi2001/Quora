package com.example.QuestionsMicroService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

}
