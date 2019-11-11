package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para agregar un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class AddPort {
	private Integer Building;
	private Integer Floor;
	private String Name;
	private Integer Switch;
	private Integer NPortSwitch;
	private String Type;
	private String wiringcenter;
	/**
	 * Método para obtener el edificio donde se ubica el puerto
	 * return El edificio donde se ubica el puerto
	 */
	public Integer getBuilding() {
		return Building;
	}
	/**
	 * Método para asignar el edificio donde se ubica el puerto
	 * @param El edificio donde se ubica el puerto
	 */
	public void setBuilding(Integer building) {
		Building = building;
	}
	/**
	 * Método para obtener el piso donde se ubica el puerto
	 * @return El piso donde se ubica el puerto
	 */
	public Integer getFloor() {
		return Floor;
	}
	/**
	 * Método para asignar el piso donde se ubica el puerto
	 * @param El piso donde se ubica el puerto
	 */
	public void setFloor(Integer floor) {
		Floor = floor;
	}
	/**
	 * Método para obtener el nombre del puerto
	 * @return El nombre del puerto
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Método para asignar el nombre del puerto
	 * @param El nombre del puerto
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * Método para obtener el switch al que esta conectado el puerto
	 * @return El switch al que esta conectado el puerto
	 */
	public Integer getSwitch() {
		return Switch;
	}
	/**
	 * Método para asignar el switch al que esta conectado el puerto
	 * @param El switch al que esta conectado el puerto
	 */
	public void setSwitch(Integer switch1) {
		Switch = switch1;
	}
	/**
	 * Método para obtener el puerto en el switch al que esta conectado el puerto
	 * @return El puerto en el switch al que esta conectado el puerto
	 */
	public Integer getNPortSwitch() {
		return NPortSwitch;
	}
	/**
	 * Método para asignar el puerto en el switch al que esta conectado el puerto
	 * @param El puerto en el switch al que esta conectado el puerto
	 */
	public void setNPortSwitch(Integer nPortSwitch) {
		NPortSwitch = nPortSwitch;
	}
	/**
	 * Método para obtener el tipo del puerto: VD,D,V
	 * @return El tipo del puerto
	 */
	public String getType() {
		return Type;
	}
	/**
	 * Método para asignar el tipo del puerto: VD,D,V
	 * @param El tipo del puerto
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * Método para obtener el centro de cableado al que esta conectado el puerto
	 * @return El centro de cableado al que esta conectado el puerto
	 */
	public String getWiringCenter() {
		return wiringcenter;
	}
	/**
	 * Método para asignar el centro de cableado al que esta conectado el puerto
	 * @param El centro de cableado al que esta conectado el puerto
	 */
	public void setWiringCenter(String wiringCenter) {
		wiringcenter = wiringCenter;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public AddPort() {
		
	}
	 /**
	  * Método para imprimir la instancia de la clase AddPort 
	  * 
	  */
	public String toString() {
		return "AddPort [Building=" + Building + ", Floor=" + Floor + ", Name=" + Name + ", Switch=" + Switch
				+ ", NPortSwitch=" + NPortSwitch + ", Type=" + Type + ", WiringCenter=" + wiringcenter + "]";
	}
	

}