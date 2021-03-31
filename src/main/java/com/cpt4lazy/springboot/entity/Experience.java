package com.cpt4lazy.springboot.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "experience")
public class Experience {
	
	private String company;
	private String position;
	private String responsibility;
	private LocalDate fromDate;
	private LocalDate toDate;
	
	public Experience (){}

	public Experience(String company, String position, String responsibility, LocalDate fromDate, LocalDate toDate) {
		this.company = company;
		this.position = position;
		this.responsibility = responsibility;
		this.fromDate = fromDate;
		this.toDate = toDate;
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
		sb.append(position);
		sb.append(responsibility);
		sb.append(fromDate);
		sb.append(toDate);
		
		
		return sb.toString();
	}
	
	
	

}
