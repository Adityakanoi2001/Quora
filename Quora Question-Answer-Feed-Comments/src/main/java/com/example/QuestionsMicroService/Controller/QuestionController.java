package com.example.QuestionsMicroService.Controller;


import com.example.QuestionsMicroService.DTO.PostDto;
import com.example.QuestionsMicroService.DTO.QuestionInputDto;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.DTO.QuestionListReturnDto;
import com.example.QuestionsMicroService.Entities.QuestionEntity;
import com.example.QuestionsMicroService.Service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins={"http://10.20.5.69:8081","http://10.20.5.66:8082","http://10.20.5.69:8080"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/question")
public class QuestionController
{
    @Autowired
    QuestionServices questionServices;

    @PostMapping  (value = "/addQuestion")
    public ResponseEntity<QuestionInputResponseDto> addQuestion (@RequestBody QuestionInputDto questionInputDto)
    {
        return new ResponseEntity(questionServices.addQuestion(questionInputDto),HttpStatus.OK);
    }

    //FOR UI TEAM SAME METHOD
    @GetMapping (value = "/param")
    public ResponseEntity<QuestionInputResponseDto> addquestionparam(@RequestParam String questionUserID, @RequestParam  String questionBody ,@RequestParam  String questionCategory)
    {
        return new ResponseEntity(questionServices.addQuestion1(questionUserID,questionBody,questionCategory),HttpStatus.OK);
    }


    @DeleteMapping(value = "/deleteQuestionById/{questionId}")
    public  ResponseEntity<QuestionInputResponseDto> deleteQuestion(@PathVariable("questionId") String questionId)
    {
        return new ResponseEntity(questionServices.deleteQuestion(questionId),HttpStatus.OK);
    }

    @GetMapping(value = "/getQuestionByQuestionUserId/{questionUserID}")
    public ResponseEntity<QuestionListReturnDto> getQuestionsByQuestionUserId (@PathVariable("questionUserID") String questionUserID)
    {
        return new ResponseEntity(questionServices.getQuestionById(questionUserID),HttpStatus.OK);
    }

    @GetMapping(value = "/getPosts")
    public ResponseEntity<List<PostDto>> getQuestionsByQuestionUserId ()
    {
        return new ResponseEntity(questionServices.getPost(),HttpStatus.OK);
    }

    @GetMapping(value = "/getQuestionsByCategeory/{categeory}")
    public ResponseEntity<List<QuestionEntity>> getQuestionsByCategeory(@PathVariable("categeory") String categeory)
    {
        return new ResponseEntity(questionServices.questionByCategeory(categeory),HttpStatus.OK);
    }

    @GetMapping(value = "/searchQuestion/{questionsearch}")
    public ResponseEntity<List<QuestionEntity>> getSearchResults(@PathVariable("questionsearch") String questionsearch)
    {
        return new ResponseEntity(questionServices.questionSearch(questionsearch),HttpStatus.OK);
    }

    //for Admin
    @GetMapping(value = "/getAllQuestions")
    public ResponseEntity<List<QuestionEntity>> getAllQuestions()
    {
        return new ResponseEntity(questionServices.sendAllQuestions(),HttpStatus.OK);
    }

    @GetMapping(value = "/getPostsbyUserId/{userId}")
    public ResponseEntity<List<PostDto>> getUserSpecificFeed(@PathVariable("userId") String userId)
    {
        return new ResponseEntity(questionServices.getPostByUser(userId),HttpStatus.OK);
    }

}
