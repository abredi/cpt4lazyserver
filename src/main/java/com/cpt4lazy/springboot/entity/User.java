package com.cpt4lazy.springboot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	private String password;
	private String email;
	@DBRef
    private UserRole role;
	
	public User() {}
	public User(String email, String password, UserRole role) {
		this.email = email;
		this.password = password;
		this.role = role;	
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(id);
		sb.append(", ");
		sb.append(email);
		sb.append(", ");
		sb.append(password);
		sb.append(", ");
		sb.append(role);
		sb.append("]");
		
		return sb.toString();
	}
	
}
