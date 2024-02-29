package com.example.quora.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BusinessItem{

	@SerializedName("image")
	private String image;

	@SerializedName("moderatorIds")
	private List<String> moderatorIds;

	@SerializedName("businessId")
	private String businessId;

	@SerializedName("businessName")
	private String businessName;

	@SerializedName("bio")
	private String bio;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setModeratorIds(List<String> moderatorIds){
		this.moderatorIds = moderatorIds;
	}

	public List<String> getModeratorIds(){
		return moderatorIds;
	}

	public void setBusinessId(String businessId){
		this.businessId = businessId;
	}

	public String getBusinessId(){
		return businessId;
	}

	public void setBusinessName(String businessName){
		this.businessName = businessName;
	}

	public String getBusinessName(){
		return businessName;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	@Override
 	public String toString(){
		return 
			"BusinessItem{" + 
			"image = '" + image + '\'' + 
			",moderatorIds = '" + moderatorIds + '\'' + 
			",businessId = '" + businessId + '\'' + 
			",businessName = '" + businessName + '\'' + 
			",bio = '" + bio + '\'' + 
			"}";
		}
}