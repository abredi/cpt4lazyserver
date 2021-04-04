package com.cpt4lazy.springboot.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userroles")
@TypeAlias("alumni")
public class Alumni extends UserRole {
	
	private String currentJob;
	private String currentCompany;
	
//	@DBRef
//	private List<Post> post;
	
	@PersistenceConstructor
	public Alumni(String name, String telephoneNumber, String address, String roleName, String currentJob, String currentCompany) {
		super(name, telephoneNumber, address, roleName);
		this.currentJob = currentJob;
		this.currentCompany = currentCompany;
	}
	
	public String getCurrentJob() {
		return currentJob;
	}
	public void setCurrentJob(String currentJob) {
		this.currentJob = currentJob;
	}
	public String getCurrentCompany() {
		return currentCompany;
	}
	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}
	
//	public List<Post> getPost() {
//		return post;
//	}
//
//	public void setPost(List<Post> post) {
//		this.post = post;
//	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(super.getId());
		sb.append(", ");
		sb.append(super.getName());
		sb.append(", ");
		sb.append(super.getTelephoneNumber());
		sb.append(", ");
		sb.append(super.getAddress());
		sb.append(", ");
		sb.append(super.getRoleName());
		sb.append(", ");
		sb.append(currentJob);
		sb.append(", ");
		sb.append(currentCompany);
		//sb.append(post);
		sb.append("]");
		
		return sb.toString();
	}
	
}
