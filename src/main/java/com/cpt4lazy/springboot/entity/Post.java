package com.cpt4lazy.springboot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
public abstract class Post {
	
	@Transient
    public static final String SEQUENCE_NAME = "post";
	
	@Id
	public long id;
	
	private String postText;
	
	public Post() {}
	public Post(String postText) {
		this.postText = postText;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		sb.append(postText);
		sb.append("]");
		
		return sb.toString();
	}
}
