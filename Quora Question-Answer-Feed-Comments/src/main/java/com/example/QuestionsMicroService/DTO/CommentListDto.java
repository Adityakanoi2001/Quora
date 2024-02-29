package com.example.QuestionsMicroService.DTO;

import com.example.QuestionsMicroService.Entities.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CommentListDto
{
    List<Comment> commentList;
}
