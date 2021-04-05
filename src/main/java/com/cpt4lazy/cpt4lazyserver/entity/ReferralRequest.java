package com.cpt4lazy.cpt4lazyserver.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cpt4lazy.cpt4lazyserver.configs.LocalDateDeserializer;
import com.cpt4lazy.cpt4lazyserver.configs.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document(collection = "referralrequest")
public class ReferralRequest {
	
	@Transient
    public static final String SEQUENCE_NAME = "referralrequest";
	
	@Id
	private long id;
	
	private String requestorEmail;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate askedDate;
	private String requestStatus;
	
	public ReferralRequest() {}
	public ReferralRequest(String requestorEmail, LocalDate askedDate, String requestStatus) {
		this.requestorEmail = requestorEmail;
		this.askedDate = askedDate;
		this.requestStatus = requestStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestorName() {
		return requestorEmail;
	}

	public void setRequestorName(String requestorEmail) {
		this.requestorEmail = requestorEmail;
	}

	public LocalDate getAskedDate() {
		return askedDate;
	}

	public void setAskedDate(LocalDate askedDate) {
		this.askedDate = askedDate;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(requestorEmail);
		sb.append(", ");
		sb.append(askedDate);
		sb.append(", ");
		sb.append(requestStatus);
		sb.append("[");
		
		return sb.toString();	
	}
}
