package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para modificar un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class UpdatePort {
	private String Port;
	private Integer Building;
	private Integer Floor;
	private String Name;
	private Integer Switch;
	private Integer NPortSwitch;
	private String Type;
	private String WiringCenter;
	/**
	 * Método para obtener el nombre original de un puerto
	 * @return El nombre original de un puerto
	 */
	public String getPort() {
		return Port;
	}
	/**
	 * Método para asignar el nombre original de un puerto
	 * @param El nombre original de un puerto
	 */
	public void setPort(String port) {
		Port = port;
	}
	/**
	 * Método para obtener el numero del edificio
	 * @return El numero del edificio
	 */
	public Integer getBuilding() {
		return Building;
	}
	/**
	 * Método para asignar el numero del edificio
	 * @param El numero del edificio
	 */
	public void setBuilding(Integer building) {
		Building = building;
	}
	/**
	 * Método para obtener el numero del piso
	 * @return El numero del piso
	 */
	public Integer getFloor() {
		return Floor;
	}
	/**
	 * Método para asignar el numero del piso
	 * @param El numero del piso
	 */
	public void setFloor(Integer floor) {
		Floor = floor;
	}
	/**
	 * Método para obtener el nuevo nombre del puerto
	 * @return El nuevo nombre del puerto
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Método para asignar el nuevo nombre del puerto
	 * @param El nuevo nombre del puerto
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * Método para obtener el nuevo switch donde se encuentre conectado el puerto
	 * @return El nuevo switch donde se encuentre conectado el puerto
	 */
	public Integer getSwitch() {
		return Switch;
	}
	/**
	 * Método para asginar el nuevo switch donde se encuentre conectado el puerto
	 * @param El nuevo switch donde se encuentre conectado el puerto
	 */
	public void setSwitch(Integer switch1) {
		Switch = switch1;
	}
	/**
	 * Método para obtener el nuevo puerto en el switch donde esta conectado el puerto
	 * @return El nuevo puerto en el switch donde esta conectado el puerto
	 */
	public Integer getNPortSwitch() {
		return NPortSwitch;
	}
	/**
	 * Método para asignar el nuevo puerto en el switch donde esta conectado el puerto
	 * @param El nuevo puerto en el switch donde esta conectado el puerto
	 */
	public void setNPortSwitch(Integer nPortSwitch) {
		NPortSwitch = nPortSwitch;
	}
	/**
	 * Método para obtener el nuevo tipo del puerto
	 * @return El nuevo tipo del puerto
	 */
	public String getType() {
		return Type;
	}
	/**
	 * Método para asignar el nuevo tipo del puerto
	 * @param El nuevo tipo del puerto
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * Método para obtener el nuevo centro de cableado donde esta conectado el puerto
	 * @return El nuevo centro de cableado donde esta conectado el puerto
	 */
	public String getWiringCenter() {
		return WiringCenter;
	}
	/**
	 * Método para asignar el nuevo centro de cableado donde esta conectado el puerto
	 * @param El nuevo centro de cableado donde esta conectado el puerto
	 */
	public void setWiringCenter(String wiringCenter) {
		WiringCenter = wiringCenter;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public UpdatePort() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase UpdatePort 
	  * 
	  */
	public String toString() {
		return "UpdatePort [Port=" + Port + ", Building=" + Building + ", Floor=" + Floor + ", Name=" + Name
				+ ", Switch=" + Switch + ", NPortSwitch=" + NPortSwitch + ", Type=" + Type + ", WiringCenter="
				+ WiringCenter + "]";
	}
	
	
}
