package com.example.UserMicroServices.dto;


import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userName;
    private Boolean isPublic;
    private String img;
    private String bio;

}
