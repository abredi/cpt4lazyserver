package com.cpt4lazy.springboot.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
@TypeAlias("jobreferalpost")
public class JobReferalPost extends Post{

	private String referal;
	@DBRef
	private List<ReferralRequest> referralRequest;
	
	public JobReferalPost() {super();}
	
	@PersistenceConstructor
	public JobReferalPost(String postedBy, String postText, LocalDate datePosted, String referal) {
		super(postedBy, datePosted, postText);
		this.referal = referal;
		referralRequest = null;
	}
	public String getReferal() {
		return referal;
	}
	public void setReferal(String referal) {
		this.referal = referal;
	}
	
	public List<ReferralRequest> getReferralRequest() {
		return referralRequest;
	}
	public void setReferralRequest(List<ReferralRequest> referralRequest) {
		this.referralRequest = referralRequest;
	}
	
}
