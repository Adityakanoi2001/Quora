package com.example.QuestionsMicroService.DTO;

import com.example.QuestionsMicroService.Entities.AnswerEntity;
import lombok.Data;

import java.util.List;

@Data
public class AnswersListOutput
{
    List<AnswerEntity> answerEntityList;
}
