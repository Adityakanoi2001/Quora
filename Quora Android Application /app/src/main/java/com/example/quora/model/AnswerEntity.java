package com.example.quora.model;

import android.widget.LinearLayout;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AnswerEntity implements Serializable {

	@SerializedName("answerID")
	private String answerID;

	@SerializedName("commentList")
	private Object commentList;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("getApprovalFlag")
	private boolean getApprovalFlag;

	@SerializedName("answerGiverName")
	private String answerGiverName;

	@SerializedName("answerBody")
	private String answerBody;

	@SerializedName("answerUserId")
	private String answerUserId;

	@SerializedName("questionBody")
	private String questionBody;

	@SerializedName("vote")
	private int vote;

	@SerializedName("downVotersList")
	private List<String> downVotersList;

	@SerializedName("upVotersList")
	private List<String> upVotersList;

	@SerializedName("answerGiverImage")
	private String answerGiverImage;

	public String getAnswerGiverImage() {
		return answerGiverImage;
	}

	public void setAnswerGiverImage(String answerGiverImage) {
		this.answerGiverImage = answerGiverImage;
	}

	public List<String> getDownVotersList() {
		return downVotersList;
	}

	public void setDownVotersList(List<String> downVotersList) {
		this.downVotersList = downVotersList;
	}

	public List<String> getUpVotersList() {
		return upVotersList;
	}



	public void setUpVotersList(List<String> upVotersList) {
		this.upVotersList = upVotersList;
	}

	public void setAnswerID(String answerID){
		this.answerID = answerID;
	}

	public String getAnswerID(){
		return answerID;
	}

	public void setCommentList(Object commentList){
		this.commentList = commentList;
	}

	public Object getCommentList(){
		return commentList;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setGetApprovalFlag(boolean getApprovalFlag){
		this.getApprovalFlag = getApprovalFlag;
	}

	public boolean isGetApprovalFlag(){
		return getApprovalFlag;
	}

	public void setAnswerGiverName(String answerGiverName){
		this.answerGiverName = answerGiverName;
	}

	public String getAnswerGiverName(){
		return answerGiverName;
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

	public void setQuestionBody(String questionBody){
		this.questionBody = questionBody;
	}

	public String getQuestionBody(){
		return questionBody;
	}

	public void setVote(int vote){
		this.vote = vote;
	}

	public int getVote(){
		return vote;
	}

	@Override
 	public String toString(){
		return 
			"AnswerEntity{" + 
			"answerID = '" + answerID + '\'' + 
			",commentList = '" + commentList + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",getApprovalFlag = '" + getApprovalFlag + '\'' + 
			",answerGiverName = '" + answerGiverName + '\'' + 
			",answerBody = '" + answerBody + '\'' + 
			",answerUserId = '" + answerUserId + '\'' + 
			",questionBody = '" + questionBody + '\'' + 
			",vote = '" + vote + '\'' + 
			"}";
		}
}