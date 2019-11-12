package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información de un piso que se le envia al front 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class SendInfoBuildng {
	private String name;
	private int number;
	/**
	 * Método para obtener el nombre del edificio
	 * @return El nombre del edifcio
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para asignar el nombre del edificio
	 * @param El nombre del edifcio
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Método para obtener el numero del edificio
	 * @return El numero del edifcio
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Método para asignar el numero del edificio
	 * @param El numero del edifcio
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public SendInfoBuildng() {
	
	}
	/**
	  * Método para imprimir la instancia de la clase SendInfoBuilding
	  * 
	  */
	public String toString() {
		return "SendInfoBuildng [name=" + name + ", number=" + number + "]";
	}
	
	
}
