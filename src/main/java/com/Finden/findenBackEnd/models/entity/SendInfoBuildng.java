package com.Finden.findenBackEnd.models.entity;

public class SendInfoBuildng {
	private String name;
	private int number;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public SendInfoBuildng() {
	
	}
	@Override
	public String toString() {
		return "SendInfoBuildng [name=" + name + ", number=" + number + "]";
	}
	
	
}
