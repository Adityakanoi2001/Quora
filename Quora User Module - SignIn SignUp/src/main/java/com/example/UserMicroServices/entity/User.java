package com.example.UserMicroServices.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = User.collection_name)
@Getter
@Setter
public class User
{
    public static final String collection_name="User";
    @Id
    private String userId; // Email of the user just named as the Userid
//    private String userId;

    private String userName;
    private Integer score;
    private String bio;
    private String img ;
   // private List<String> questions;
   // private List<Answer> answers;
//    private List<User> followers;
//    private List<User> following;
    private String classification;
    private Boolean isPublic;

}
