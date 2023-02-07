package com.example.QuestionsMicroService.Service;

import com.example.QuestionsMicroService.DTO.QuestionInputDto;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.DTO.QuestionListReturnDto;
import com.example.QuestionsMicroService.Entities.QuestionEntity;

import java.util.List;

public interface QuestionServices
{
    public QuestionInputResponseDto addQuestion (QuestionInputDto questionInputDto);
    public QuestionInputResponseDto deleteQuestion (String questionId);
    public QuestionListReturnDto getQuestionById (String questionUserID);

}
