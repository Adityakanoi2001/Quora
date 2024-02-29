package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class CommentListItem{

	@SerializedName("answerId")
	private String answerId;

	@SerializedName("date")
	private String date;

	@SerializedName("commentBody")
	private String commentBody;

	@SerializedName("commentId")
	private String commentId;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;

	@SerializedName("parentId")
	private String parentId;


	@SerializedName("commenterImage")
	private String commenterImage;

	public String getCommenterImage() {
		return commenterImage;
	}

	public void setCommenterImage(String commenterImage) {
		this.commenterImage = commenterImage;
	}

	public void setAnswerId(String answerId){
		this.answerId = answerId;
	}

	public String getAnswerId(){
		return answerId;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setCommentBody(String commentBody){
		this.commentBody = commentBody;
	}

	public String getCommentBody(){
		return commentBody;
	}

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return commentId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getParentId(){
		return parentId;
	}

	@Override
 	public String toString(){
		return 
			"CommentListItem{" + 
			"answerId = '" + answerId + '\'' + 
			",date = '" + date + '\'' + 
			",commentBody = '" + commentBody + '\'' + 
			",commentId = '" + commentId + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			",parentId = '" + parentId + '\'' + 
			"}";
		}
}