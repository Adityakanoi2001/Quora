package com.example.quora.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("User")
	private List<UserItem> user;

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"user = '" + user + '\'' + 
			"}";
		}
}