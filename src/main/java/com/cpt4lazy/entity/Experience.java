package com.cpt4lazy.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cpt4lazy.configs.LocalDateDeserializer;
import com.cpt4lazy.configs.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Document(collection = "experience")
public class Experience {
	
	@Transient
    public static final String SEQUENCE_NAME = "experience";
	
	@Id
	private long id;
	
	
	private String company;
	private String position;
	private String responsibility;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fromDate;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate toDate;
	
	public Experience (){}

	public Experience(String company, String position, String responsibility, LocalDate fromDate, LocalDate toDate) {
		this.company = company;
		this.position = position;
		this.responsibility = responsibility;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(company);
		sb.append(", ");
		sb.append(position);
		sb.append(", ");
		sb.append(responsibility);
		sb.append(", ");
		sb.append(fromDate);
		sb.append(", ");
		sb.append(toDate);
		sb.append("]");
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object experience) {
		if(experience == null) return false; 
		if(!(experience instanceof Experience)) return false;
		Experience e = (Experience)experience;
		boolean isEqual = this.company.equals(e.getCompany()) &&
				          this.position.equals(e.getPosition()) &&
				          this.responsibility.equals(e.getResponsibility()) &&
				          this.fromDate.equals(e.getFromDate()) && 
				          this.toDate.equals(e.getToDate());
		return isEqual;
	}
	
	 
	

}
