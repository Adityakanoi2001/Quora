package com.example.quora.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Business{

	@SerializedName("Business")
	private List<BusinessItem> business;

	public void setBusiness(List<BusinessItem> business){
		this.business = business;
	}

	public List<BusinessItem> getBusiness(){
		return business;
	}

	@Override
 	public String toString(){
		return 
			"Business{" + 
			"business = '" + business + '\'' + 
			"}";
		}
}