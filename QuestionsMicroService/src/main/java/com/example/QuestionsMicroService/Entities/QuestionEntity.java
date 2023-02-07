package com.example.QuestionsMicroService.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class QuestionEntity
{
    @Id
    private String questionId ;          //Auto Generated
    private String questionUserID;      //QuestionUserID
    private String questionBody;       //Question text
    private String questionCategory;  //Question Categeory
    private Date date = new Date();  //date
}
