package com.example.QuestionsMicroService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//dto to throw FE respose as question added and question unique id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInputResponseDto
{
    private String response;
    private String message;
}



