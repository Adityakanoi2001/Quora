package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostsItem implements Serializable {

	@SerializedName("answerEntity")
	private AnswerEntity answerEntity;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("questionBody")
	private String questionBody;

	public void setAnswerEntity(AnswerEntity answerEntity){
		this.answerEntity = answerEntity;
	}

	public AnswerEntity getAnswerEntity(){
		return answerEntity;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setQuestionBody(String questionBody){
		this.questionBody = questionBody;
	}

	public String getQuestionBody(){
		return questionBody;
	}

	@Override
 	public String toString(){
		return 
			"PostsItem{" + 
			"answerEntity = '" + answerEntity + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",questionBody = '" + questionBody + '\'' + 
			"}";
		}
}