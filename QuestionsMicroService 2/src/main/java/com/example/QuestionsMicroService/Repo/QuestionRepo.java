package com.example.QuestionsMicroService.Repo;

import com.example.QuestionsMicroService.Entities.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends MongoRepository<QuestionEntity,String>
{
    List<QuestionEntity> findAllByQuestionUserID (String questionUserID);
    QuestionEntity findQuestionBodyByQuestionId(String questionId);
}
