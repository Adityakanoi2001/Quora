package com.example.QuestionsMicroService.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto
{
    String commentBody;
    String parentId;
    String answerId;
    String UserId;
}
