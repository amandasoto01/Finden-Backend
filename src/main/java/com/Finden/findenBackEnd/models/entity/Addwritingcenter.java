package com.Finden.findenBackEnd.models.entity;

import java.util.List;
/**
 * Esta clase representa la información obtenida desde el front para agregar un Centro de cableado
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class Addwritingcenter {
 private int Id;
 private String Name;
 private int building;
 private int Floor;
 private List<AddSwitch> Switches;
	/**
	 * Método para obtener el id del centro de cableado segun HPeIMC
	 * @return El id del centro de cableado segun HPeIMC
	 */
public int getId() {
	return Id;
}
/**
 * Método para asignar el id del centro de cableado segun HPeIMC
 * @param El id del centro de cableado segun HPeIMC
 */
public void setId(int id) {
	Id = id;
}
/**
 * Método para obtener el nombre del centro de cableado
 * @return El nombre del centro de cableado
 */
public String getName() {
	return Name;
}
/**
 * Método para asignar el nombre del centro de cableado
 * @param El nombre del centro de cableado
 */
public void setName(String name) {
	Name = name;
}
/**
 * Método para obtener el edificio donde se ubica el centro de cableado
 * @return El edificio donde se ubica el centro de cableado
 */
public int getBuilding() {
	return building;
}
/**
 * Método para asignar el edificio donde se ubica el centro de cableado
 * @param El edificio donde se ubica el centro de cableado
 */
public void setBuilding(int building) {
	this.building = building;
}
/**
 * Método para obtener el piso donde se ubica el centro de cableado
 * @return El piso donde se ubica el centro de cableado
 */
public int getFloor() {
	return Floor;
}
/**
 * Método para asignar el piso donde se ubica el centro de cableado
 * @param El piso donde se ubica el centro de cableado
 */
public void setFloor(int floor) {
	Floor = floor;
}
/**
 * Método para obtener los switches que se ubican el centro de cableado
 * @return Los switches que se ubican el centro de cableado
 */
public List<AddSwitch> getSwitches() {
	return Switches;
}
/**
 * Método para asignar los switches que se ubican el centro de cableado
 * @param Los switches que se ubican el centro de cableado
 */
public void setSwitches(List<AddSwitch> switches) {
	Switches = switches;
}
/**
 * Constructor de la clase
 * @return Una instancia de la clase con sus campos en null
 */
public Addwritingcenter() {
	
}
/**
 * Método para imprimir la instancia de la clase AddWiringCenter
 * 
 */
public String toString() {
	return "AddWiringCenter [Id=" + Id + ", Name=" + Name + ", building=" + building + ", Floor=" + Floor
			+ ", Switches=" + Switches + "]";
}



}
