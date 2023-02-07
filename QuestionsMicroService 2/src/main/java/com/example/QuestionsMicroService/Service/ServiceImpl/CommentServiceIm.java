package com.example.QuestionsMicroService.Service.ServiceImpl;

import com.example.QuestionsMicroService.DTO.CommentListDto;
import com.example.QuestionsMicroService.DTO.DaDto;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Entities.Comment;
import com.example.QuestionsMicroService.Entities.QuestionEntity;
import com.example.QuestionsMicroService.Feign.DAFinge;
import com.example.QuestionsMicroService.Feign.UserFinge;
import com.example.QuestionsMicroService.Repo.AnswerRepo;
import com.example.QuestionsMicroService.Repo.CommentRepository;
import com.example.QuestionsMicroService.Repo.QuestionRepo;
import com.example.QuestionsMicroService.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceIm implements CommentService
{

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AnswerRepo answerRepository;

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    DAFinge daFinge;

    @Autowired
    UserFinge userFinge;

    @Override
    public CommentListDto getcommentbyparent(String parentId)
    {
        List<Comment> list=commentRepository.findAllByParentId(parentId);
        CommentListDto commentListDto = new CommentListDto();
        commentListDto.setCommentList(list);
        return  commentListDto;
    }

    @Override
    public Comment save(Comment comment)
    {
        comment.setUserName(userFinge.findUserNameByUserId(comment.getUserId()));
        comment.setCommenterImage(userFinge.findUserImgByUserId(comment.getUserId()));
        Comment comment1= commentRepository.save(comment);

        if(comment1.getParentId().equals(null))
        {
            Optional<AnswerEntity> optionalAnswer =answerRepository.findById(comment1.getAnswerId());
            AnswerEntity answer=optionalAnswer.get();
            List<String> list=answer.getCommentsList();
            if(list==null)
            {
                List<String> list1=new ArrayList<>();
                list1.add(comment1.getCommentId());
                answer.setCommentsList(list1);
                answerRepository.save(answer);
            }
            else
                {
                    list.add(comment1.getCommentId());
                    answer.setCommentsList(list);
                    answerRepository.save(answer);
                }
        }

        //DATA ANALYTICS COMMENTS
        DaDto daDto = new DaDto();
        daDto.setActionTime(LocalDateTime.now());
        daDto.setUserId(comment.getUserId());
        daDto.setPostId(comment.getAnswerId());

        String AnswerID = comment.getAnswerId();
        Optional<AnswerEntity> answerEntity;
        answerEntity = answerRepository.findById(AnswerID);
        String questionId = answerEntity.get().getQuestionId();

        Optional<QuestionEntity> questionEntity;
        questionEntity = questionRepo.findById(questionId);
        String categeory = questionEntity.get().getQuestionCategory();
        List<String> categeories = new ArrayList<>();
        categeories.add(categeory);
        daDto.setCategories(categeories);
        daDto.setActionType("comment");
        daDto.setPlatformId("quora");
        daFinge.sendData(daDto);


        return  comment1;
    }

    @Override
    public CommentListDto getcommentbyanswer(String answerId)
    {
        List<Comment> list=commentRepository.findAllByAnswerId(answerId);
        List<Comment> list1=new ArrayList<>();
        for(Comment comment:list)
        {
            if(comment.getParentId().equals("null") || comment.getParentId().equals(""))
                list1.add(comment);
        }
        CommentListDto commentListDto = new CommentListDto();
        commentListDto.setCommentList(list1);
        return  commentListDto;
    }
}
