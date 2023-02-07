package com.example.QuestionsMicroService.DTO;

import lombok.Data;

@Data
public class User
{
    private String userId; // Email of the user just named as the Userid
    private String userName;
    private Integer score;
    private String bio;
    private String img ;
    private String classification;
    private Boolean isPublic;
}
//under develpoment
