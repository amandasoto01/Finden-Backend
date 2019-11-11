package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para agregar un edificio
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class AddBuilding {
private String Name;
private int Number;
private int NFloors;
private int NBasement;
/**
 * Constructor de la clase
 * @return Una instancia de la clase con sus campos en null
 */
public AddBuilding() {
	
}
/**
 * Método para obtener el nombre del edificio 
 * @return El nombre del edificio de la instancia
 */
public String getName() {
	return Name;
}
/**
 * Método asiganar el nombre del edificio 
 * @param El nombre del edificio
 */
public void setName(String name) {
	Name = name;
}
/**
 * Método para obtener el numero del edificio 
 * @return El numero del edificio de la instancia
 */
public int getNumber() {
	return Number;
}
/**
 * Método para asignar el numero del edificio 
 * @param El numero del edificio de la instancia
 */
public void setNumber(int number) {
	Number = number;
}
/**
 * Método para obtener el numero de pisos del edificio 
 * @return El numero de pisos del edificio de la instancia
 */
public int getNFloors() {
	return NFloors;
}
/**
 * Método para asignar el numero de pisos del edificio 
 * @param El numero de pisos del edificio de la instancia
 */
public void setNFloors(int nFloors) {
	NFloors = nFloors;
}
/**
 * Método para obtener el numero de sotanos del edificio 
 * @return El numero de sotanos del edificio de la instancia
 */
public int getNBasement() {
	return NBasement;
}
/**
 * Método para asignar el numero de sotanos del edificio 
 * @param El numero de sotanos del edificio de la instancia
 */
public void setNBasement(int nBasement) {
	NBasement = nBasement;
}
/**
 * Método para imprimir la instancia de la clase addBuilding 
 * 
 */
public String toString() {
	return "AddBuilding [Name=" + Name + ", Number=" + Number + ", NFloors=" + NFloors + ", NBasement=" + NBasement
			+ "]";
}

}
