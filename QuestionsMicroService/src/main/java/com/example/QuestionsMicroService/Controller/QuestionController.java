package com.example.QuestionsMicroService.Controller;


import com.example.QuestionsMicroService.DTO.QuestionInputDto;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.DTO.QuestionListReturnDto;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Entities.QuestionEntity;
import com.example.QuestionsMicroService.Service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://10.20.5.66:8083")
@RestController
@RequestMapping("/question")
public class QuestionController
{
    @Autowired
    QuestionServices questionServices;

    @PostMapping (value = "/addQuestion")
    public ResponseEntity<QuestionInputResponseDto> addQuestion (@RequestBody QuestionInputDto questionInputDto)
    {
        return new ResponseEntity(questionServices.addQuestion(questionInputDto),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteQuestionById/{questionId}")
    public  ResponseEntity<QuestionInputResponseDto> deleteQuestion(@RequestParam String questionId)
    {
        return new ResponseEntity(questionServices.deleteQuestion(questionId),HttpStatus.OK);
    }

    @GetMapping(value = "/getQuestionByQuestionUserId/{questionUserID}")
    public ResponseEntity<QuestionListReturnDto> getQuestionsByQuestionUserId (@PathVariable("questionUserID") String questionUserID)
    {
        return new ResponseEntity(questionServices.getQuestionById(questionUserID),HttpStatus.OK);
    }

//    @GetMapping(value = "/getQuestionsByCategeory/{categoery}")
//    public ResponseEntity<QuestionListReturnDto> getQuestionsByCategeory (@PathVariable("categeory") String categeory)
//    {
//        return new ResponseEntity(questionServices.)
//
//    }

}
