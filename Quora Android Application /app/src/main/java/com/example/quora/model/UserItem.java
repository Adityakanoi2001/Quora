package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class UserItem{

	@SerializedName("score")
	private int score;

	@SerializedName("img")
	private String img;

	@SerializedName("bio")
	private String bio;

	@SerializedName("isPublic")
	private boolean isPublic;

	@SerializedName("classification")
	private String classification;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setIsPublic(boolean isPublic){
		this.isPublic = isPublic;
	}

	public boolean isIsPublic(){
		return isPublic;
	}

	public void setClassification(String classification){
		this.classification = classification;
	}

	public String getClassification(){
		return classification;
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

	@Override
 	public String toString(){
		return 
			"UserItem{" + 
			"score = '" + score + '\'' + 
			",img = '" + img + '\'' + 
			",bio = '" + bio + '\'' + 
			",isPublic = '" + isPublic + '\'' + 
			",classification = '" + classification + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}