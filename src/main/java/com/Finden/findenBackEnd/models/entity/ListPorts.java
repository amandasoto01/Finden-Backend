package com.Finden.findenBackEnd.models.entity;

import java.util.ArrayList;

public class ListPorts {
	private Integer Building;
	private Integer Floor;
	private ArrayList<UpdatePort> ports;
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
	public ArrayList<UpdatePort> getPorts() {
		return ports;
	}
	public void setPorts(ArrayList<UpdatePort> ports) {
		this.ports = ports;
	}
	public ListPorts() {
		
	}
	@Override
	public String toString() {
		return "ListPorts [Building=" + Building + ", Floor=" + Floor + ", ports=[" + ports.toString() + "]]";
	}
	


}
