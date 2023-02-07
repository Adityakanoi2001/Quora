package com.example.QuestionsMicroService.Service;

import com.example.QuestionsMicroService.DTO.AnswerInputDto;
import com.example.QuestionsMicroService.DTO.AnswersListOutput;
import com.example.QuestionsMicroService.DTO.PostDto;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.Entities.AnswerEntity;

import java.util.List;

public interface AnswerService
{
    public QuestionInputResponseDto addAnswer(AnswerInputDto answerInputDto);
    public QuestionInputResponseDto upVoteAnswer(String answerId,String userId);
    public QuestionInputResponseDto downVoteAnswer (String answerId,String userId);
    //public AnswersListOutput getAllAnswersByQuestionId(String questionId);
    public void changeflag (String answerId);
    public AnswerEntity getBestAnswer(String questionId);
    public List<AnswerEntity> getAllAnswersByQuestionId(String questionId);
    public List<AnswerEntity> getAllAnswers(String questionId); //For Admin Side
    public List<AnswerEntity> getAllAnswerbyUserId (String answerUserId);

    //RAM METHOD PROBLEM FORBIDDEN 403
    public QuestionInputResponseDto addAnswer1 (String answerUserId,String questionId,String answerBody);

}
