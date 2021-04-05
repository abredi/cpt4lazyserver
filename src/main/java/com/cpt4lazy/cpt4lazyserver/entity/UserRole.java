package com.cpt4lazy.cpt4lazyserver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userroles")
public abstract class UserRole {
	
	@Transient
    public static final String SEQUENCE_NAME = "userrole_sequence";
	
	@Id
	private long id;
	
	@Indexed(unique=true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String name;
	private String telephoneNumber;
	private String address;
	private String roleName;
	
	
	
	public UserRole(String name, String telephoneNumber, String address, String roleName) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.address = address;
		this.roleName = roleName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(id);
		sb.append(", ");
		sb.append(name);
		sb.append(", ");
		sb.append(telephoneNumber);
		sb.append(", ");
		sb.append(address);
		sb.append(", ");
		sb.append(roleName);
		sb.append("]");
		
		return sb.toString();
	}

}
