package com.Finden.findenBackEnd.models.entity;

public class AddPort {
	private int Building;
	private int Floor;
	private String Name;
	private int Switch;
	private int NPortSwitch;
	private String Type;
	private String WiringCenter;
	public int getBuilding() {
		return Building;
	}
	public void setBuilding(int building) {
		Building = building;
	}
	public Integer getFloor() {
		return Floor;
	}
	public void setFloor(int floor) {
		Floor = floor;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getSwitch() {
		return Switch;
	}
	public void setSwitch(int switch1) {
		Switch = switch1;
	}
	public int getNPortSwitch() {
		return NPortSwitch;
	}
	public void setNPortSwitch(int nPortSwitch) {
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
	@Override
	public String toString() {
		return "AddPort [Building=" + Building + ", Floor=" + Floor + ", Name=" + Name + ", Switch=" + Switch
				+ ", NPortSwitch=" + NPortSwitch + ", Type=" + Type + ", WiringCenter=" + WiringCenter + "]";
	}

}