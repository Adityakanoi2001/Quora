package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddCommentDto implements Serializable {

	@SerializedName("answerId")
	private String answerId;

	@SerializedName("commentBody")
	private String commentBody;

	@SerializedName("userId")
	private String userId;

	@SerializedName("parentId")
	private String parentId;

	public void setAnswerId(String answerId){
		this.answerId = answerId;
	}

	public String getAnswerId(){
		return answerId;
	}

	public void setCommentBody(String commentBody){
		this.commentBody = commentBody;
	}

	public String getCommentBody(){
		return commentBody;
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
			"AddCommentDto{" + 
			"answerId = '" + answerId + '\'' + 
			",commentBody = '" + commentBody + '\'' + 
			",userId = '" + userId + '\'' + 
			",parentId = '" + parentId + '\'' + 
			"}";
		}
}