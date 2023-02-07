package com.example.QuestionsMicroService.DTO;

import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Entities.Comment;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class PostDto
{
    private String questionBody;
    private String questionUserId;
    private AnswerEntity answerEntity;
    private String questionId;

}
