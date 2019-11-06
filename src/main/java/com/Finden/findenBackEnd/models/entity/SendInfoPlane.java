package com.Finden.findenBackEnd.models.entity;

public class SendInfoPlane {
	private String Building;
	private String Floor;
	private String Name;
	private String Description;
	private String Status;
	private int version;
	public String getBuilding() {
		return Building;
	}
	public void setBuilding(String building) {
		Building = building;
	}
	public String getFloor() {
		return Floor;
	}
	public void setFloor(String floor) {
		Floor = floor;
	}
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
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "SendInfoPlane [Building=" + Building + ", Floor=" + Floor + ", Name=" + Name + ", Description="
				+ Description + ", Status=" + Status + ", version=" + version + "]";
	}


	
	
}
