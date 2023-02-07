package com.example.QuestionsMicroService.DTO;

import lombok.Data;

@Data
public class AnswerInputDto
{
    private String answerUserId;
    private String questionId;
    private String answerBody;
}
