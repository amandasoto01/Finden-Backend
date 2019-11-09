package com.Finden.findenBackEnd.models.entity;

public class AllInfoPort {
	private String mac;
	private String state;
	private String speed;
	private String building;
	private String portInSwitch;
	private String switchs;
	private String floor;
	private String wiringCenter;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getPortInSwitch() {
		return portInSwitch;
	}
	public void setPortInSwitch(String portInSwitch) {
		this.portInSwitch = portInSwitch;
	}
	public String getSwitchs() {
		return switchs;
	}
	public void setSwitchs(String switchs) {
		this.switchs = switchs;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getWiringCenter() {
		return wiringCenter;
	}
	public void setWiringCenter(String wiringCenter) {
		this.wiringCenter = wiringCenter;
	}
	@Override
	public String toString() {
		return "AllInfoPort [mac=" + mac + ", state=" + state + ", speed=" + speed + ", building=" + building
				+ ", portInSwitch=" + portInSwitch + ", switchs=" + switchs + ", floor=" + floor + ", wiringCenter="
				+ wiringCenter + "]";
	}
	public AllInfoPort() {
		
	}

}
