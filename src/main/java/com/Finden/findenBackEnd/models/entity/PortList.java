package com.Finden.findenBackEnd.models.entity;

public class PortList {
	private String writingCenter;
	private Integer Switch;
	private String Port;
	private Integer portInSwitch;
	public String getWritingCenter() {
		return writingCenter;
	}
	public void setWritingCenter(String writingCenter) {
		this.writingCenter = writingCenter;
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
	
	public PortList() {
	
	}
	
	public String toString() {
		return "PortList [writingCenter=" + writingCenter + ", Switch=" + Switch + ", Port=" + Port + ", portInSwitch="
				+ portInSwitch + "]";
	}
	
	
}
