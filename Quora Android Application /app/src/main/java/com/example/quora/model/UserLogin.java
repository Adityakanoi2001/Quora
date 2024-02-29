package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class UserLogin{

	@SerializedName("password")
	private String password;

	@SerializedName("username")
	private String username;

	@SerializedName("platformId")
	private String platformId;

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"UserLogin{" + 
			"password = '" + password + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}