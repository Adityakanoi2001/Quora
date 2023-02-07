package com.example.QuestionsMicroService.Service;

import com.example.QuestionsMicroService.DTO.AnswerInputDto;
import com.example.QuestionsMicroService.DTO.AnswersListOutput;
import com.example.QuestionsMicroService.DTO.PostDto;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.Entities.AnswerEntity;

public interface AnswerService
{
    public QuestionInputResponseDto addAnswer(AnswerInputDto answerInputDto);
    public QuestionInputResponseDto upVoteAnswer(String answerId);
    public QuestionInputResponseDto downVoteAnswer (String answerId);
    public AnswersListOutput getAllAnswersByQuestionId(String questionId);
    public void changeflag (String answerId);
    public AnswerEntity getBestAnswer(String questionId);

}
