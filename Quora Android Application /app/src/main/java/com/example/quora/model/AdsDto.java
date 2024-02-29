package com.example.quora.model;

import com.google.gson.annotations.SerializedName;

public class AdsDto {

	@SerializedName("cost")
	private int cost;

	@SerializedName("description")
	private String description;

	@SerializedName("productName")
	private String productName;

	@SerializedName("url")
	private String url;

	public void setCost(int cost){
		this.cost = cost;
	}

	public int getCost(){
		return cost;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Ads{" + 
			"cost = '" + cost + '\'' + 
			",description = '" + description + '\'' + 
			",productName = '" + productName + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}