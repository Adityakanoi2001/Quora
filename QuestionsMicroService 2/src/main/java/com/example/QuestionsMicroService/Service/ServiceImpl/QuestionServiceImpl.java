package com.example.QuestionsMicroService.Service.ServiceImpl;

import com.example.QuestionsMicroService.DTO.*;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Entities.QuestionEntity;
import com.example.QuestionsMicroService.Feign.DAFinge;
import com.example.QuestionsMicroService.Feign.UserFinge;
import com.example.QuestionsMicroService.Repo.QuestionRepo;
import com.example.QuestionsMicroService.Service.AnswerService;
import com.example.QuestionsMicroService.Service.QuestionServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class QuestionServiceImpl implements QuestionServices
{
    @Autowired
    QuestionRepo questionRepo;

   @Autowired
   UserFinge userFinge;

   @Autowired
    AnswerService answerService;

   @Autowired
    MongoTemplate mongoTemplate;

   @Autowired
    DAFinge daFinge;



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


        //DATA ANALYTICS DATA
        DaDto daDto = new DaDto();
        daDto.setPostId(questionEntity.getQuestionId());
        daDto.setPlatformId("quora");
        List<String> categories = new ArrayList<>();
        daDto.setActionType("like");
        categories.add(questionInputDto.getQuestionCategory());
        daDto.setCategories(categories);
        daDto.setUserId(questionEntity.getQuestionUserID());
        daFinge.sendData(daDto);
        daDto.setActionTime(LocalDateTime.now());

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
    public QuestionListReturnDto getQuestionById(String questionUserID)
    {
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for (QuestionEntity question : questionRepo.findAllByQuestionUserID(questionUserID))
        {
            if ((question.getQuestionUserID().equals(questionUserID)))
            {
                questionEntityList.add(question);
            }
        }
        QuestionListReturnDto questionListReturnDto = new QuestionListReturnDto();
        questionListReturnDto.setQuestionEntityList(questionEntityList);
        questionListReturnDto.setNoOfQuestionAsked(questionEntityList.size());
        return questionListReturnDto;

    }

    @Override
    public List<PostDto> getPost()
    {
        List<PostDto> postDtoList=new ArrayList<>();
        List<QuestionEntity> questionEntitiesList=questionRepo.findAll();
        for(QuestionEntity q:questionEntitiesList)
        {
            PostDto postDto=new PostDto();
            String quesntionId=q.getQuestionId();
            List<AnswerEntity>answerEntityList=answerService.getAllAnswersByQuestionId(quesntionId);
            String question=q.getQuestionBody();
            if(answerEntityList.size()==0)
                continue;
            AnswerEntity answerEntity=answerService.getBestAnswer(quesntionId);
            postDto.setAnswerEntity(answerEntity);
            postDto.setQuestionBody(question);
            postDto.setQuestionId(q.getQuestionId());
            postDto.setQuestionUserId(q.getQuestionUserID());
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public List<QuestionEntity> questionByCategeory(String category)
    {
        List<QuestionEntity> questionEntityList;
        Pattern regs = Pattern.compile(category, Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        query.addCriteria(Criteria.where("questionCategory").regex(regs));
        questionEntityList = mongoTemplate.find(query,QuestionEntity.class);
        return questionEntityList;
    }

    @Override
    public List<QuestionEntity> questionSearch(String questionBody)
    {
        Pattern regs = Pattern.compile(questionBody, Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        query.addCriteria(Criteria.where("questionBody").regex(regs));
        List<QuestionEntity> questionEntityList = mongoTemplate.find(query,QuestionEntity.class);
        return questionEntityList;
    }



    @Override
    public List<QuestionEntity> sendAllQuestions()
    {
        return questionRepo.findAll();
    }



    // SPECIAL ADD METHOD FOR RAM UI
    public QuestionInputResponseDto addQuestion1(String questionUserID,String questionBody , String questionCategory)
    {

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionId(UUID.randomUUID().toString());
        questionEntity.setQuestionUserID(questionUserID);
        questionEntity.setQuestionBody(questionBody);
        questionEntity.setQuestionCategory(questionCategory);
        questionRepo.save(questionEntity);
        QuestionInputResponseDto response = new QuestionInputResponseDto();
        response.setResponse("SuccessFully Added");
        userFinge.increaseByTen(questionUserID);

        //Da Data
        DaDto daDto = new DaDto();
        daDto.setPostId(questionEntity.getQuestionId());
        daDto.setPlatformId("quora");
        List<String> categories = new ArrayList<>();
        daDto.setActionType("like");
        categories.add(questionCategory);
        daDto.setCategories(categories);
        daDto.setUserId(questionEntity.getQuestionUserID());
        daFinge.sendData(daDto);

        return response;
    }

    public List<PostDto> getPostByUser(String userId)
    {

        List<User> followingDtoList = userFinge.findFollowing(userId);
        List<PostDto> postDtos = new ArrayList<>();

        if(followingDtoList != null && followingDtoList.size()!=0)
        {
            for(int i=0 ; i<followingDtoList.size();i++)
            {
                try
                {
                    List<QuestionEntity> questionEntityList = questionRepo.findAllByQuestionUserID(followingDtoList.get(i).getUserId());
                    String questionBody;
                    AnswerEntity answerEntity;
                    String questionid;

                    if(questionEntityList.size() !=0)
                    {
                        for(int f =0 ; f<questionEntityList.size();f++)
                        {
                            PostDto postDto = new PostDto();
                            questionBody = questionEntityList.get(f).getQuestionBody();
                            questionid = questionEntityList.get(f).getQuestionId();
                            answerEntity = answerService.getBestAnswer(questionid);
                            if(answerEntity.getAnswerID() != null) {
                                postDto.setQuestionBody(questionBody);
                                postDto.setAnswerEntity(answerEntity);
                                postDto.setQuestionId(questionid);
                                postDto.setQuestionUserId(questionEntityList.get(f).getQuestionUserID());
                                postDtos.add(postDto);
                            }
                        }
                    }
                    else
                    {
                        continue;
                    }

                } catch (NullPointerException a)
                {
                    System.out.println("error"+a);
                }
            }
        }

        else
        {
            List<PostDto> internallist = getPost();
            for(PostDto a :internallist)
            {
                postDtos.add(a);
            }
        }
        return postDtos;
    }
}
