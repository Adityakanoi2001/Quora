package com.example.QuestionsMicroService.Service.ServiceImpl;


import com.example.QuestionsMicroService.DTO.*;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Feign.UserFinge;
import com.example.QuestionsMicroService.Repo.AnswerRepo;
import com.example.QuestionsMicroService.Repo.QuestionRepo;
import com.example.QuestionsMicroService.Service.AnswerService;
import com.example.QuestionsMicroService.Service.QuestionServices;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    AnswerRepo answerRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserFinge userFinge;

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public QuestionInputResponseDto addAnswer(AnswerInputDto answerInputDto)
    {
        AnswerEntity answerEntity = new AnswerEntity();
        BeanUtils.copyProperties(answerInputDto, answerEntity);
        answerEntity.setAnswerGiverName(userFinge.findUserNameByUserId(answerInputDto.getAnswerUserId()));
        answerEntity.setAnswerID(UUID.randomUUID().toString());
        answerEntity.setQuestionBody(questionRepo.findQuestionBodyByQuestionId(answerInputDto.getQuestionId()));
        answerRepo.save(answerEntity);
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("Answer Added");
        return questionInputResponseDto;
    }

    @Override
    public QuestionInputResponseDto upVoteAnswer(String answerId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(answerId));
        List<AnswerEntity> answerEntityList = mongoTemplate.find(query, AnswerEntity.class);
        Integer newUpvote = answerEntityList.get(0).getVote() + 1;
        Update update = new Update();
        update.set("vote", newUpvote);
        mongoTemplate.findAndModify(query, update, AnswerEntity.class);
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("Upvoted");
        questionInputResponseDto.setMessage(answerEntityList.get(0).getVote().toString());
        userFinge.increaseScoreByOne(answerEntityList.get(0).getAnswerUserId());
        return questionInputResponseDto;
    }

    @Override
    public QuestionInputResponseDto downVoteAnswer(String answerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(answerId));
        List<AnswerEntity> answerEntityList = mongoTemplate.find(query, AnswerEntity.class);
        Integer newDownvote;
        if (answerEntityList.get(0).getVote() == 0) {
            newDownvote = 0;
        } else {
            newDownvote = answerEntityList.get(0).getVote() - 1;
        }
        Update update = new Update();
        update.set("vote", newDownvote);
        mongoTemplate.findAndModify(query, update, AnswerEntity.class);
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("DownVoted");
        questionInputResponseDto.setMessage(answerEntityList.get(0).getVote().toString());
        userFinge.decreaseByOne(answerEntityList.get(0).getAnswerUserId());
        return questionInputResponseDto;
    }

    @Override
    public AnswersListOutput getAllAnswersByQuestionId(String questionId)
    {

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        AnswersListOutput answersListOutput = new AnswersListOutput();
        for (AnswerEntity answer : answerRepo.findAllByQuestionId(questionId))
        {
            if ((answer.getQuestionId().equals(questionId)) && answer.getGetApprovalFlag().equals(true))
            {
                answerEntityList.add(answer);
            }
        }
        List<AnswerOutput> answerOutputList = new ArrayList<>();
        BeanUtils.copyProperties(answerEntityList,answerOutputList);
        System.out.print(answerOutputList);
        answersListOutput.setAnswerEntityList(answerOutputList);
        return answersListOutput;
    }

    @Override
    public void changeflag(String answerId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(answerId));
        List<AnswerEntity> answerEntityList = mongoTemplate.find(query, AnswerEntity.class);
        boolean newflag;
        if(answerEntityList.get(0).getGetApprovalFlag()==true)
        {
            newflag = false;
        }
        else
        {
            newflag = true;
        }
        Update update = new Update();
        update.set("getApprovalFlag",newflag);
        mongoTemplate.findAndModify(query,update,AnswerEntity.class);
    }

    @Override
    public AnswerEntity getBestAnswer(String questionId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("questionId").is(questionId));
        List<AnswerEntity> answerEntityList = mongoTemplate.find(query, AnswerEntity.class);
        AnswerEntity answerEntity = new AnswerEntity();
        for(int i=0 ; i<answerEntityList.size();i++)
        {
            int max =0;
            if(answerEntityList.get(i).getVote()>=max && answerEntityList.get(i).getGetApprovalFlag()==true)
            {
                answerEntity = answerEntityList.get(i);
                max = answerEntityList.get(i).getVote();
            }
        }
        return answerEntity;
    }





}


