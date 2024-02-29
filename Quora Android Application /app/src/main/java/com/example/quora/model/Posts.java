package com.example.quora.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Posts implements Serializable {

	@SerializedName("Posts")
	private List<PostsItem> posts;

	public void setPosts(List<PostsItem> posts){
		this.posts = posts;
	}

	public List<PostsItem> getPosts(){
		return posts;
	}

	@Override
 	public String toString(){
		return 
			"Posts{" + 
			"posts = '" + posts + '\'' + 
			"}";
		}
}