package com.Finden.findenBackEnd.models.entity;

public class SendPort {
	private String Mac;
	private String State;
	private String Description;
	private String Speed;
	public String getMac() {
		return Mac;
	}
	public void setMac(String mac) {
		Mac = mac;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSpeed() {
		return Speed;
	}
	public void setSpeed(String speed) {
		Speed = speed;
	}
	public SendPort() {
		
	}
	@Override
	public String toString() {
		return "SendPort [Mac=" + Mac + ", State=" + State + ", Description=" + Description + ", Speed=" + Speed + "]";
	}
	
}
