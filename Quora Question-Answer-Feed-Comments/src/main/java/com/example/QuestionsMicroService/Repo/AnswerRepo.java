package com.example.QuestionsMicroService.Repo;

import com.example.QuestionsMicroService.Entities.AnswerEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends MongoRepository<AnswerEntity,String>
{
    List<AnswerEntity> findAllByQuestionId(String QuestionId);
    List<AnswerEntity> findAllByAnswerUserId(String answerUserId);

}
