package com.Finden.findenBackEnd.models.entity;

public class SendPort {
	private String Mac;
	private String State;
	private String Description;
	private String Speed;
	private String building;
	private String floor;
	private String port;
	private String wc;
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
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getWc() {
		return wc;
	}
	public void setWc(String wc) {
		this.wc = wc;
	}
	@Override
	public String toString() {
		return "SendPort [Mac=" + Mac + ", State=" + State + ", Description=" + Description + ", Speed=" + Speed
				+ ", building=" + building + ", floor=" + floor + ", switchs=" + port + ", wc=" + wc + "]";
	}

}
