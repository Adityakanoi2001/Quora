package com.example.quora.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShowComments{

	@SerializedName("commentList")
	private List<CommentListItem> commentList;

	public void setCommentList(List<CommentListItem> commentList){
		this.commentList = commentList;
	}

	public List<CommentListItem> getCommentList(){
		return commentList;
	}

	@Override
 	public String toString(){
		return 
			"ShowComments{" + 
			"commentList = '" + commentList + '\'' + 
			"}";
		}
}