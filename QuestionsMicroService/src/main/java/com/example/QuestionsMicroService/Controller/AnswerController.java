package com.example.QuestionsMicroService.Controller;

import com.example.QuestionsMicroService.DTO.AnswerInputDto;
import com.example.QuestionsMicroService.DTO.AnswersListOutput;
import com.example.QuestionsMicroService.DTO.QuestionInputResponseDto;
import com.example.QuestionsMicroService.Entities.AnswerEntity;
import com.example.QuestionsMicroService.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController
{
    @Autowired
    AnswerService answerService;

    @PostMapping(value = "/addAnswer")
    public ResponseEntity<QuestionInputResponseDto> addQuestion (@RequestBody AnswerInputDto answerInputDto)
    {
        return new ResponseEntity(answerService.addAnswer(answerInputDto),HttpStatus.OK);
    }

    @GetMapping(value = "/upVote")
    public ResponseEntity<QuestionInputResponseDto> upVote (@RequestParam String answerId)
    {
        return new ResponseEntity(answerService.upVoteAnswer(answerId),HttpStatus.OK);
    }

    @GetMapping(value = "/downVote")
    public ResponseEntity<QuestionInputResponseDto> downVote (@RequestParam String answerId)
    {
        return new ResponseEntity(answerService.downVoteAnswer(answerId),HttpStatus.OK);
    }

    @GetMapping(value = "/getListOfAnswersWithQuestionId/{questionId}")
    public ResponseEntity<AnswersListOutput> getListOfAnswersWithQuestionId (@PathVariable String questionId)
    {
        return new ResponseEntity(answerService.getAllAnswersByQuestionId(questionId),HttpStatus.OK);

    }

    // Admin Can Change The Answer Approval
    @GetMapping(value = "changeApprovalFlag/{answerId}")
    public ResponseEntity<String> changeFlag (@PathVariable String answerId)
    {
        answerService.changeflag(answerId);
        return new ResponseEntity("Answer Approval Changed",HttpStatus.OK);
    }

    @GetMapping(value = "getBestAnswer/{questionId}")
    public ResponseEntity<AnswerEntity> getBestAnswer(@PathVariable String questionId)
    {
        return new ResponseEntity(answerService.getBestAnswer(questionId),HttpStatus.OK);
    }




}
