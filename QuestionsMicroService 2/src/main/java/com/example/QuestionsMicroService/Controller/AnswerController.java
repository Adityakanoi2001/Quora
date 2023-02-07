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

//@CrossOrigin(origins={"http://10.20.5.69:8081","http://10.20.5.66:8082","http://10.20.5.69:8080"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
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

    //For Ram
    @GetMapping (value = "/addAnswer1")
    public ResponseEntity<QuestionInputResponseDto> addQuestion1 (@RequestParam String answerUserId, @RequestParam String questionId, @RequestParam String answerBody)
    {
        return new ResponseEntity(answerService.addAnswer1(answerUserId,questionId,answerBody),HttpStatus.OK);
    }


    @PostMapping (value = "/upVote/{answerId}/{userId}")
    public ResponseEntity<QuestionInputResponseDto> upVote (@PathVariable("answerId") String answerId,@PathVariable("userId") String userId)
    {
        return new ResponseEntity(answerService.upVoteAnswer(answerId,userId),HttpStatus.OK);
    }


    //FOR RAM UPVOTE
    @GetMapping (value = "/upVote")
    public ResponseEntity<QuestionInputResponseDto> upVote1 (@RequestParam String answerId,@RequestParam String userId)
    {
        return new ResponseEntity(answerService.upVoteAnswer(answerId,userId),HttpStatus.OK);
    }


    @PostMapping(value = "/downVote/{answerId}/{userId}")
    public ResponseEntity<QuestionInputResponseDto> downVote (@PathVariable("answerId") String answerId,@PathVariable("userId") String userId)
    {
        return new ResponseEntity(answerService.downVoteAnswer(answerId,userId),HttpStatus.OK);
    }


    //FOR RAM DONWVOTE
    @GetMapping(value = "/downVote")
    public ResponseEntity<QuestionInputResponseDto> downVote1 (@RequestParam String answerId,@RequestParam String userId)
    {
        return new ResponseEntity(answerService.downVoteAnswer(answerId,userId),HttpStatus.OK);
    }



    @GetMapping(value = "/getListOfAnswersWithQuestionId/{questionId}")
    public ResponseEntity<AnswersListOutput> getListOfAnswersWithQuestionId (@PathVariable("questionId") String questionId)
    {
        return new ResponseEntity(answerService.getAllAnswersByQuestionId(questionId),HttpStatus.OK);

    }

    // Admin Can Change The Answer Approval
    @PostMapping(value = "changeApprovalFlag/{answerId}")
    public ResponseEntity<String> changeFlag (@PathVariable("answerId") String answerId)
    {
        answerService.changeflag(answerId);
        return new ResponseEntity("Answer Approval Changed",HttpStatus.OK);
    }

    @GetMapping(value = "getBestAnswer/{questionId}")
    public ResponseEntity<AnswerEntity> getBestAnswer(@PathVariable("questionId") String questionId)
    {
        return new ResponseEntity(answerService.getBestAnswer(questionId),HttpStatus.OK);
    }

    @GetMapping(value = "findAllAnswers/{questionId}")
    public ResponseEntity<AnswerEntity> getAllAnswersByQuestionId(@PathVariable("questionId") String questionId)
    {
        return new ResponseEntity(answerService.getAllAnswers(questionId),HttpStatus.OK);
    }

    @GetMapping(value = "getAllAnswerByAnswerUserId/{answerUserId}")
    public ResponseEntity<AnswerEntity> getAllAnswerByAnswerUserId(@PathVariable("answerUserId") String answerUserId)
    {
        return new ResponseEntity(answerService.getAllAnswerbyUserId(answerUserId),HttpStatus.OK);
    }


}
