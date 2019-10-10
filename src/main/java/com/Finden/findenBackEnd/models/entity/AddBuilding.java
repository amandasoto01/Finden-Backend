package com.Finden.findenBackEnd.models.entity;

public class AddBuilding {
private String Name;
private int Number;
private int NFloors;
private int NBasement;

public AddBuilding() {
	
}

public String getName() {
	return Name;
}

public void setName(String name) {
	Name = name;
}

public int getNumber() {
	return Number;
}

public void setNumber(int number) {
	Number = number;
}

public int getNFloors() {
	return NFloors;
}

public void setNFloors(int nFloors) {
	NFloors = nFloors;
}

public int getNBasement() {
	return NBasement;
}

public void setNBasement(int nBasement) {
	NBasement = nBasement;
}

@Override
public String toString() {
	return "AddBuilding [Name=" + Name + ", Number=" + Number + ", NFloors=" + NFloors + ", NBasement=" + NBasement
			+ "]";
}

}
