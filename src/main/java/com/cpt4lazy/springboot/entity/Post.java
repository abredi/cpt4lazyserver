package com.cpt4lazy.springboot.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
public abstract class Post {
	
	@Transient
    public static final String SEQUENCE_NAME = "post";
	
	@Id
	public long id;
	
	private String postedBy;
	private LocalDate datePosted;
	private String postText;
	

	public Post() {}
	public Post(String postedBy, LocalDate datePosted, String postText) {
		this.postedBy = postedBy;
		this.postText = postText;
		this.datePosted = datePosted;
		
	}
	
	public String getPostedby() {
		return postedBy;
	}
	
	public void setPostedby(String postedby) {
		this.postedBy = postedby;
	}
	
	public LocalDate getDatePosted() {
		return datePosted;
	}
	
	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
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
		sb.append(postedBy);
		sb.append(", ");
		sb.append(postText);
		sb.append(", ");
		sb.append(datePosted);
		sb.append("]");
		
		return sb.toString();
	}
}
