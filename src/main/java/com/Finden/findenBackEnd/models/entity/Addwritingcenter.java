package com.Finden.findenBackEnd.models.entity;

import java.util.List;

public class Addwritingcenter {
 private int Id;
 private String Name;
 private int building;
 private int Floor;
 private List<AddSwitch> Switches;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getBuilding() {
	return building;
}
public void setBuilding(int building) {
	this.building = building;
}
public int getFloor() {
	return Floor;
}
public void setFloor(int floor) {
	Floor = floor;
}
public List<AddSwitch> getSwitches() {
	return Switches;
}
public void setSwitches(List<AddSwitch> switches) {
	Switches = switches;
}
public Addwritingcenter() {
	
}
@Override
public String toString() {
	return "AddWiringCenter [Id=" + Id + ", Name=" + Name + ", building=" + building + ", Floor=" + Floor
			+ ", Switches=" + Switches + "]";
}



}
