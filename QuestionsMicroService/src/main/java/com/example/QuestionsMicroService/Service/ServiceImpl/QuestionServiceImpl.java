package com.example.QuestionsMicroService.Service.ServiceImpl;

import com.example.QuestionsMicroService.DTO.QuestionInputDto;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.DTO.QuestionListReturnDto;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Entities.QuestionEntity;
import com.example.QuestionsMicroService.Feign.UserFinge;
import com.example.QuestionsMicroService.Repo.QuestionRepo;
import com.example.QuestionsMicroService.Service.QuestionServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionServices
{
    @Autowired
    QuestionRepo questionRepo;

   @Autowired
   UserFinge userFinge;

    @Override
    public QuestionInputResponseDto addQuestion(QuestionInputDto questionInputDto)
    {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(questionInputDto,questionEntity);
        questionRepo.save(questionEntity);
        QuestionInputResponseDto response = new QuestionInputResponseDto();
        response.setResponse("SuccessFully Added");
        userFinge.increaseByTen(questionInputDto.getQuestionUserID());

        return response;
    }

    @Override
    public QuestionInputResponseDto deleteQuestion(String questionId)
    {
        questionRepo.deleteById(questionId);
        QuestionInputResponseDto response = new QuestionInputResponseDto();
        response.setResponse("SuccessFully Deleted The Question !");
        return response;
    }

    @Override
    public QuestionListReturnDto getQuestionById (String questionUserID)
    {
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for (QuestionEntity question : questionRepo.findAllByQuestionUserID(questionUserID))
        {
            if ((question.getQuestionUserID().equals(questionUserID)))
            {
                questionEntityList.add(question);
            }
        }
        QuestionListReturnDto   questionListReturnDto = new QuestionListReturnDto();
        questionListReturnDto.setQuestionEntityList(questionEntityList);
        questionListReturnDto.setNoOfQuestionAsked(questionEntityList.size());
        return questionListReturnDto;

    }





}
