package com.example.QuestionsMicroService.Service;

import com.example.QuestionsMicroService.DTO.PostDto;
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
    public List<PostDto> getPost();
    public List<QuestionEntity> questionByCategeory (String category);
    public List<QuestionEntity> questionSearch(String questionBody);
    //public List<PostDto>  getPostByUser(String userId);
    public List<QuestionEntity> sendAllQuestions();
    public QuestionInputResponseDto addQuestion1 (String questionUserID,String questionBody , String questionCategory);// for UI TEAM
    public List<PostDto>  getPostByUser(String userId);

}
