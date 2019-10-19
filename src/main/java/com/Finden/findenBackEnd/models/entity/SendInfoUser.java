package com.Finden.findenBackEnd.models.entity;

public class SendInfoUser {
	private String name;
	private String email;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SendInfoUser() {
		
	}
	@Override
	public String toString() {
		return "SendInfoUser [name=" + name + ", email=" + email + ", type=" + type + "]";
	}
	
}
