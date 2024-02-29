package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class BusinessRegistration{

	@SerializedName("corporateName")
	private String corporateName;

	@SerializedName("platformId")
	private String platformId;

	public void setCorporateName(String corporateName){
		this.corporateName = corporateName;
	}

	public String getCorporateName(){
		return corporateName;
	}

	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}

	public String getPlatformId(){
		return platformId;
	}

	@Override
 	public String toString(){
		return 
			"BusinessRegistration{" + 
			"corporateName = '" + corporateName + '\'' + 
			",platformId = '" + platformId + '\'' + 
			"}";
		}
}