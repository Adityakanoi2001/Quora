package com.example.QuestionsMicroService.Service;

import com.example.QuestionsMicroService.DTO.CommentListDto;
import com.example.QuestionsMicroService.Entities.Comment;

public interface CommentService
{
    Comment save(Comment comment);
    CommentListDto getcommentbyanswer(String answerId);
    CommentListDto getcommentbyparent(String parentId);
}

