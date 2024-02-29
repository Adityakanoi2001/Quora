package com.example.UserMicroServices.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Post {
    @Id
    private String answerID;
    private String answerUserId;
    private String answerGiverName;
    private String questionId;
    private String questionBody;
    private String answerBody;
    private Integer vote = 0;
    private List<String> upVotersList=new ArrayList<>();
    private List<String> downVotersList = new ArrayList<>();
    private Boolean getApprovalFlag=true;
    private List<String> commentsList;
}
