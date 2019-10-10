package com.Finden.findenBackEnd.models.entity;

public class AddSwitch {
private int Switch;
private int index;
public int getSwitch() {
	return Switch;
}
public void setSwitch(int switch1) {
	Switch = switch1;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public AddSwitch() {
	
}
@Override
public String toString() {
	return "AddSwitch [Switch=" + Switch + ", index=" + index + "]";
}

}
