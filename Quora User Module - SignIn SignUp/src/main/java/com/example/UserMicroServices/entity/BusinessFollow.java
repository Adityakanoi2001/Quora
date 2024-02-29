package com.example.UserMicroServices.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class BusinessFollow {
    @Id
    private String followId;
    private String followerId;
    private String followingId;
    private String connection;
    private Date createdDate;
    private Date updatedDate;
}
