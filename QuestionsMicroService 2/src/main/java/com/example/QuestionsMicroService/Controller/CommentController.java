package com.example.QuestionsMicroService.Controller;

import com.example.QuestionsMicroService.DTO.CommentDto;
import com.example.QuestionsMicroService.DTO.CommentListDto;
import com.example.QuestionsMicroService.DTO.ResponseString;
import com.example.QuestionsMicroService.Entities.Comment;
import com.example.QuestionsMicroService.Service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping(value = "/addcomment")
    public ResponseString addcomment(@RequestBody CommentDto commentDto)
    {
        Comment comment =new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        Comment comment1=commentService.save(comment);
        String id=comment1.getCommentId();
        ResponseString responseString=new ResponseString();
        responseString.setId(id);
        return responseString;
    }

    @GetMapping(value = "/getcommentbyanswer/{answerId}")
    public CommentListDto getcommentbyanswer(@PathVariable("answerId") String answerId)
    {
        return  commentService.getcommentbyanswer(answerId);
    }

    @GetMapping(value = "/getcommentbyparent/{parentId}")
    public CommentListDto getcommentbyparent(@PathVariable("parentId") String parentId)
    {
        return  commentService.getcommentbyparent(parentId);
    }
}
