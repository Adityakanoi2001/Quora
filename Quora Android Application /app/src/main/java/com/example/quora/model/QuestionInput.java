package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class QuestionInput{

	@SerializedName("questionCategory")
	private String questionCategory;

	@SerializedName("questionBody")
	private String questionBody;

	@SerializedName("questionUserID")
	private String questionUserID;

	public void setQuestionCategory(String questionCategory){
		this.questionCategory = questionCategory;
	}

	public String getQuestionCategory(){
		return questionCategory;
	}

	public void setQuestionBody(String questionBody){
		this.questionBody = questionBody;
	}

	public String getQuestionBody(){
		return questionBody;
	}

	public void setQuestionUserID(String questionUserID){
		this.questionUserID = questionUserID;
	}

	public String getQuestionUserID(){
		return questionUserID;
	}

	@Override
 	public String toString(){
		return 
			"QuestionInput{" + 
			"questionCategory = '" + questionCategory + '\'' + 
			",questionBody = '" + questionBody + '\'' + 
			",questionUserID = '" + questionUserID + '\'' + 
			"}";
		}
}