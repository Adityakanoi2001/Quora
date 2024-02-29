package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionEntityListItem implements Serializable {

	@SerializedName("date")
	private String date;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("questionCategory")
	private String questionCategory;

	@SerializedName("questionBody")
	private String questionBody;

	@SerializedName("questionUserID")
	private String questionUserID;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

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
			"QuestionEntityListItem{" + 
			"date = '" + date + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",questionCategory = '" + questionCategory + '\'' + 
			",questionBody = '" + questionBody + '\'' + 
			",questionUserID = '" + questionUserID + '\'' + 
			"}";
		}
}