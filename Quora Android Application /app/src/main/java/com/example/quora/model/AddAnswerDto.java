package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class AddAnswerDto{

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("answerBody")
	private String answerBody;

	@SerializedName("answerUserId")
	private String answerUserId;

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setAnswerBody(String answerBody){
		this.answerBody = answerBody;
	}

	public String getAnswerBody(){
		return answerBody;
	}

	public void setAnswerUserId(String answerUserId){
		this.answerUserId = answerUserId;
	}

	public String getAnswerUserId(){
		return answerUserId;
	}

	@Override
 	public String toString(){
		return 
			"AddAnswerDto{" + 
			"questionId = '" + questionId + '\'' + 
			",answerBody = '" + answerBody + '\'' + 
			",answerUserId = '" + answerUserId + '\'' + 
			"}";
		}
}