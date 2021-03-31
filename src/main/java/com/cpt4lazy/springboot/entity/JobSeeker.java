package com.cpt4lazy.springboot.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userroles")
@TypeAlias("jobseeker")
public class JobSeeker extends UserRole{
	
	private String preferredJob;
	private String preferredCompany;
	
	
	@PersistenceConstructor
	public JobSeeker(String name, String telephoneNumber, String address, String roleName, String preferredJob, String preferredCompany) {
		super(name, telephoneNumber, address, roleName);
		this.preferredJob = preferredJob;
		this.preferredCompany = preferredCompany;
	}
	
	public String getPreferredJob() {
		return preferredJob;
	}
	public void setPreferredJob(String preferredJob) {
		this.preferredJob = preferredJob;
	}
	public String getPreferredCompany() {
		return preferredCompany;
	}
	public void setPreferredCompany(String preferredCompany) {
		this.preferredCompany = preferredCompany;
	}
	
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
		sb.append(preferredJob);
		sb.append(", ");
		sb.append(preferredCompany);
		sb.append("]");
		
		return sb.toString();
	}
	
}
