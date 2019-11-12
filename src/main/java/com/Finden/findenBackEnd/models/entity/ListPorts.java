package com.Finden.findenBackEnd.models.entity;

import java.util.ArrayList;
/**
 * Esta clase representa la información obtenida desde el front para obtener la lista de puetos por piso y edificio
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class ListPorts {
	private Integer Building;
	private Integer Floor;
	private ArrayList<UpdatePort> ports;
	/**
	 * Método para obtener el eidicio
	 * @return El numero de un edificio
	 */
	public Integer getBuilding() {
		return Building;
	}
	/**
	 * Método para asignar el eidicio
	 * @param El numero de un edificio
	 */
	public void setBuilding(Integer building) {
		Building = building;
	}
	/**
	 * Método para obtener el piso
	 * @return El numero de un piso
	 */
	public Integer getFloor() {
		return Floor;
	}
	/**
	 * Método para asignar el piso
	 * @param El numero de un piso
	 */
	public void setFloor(Integer floor) {
		Floor = floor;
	}
	/**
	 * Método para obtener la lista de puertos actualizados
	 * @return La lista de puertos actualizados
	 */
	public ArrayList<UpdatePort> getPorts() {
		return ports;
	}
	/**
	 * Método para asignar la lista de puertos actualizados
	 * @param La lista de puertos actualizados
	 */
	public void setPorts(ArrayList<UpdatePort> ports) {
		this.ports = ports;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public ListPorts() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase ListPorts
	  * 
	  */
	public String toString() {
		return "ListPorts [Building=" + Building + ", Floor=" + Floor + ", ports=[" + ports.toString() + "]]";
	}
	


}
