package com.example.QuestionsMicroService.Repo;

import com.example.QuestionsMicroService.Entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String>
{
    List<Comment> findAllByAnswerId(String AnswerId);
    List<Comment> findAllByParentId(String parentId);
}
