package com.example.QuestionsMicroService.Entities;

import com.example.QuestionsMicroService.DTO.DaDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "Comment")
public class Comment
{
    @Id
    String commentId;
    String commentBody;
    String parentId;
    String answerId;
    String UserId;
    String commenterImage;
    Date date=new Date();
    String userName;
}

