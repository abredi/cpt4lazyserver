package com.cpt4lazy.springboot.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
@TypeAlias("jobreferalpost")
public class JobReferalPost extends Post{

	private String referal;
	
	public JobReferalPost() {super();}
	
	@PersistenceConstructor
	public JobReferalPost(String postText, String referal) {
		super(postText);
		this.referal = referal;
		
	}
	public String getReferal() {
		return referal;
	}
	public void setReferal(String referal) {
		this.referal = referal;
	}
	
}
