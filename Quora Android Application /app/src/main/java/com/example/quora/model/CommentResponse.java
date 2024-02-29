package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class CommentResponse{

	@SerializedName("id")
	private String id;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"CommentResponse{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}