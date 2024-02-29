package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class QuestionInputResponse{

	@SerializedName("response")
	private String response;

	@SerializedName("message")
	private String message;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"QuestionInputResponse{" + 
			"response = '" + response + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}