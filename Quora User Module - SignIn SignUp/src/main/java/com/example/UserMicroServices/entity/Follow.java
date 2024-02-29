package com.example.UserMicroServices.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Follow {
    @Id
    private String followId;
    private String followerId;
    private String followingId;
    private String connection;
    private Date createdDate;
    private Date updatedDate;
}
