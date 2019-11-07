package com.Finden.findenBackEnd.models.entity;

public class ApprovePlane {

	private String Email;
	private String NamePlane;
	private boolean Status;
	private String description;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNamePlane() {
		return NamePlane;
	}
	public void setNamePlane(String namePlane) {
		NamePlane = namePlane;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ApprovePlane() {
		
	}
	@Override
	public String toString() {
		return "ApprovePlane [Email=" + Email + ", NamePlane=" + NamePlane + ", Status=" + Status + ", description="
				+ description + "]";
	}
	
}
