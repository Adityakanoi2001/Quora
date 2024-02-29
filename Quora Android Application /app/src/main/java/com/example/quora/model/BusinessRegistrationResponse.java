package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinessRegistrationResponse implements Serializable {

	@SerializedName("corporateId")
	private String corporateId;

	public void setCorporateId(String corporateId){
		this.corporateId = corporateId;
	}

	public String getCorporateId(){
		return corporateId;
	}

	@Override
 	public String toString(){
		return 
			"BusinessRegistrationResponse{" + 
			"corporateId = '" + corporateId + '\'' + 
			"}";
		}
}