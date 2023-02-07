package com.example.QuestionsMicroService.DTO;

import com.example.QuestionsMicroService.Entities.QuestionEntity;
import lombok.Data;

import java.util.List;


//dto for will return the no of question asked by a user and its count
@Data
public class QuestionListReturnDto
{
    List<QuestionEntity> questionEntityList;
    Integer noOfQuestionAsked;
}
