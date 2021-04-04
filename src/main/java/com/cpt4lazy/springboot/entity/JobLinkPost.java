package com.cpt4lazy.springboot.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
@TypeAlias("joblinkpost")
public class JobLinkPost extends Post{
	
	private String jobURL;
	
	public JobLinkPost() {super();}
	
	@PersistenceConstructor
	public JobLinkPost(String postedBy, String postText, LocalDate datePosted, String jobURL) {
		super(postedBy, datePosted, postText);
		this.jobURL = jobURL;
	}
	
	public String getJobURL() {
		return jobURL;
	}
	public void setJobURL(String jobURL) {
		this.jobURL = jobURL;
	}
	
}
