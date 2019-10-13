package com.Finden.findenBackEnd.models.entity;

public class UpdatePort {
	private String Port;
	private Integer Building;
	private Integer Floor;
	private String Name;
	private Integer Switch;
	private Integer NPortSwitch;
	private String Type;
	private String WiringCenter;
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	public Integer getBuilding() {
		return Building;
	}
	public void setBuilding(Integer building) {
		Building = building;
	}
	public Integer getFloor() {
		return Floor;
	}
	public void setFloor(Integer floor) {
		Floor = floor;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getSwitch() {
		return Switch;
	}
	public void setSwitch(Integer switch1) {
		Switch = switch1;
	}
	public Integer getNPortSwitch() {
		return NPortSwitch;
	}
	public void setNPortSwitch(Integer nPortSwitch) {
		NPortSwitch = nPortSwitch;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getWiringCenter() {
		return WiringCenter;
	}
	public void setWiringCenter(String wiringCenter) {
		WiringCenter = wiringCenter;
	}
	
	public UpdatePort() {
		
	}
	@Override
	public String toString() {
		return "UpdatePort [Port=" + Port + ", Building=" + Building + ", Floor=" + Floor + ", Name=" + Name
				+ ", Switch=" + Switch + ", NPortSwitch=" + NPortSwitch + ", Type=" + Type + ", WiringCenter="
				+ WiringCenter + "]";
	}
	
	
}
