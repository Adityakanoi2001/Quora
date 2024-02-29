package com.example.quora.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Questions implements Serializable {

	@SerializedName("questionEntityList")
	private List<QuestionEntityListItem> questionEntityList;

	@SerializedName("noOfQuestionAsked")
	private int noOfQuestionAsked;

	public void setQuestionEntityList(List<QuestionEntityListItem> questionEntityList){
		this.questionEntityList = questionEntityList;
	}

	public List<QuestionEntityListItem> getQuestionEntityList(){
		return questionEntityList;
	}

	public void setNoOfQuestionAsked(int noOfQuestionAsked){
		this.noOfQuestionAsked = noOfQuestionAsked;
	}

	public int getNoOfQuestionAsked(){
		return noOfQuestionAsked;
	}

	@Override
 	public String toString(){
		return 
			"Questions{" + 
			"questionEntityList = '" + questionEntityList + '\'' + 
			",noOfQuestionAsked = '" + noOfQuestionAsked + '\'' + 
			"}";
		}
}