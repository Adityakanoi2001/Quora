package com.example.QuestionsMicroService.Service.ServiceImpl;


import com.example.QuestionsMicroService.DTO.*;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Entities.QuestionEntity;
import com.example.QuestionsMicroService.Feign.DAFinge;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    DAFinge daFinge;


    @Override
    public QuestionInputResponseDto addAnswer(AnswerInputDto answerInputDto)
    {
        AnswerEntity answerEntity = new AnswerEntity();
        BeanUtils.copyProperties(answerInputDto, answerEntity);
        answerEntity.setAnswerGiverName(userFinge.findUserNameByUserId(answerInputDto.getAnswerUserId()));
        answerEntity.setAnswerGiverImage(userFinge.findUserImgByUserId(answerInputDto.getAnswerUserId()));
        answerEntity.setAnswerID(,);
        QuestionEntity questionEntity=questionRepo.findQuestionBodyByQuestionId(answerInputDto.getQuestionId());
        answerEntity.setQuestionBody(questionEntity.getQuestionBody());
        answerRepo.save(answerEntity);
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("Answer Added");
        return questionInputResponseDto;
    }



    @Override
    public QuestionInputResponseDto upVoteAnswer(String answerId,String userId)
    {
        List<String> upvoterList=new ArrayList<>();
        List<String> downvoterList=new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(answerId));
        List<AnswerEntity> answerEntityList = mongoTemplate.find(query, AnswerEntity.class);

        upvoterList=answerEntityList.get(0).getUpVotersList();
        downvoterList=answerEntityList.get(0).getDownVotersList();

        if(upvoterList==null)
            upvoterList=new ArrayList<>();
        if(downvoterList==null)
            downvoterList=new ArrayList<>();

        if(downvoterList.contains(userId))
        {
            Integer vote=answerEntityList.get(0).getVote();
            answerEntityList.get(0).setVote(vote+1);
            downvoterList.remove(userId);
            answerRepo.save(answerEntityList.get(0));
        }
        else if(upvoterList.contains(userId))
        {
            Integer vote=answerEntityList.get(0).getVote();
            answerEntityList.get(0).setVote(vote-1);
            upvoterList.remove(userId);
            answerRepo.save(answerEntityList.get(0));
        }
        else
        {
            Integer vote=answerEntityList.get(0).getVote();
            answerEntityList.get(0).setVote(vote+1);
            upvoterList.add(userId);
            answerRepo.save(answerEntityList.get(0));
        }
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("Upvoted");
        questionInputResponseDto.setMessage(answerEntityList.get(0).getVote().toString());
        userFinge.increaseScoreByOne(answerEntityList.get(0).getAnswerUserId());


        //DATA ANALYTICS
        DaDto daDto = new DaDto();
        daDto.setPostId(answerEntityList.get(0).getQuestionId());
        daDto.setPlatformId("quora");
        List<String> categories = new ArrayList<>();
        daDto.setActionType("like");
        Optional<QuestionEntity> questionEntity = questionRepo.findById(answerEntityList.get(0).getQuestionId());
        String cat = questionEntity.get().getQuestionCategory();
        categories.add(cat);
        daDto.setActionTime(LocalDateTime.now());
        daDto.setCategories(categories);
        daDto.setUserId(userId);
        daFinge.sendData(daDto);


        return questionInputResponseDto;
    }

    @Override
    public QuestionInputResponseDto downVoteAnswer(String answerId,String userId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(answerId));
        List<AnswerEntity> answerEntityList = mongoTemplate.find(query, AnswerEntity.class);


        List<String> upvoterList=answerEntityList.get(0).getUpVotersList();
        List<String> downvoterList=answerEntityList.get(0).getDownVotersList();

        if(upvoterList.contains(userId))
        {
            Integer vote=answerEntityList.get(0).getVote();
            if(vote==0)
                vote=1;
            answerEntityList.get(0).setVote(vote-1);
            upvoterList.remove(userId);
            answerRepo.save(answerEntityList.get(0));
        }
        else if(downvoterList.contains(userId))
        {
            Integer vote=answerEntityList.get(0).getVote();
            answerEntityList.get(0).setVote(vote+1);
            downvoterList.remove(userId);
            answerRepo.save(answerEntityList.get(0));
        }
        else
        {
            Integer vote=answerEntityList.get(0).getVote();
            if(vote==0)
                vote=1;
            answerEntityList.get(0).setVote(vote-1);
            downvoterList.add(userId);
            answerRepo.save(answerEntityList.get(0));
        }
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("DownVoted");
        questionInputResponseDto.setMessage(answerEntityList.get(0).getVote().toString());
        userFinge.decreaseByOne(answerEntityList.get(0).getAnswerUserId());


        //Da Data
        DaDto daDto = new DaDto();
        daDto.setPostId(answerId);
        daDto.setPlatformId("quora");
        List<String> categories = new ArrayList<>();
        daDto.setActionType("dislike");
        Optional<QuestionEntity> questionEntity = questionRepo.findById(answerEntityList.get(0).getQuestionId());
        String cat = questionEntity.get().getQuestionCategory();
        categories.add(cat);
        daDto.setActionTime(LocalDateTime.now());
        daDto.setCategories(categories);
        daDto.setUserId(userId);
        daFinge.sendData(daDto);


        return questionInputResponseDto;
    }


    @Override
    public List<AnswerEntity> getAllAnswersByQuestionId(String questionId)
    {

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (AnswerEntity answer : answerRepo.findAllByQuestionId(questionId))
        {
            if ((answer.getQuestionId().equals(questionId)) && answer.getGetApprovalFlag().equals(true))
            {
                answerEntityList.add(answer);
            }
        }
        return answerEntityList;
    }

    @Override
    public List<AnswerEntity> getAllAnswers(String questionId)
    {
        return answerRepo.findAllByQuestionId(questionId);
    }

    @Override
    public List<AnswerEntity> getAllAnswerbyUserId(String answerUserId)
    {
        return answerRepo.findAllByAnswerUserId(answerUserId);
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
        int max =0;
        for(int i=0 ; i<answerEntityList.size();i++)
        {

            if(answerEntityList.get(i).getVote()>=max && answerEntityList.get(i).getGetApprovalFlag()==true)
            {
                answerEntity = answerEntityList.get(i);
                max = answerEntityList.get(i).getVote();
            }
        }
        return answerEntity;
    }


    /// SPECIAL ADD FUNCTION FOR RAM UI
    @Override
    public QuestionInputResponseDto addAnswer1(String answerUserId, String questionId, String answerBody)
    {

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswerUserId(answerUserId);
        answerEntity.setAnswerGiverName(userFinge.findUserNameByUserId(answerUserId));
        answerEntity.setAnswerGiverImage(userFinge.findUserImgByUserId(answerUserId));
        answerEntity.setAnswerBody(answerBody);
        answerEntity.setQuestionId(questionId);
        answerEntity.setAnswerID(UUID.randomUUID().toString());
        QuestionEntity questionEntity=questionRepo.findQuestionBodyByQuestionId(questionId);
        answerEntity.setQuestionBody(questionEntity.getQuestionBody());
        answerRepo.save(answerEntity);
        QuestionInputResponseDto questionInputResponseDto = new QuestionInputResponseDto();
        questionInputResponseDto.setResponse("Answer Added");
        return questionInputResponseDto;
    }

}


