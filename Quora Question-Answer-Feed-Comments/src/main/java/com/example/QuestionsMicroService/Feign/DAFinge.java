package com.example.QuestionsMicroService.Feign;

import com.example.QuestionsMicroService.DTO.DaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "DaFinge",url="http://10.20.5.13:8075/kafka")
public interface DAFinge
{

    @PostMapping("/postMsg")
    void sendData (DaDto daDto);

}