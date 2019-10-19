package com.Finden.findenBackEnd.models.entity;

public class SendInfoPlane {
	private String Name;
	private String Description;
	private Boolean Status;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Boolean isStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	
	public SendInfoPlane() {
		
	}
	@Override
	public String toString() {
		return "SendInfoPlane [Name=" + Name + ", Description=" + Description + ", Status=" + Status + "]";
	}
	
}
