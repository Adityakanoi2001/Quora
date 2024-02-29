package com.example.QuestionsMicroService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;


// dto to take input from the user for the adding of question
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInputDto
{

    private String questionUserID;  //QuestionUserID
    private String questionBody; //Question text
    private String questionCategory; //Question Categeory

}
