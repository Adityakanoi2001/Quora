package com.example.QuestionsMicroService.DTO;

import com.example.QuestionsMicroService.Entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class AnswerOutput
{
        private String answerUserId;
        private String answerGiverName;
        private String questionId;
        private String answerBody;
        private Integer vote = 0;
        private Boolean getApprovalFlag=true;
        private List<Comment> commentList;

    }

