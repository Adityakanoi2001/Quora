package com.example.UserMicroServices.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = Answer.collection_name)
@Setter
@Getter
public class Answer {
    public static final String collection_name="Answer";
    private String answerID;
    private String answerUserId;
    private String questionId;
    private String answerBody;
    private int upVote = 0;
    private int downVote = 0;
    private List<Comment> commentList;
}
