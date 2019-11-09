package com.Finden.findenBackEnd.models.entity;

public class PortList {
	private String wiringCenter;
	private Integer Switch;
	private String Port;
	private Integer portInSwitch;
	private String type;
	public String getWritingCenter() {
		return wiringCenter;
	}
	public void setWritingCenter(String writingCenter) {
		this.wiringCenter = writingCenter;
	}
	public Integer getSwitch() {
		return Switch;
	}
	public void setSwitch(Integer switch1) {
		Switch = switch1;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	public Integer getPortInSwitch() {
		return portInSwitch;
	}
	public void setPortInSwitch(Integer portInSwitch) {
		this.portInSwitch = portInSwitch;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PortList [writingCenter=" + wiringCenter + ", Switch=" + Switch + ", Port=" + Port + ", portInSwitch="
				+ portInSwitch + ", type=" + type + "]";
	}
	public PortList() {
		
	}
}
