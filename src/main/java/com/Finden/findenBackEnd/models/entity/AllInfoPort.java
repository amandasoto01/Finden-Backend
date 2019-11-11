package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información de un puerto segun el software HPeIMC que se le envia al front 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class AllInfoPort {
	private String mac;
	private String state;
	private String speed;
	private String building;
	private String portInSwitch;
	private String switchs;
	private String floor;
	private String wiringCenter;
	/**
	 * Método para obtener la mac del computador conectado al puerto
	 * @return La mac del computador conectado al puerto
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * Método para asignar la mac del computador conectado al puerto
	 * @param La mac del computador conectado al puerto
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * Método para obtener el estado del puerto
	 * @return El estado del puerto
	 */
	public String getState() {
		return state;
	}
	/**
	 * Método para asignar el estado del puerto
	 * @param El estado del puerto
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * Método para obtener la velocidad de conexión del puerto
	 * @return La velociodad de conexión del puerto
	 */
	public String getSpeed() {
		return speed;
	}
	/**
	 * Método para asignar la velocidad de conexión del puerto
	 * @param La velociodad de conexión del puerto
	 */
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	/**
	 * Método para obtener el edificio donde se encuetra el puerto
	 * @return El edificio donde se encuetra el puerto
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * Método para asignar el edificio donde se encuetra el puerto
	 * @param El edificio donde se encuetra el puerto
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**
	 * Método para obtener el puerto en el siwtch donde se encuetra el puerto
	 * @return El puerto en el siwtch donde se encuetra el puerto
	 */
	public String getPortInSwitch() {
		return portInSwitch;
	}
	/**
	 * Método para asignar el puerto en el siwtch donde se encuetra el puerto
	 * @param El puerto en el siwtch donde se encuetra el puerto
	 */
	public void setPortInSwitch(String portInSwitch) {
		this.portInSwitch = portInSwitch;
	}
	/**
	 * Método para obtener el siwtch donde se encuetra el puerto
	 * @return El siwtch donde se encuetra el puerto
	 */
	public String getSwitchs() {
		return switchs;
	}
	/**
	 * Método para asignar el siwtch donde se encuetra el puerto
	 * @param El siwtch donde se encuetra el puerto
	 */
	public void setSwitchs(String switchs) {
		this.switchs = switchs;
	}
	/**
	 * Método para obtener el piso donde se encuetra el puerto
	 * @return El piso donde se encuetra el puerto
	 */
	public String getFloor() {
		return floor;
	}
	/**
	 * Método para asignar el piso donde se encuetra el puerto
	 * @param El piso donde se encuetra el puerto
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * Método para obtener el centro de cableado donde se encuetra conetcado el puerto
	 * @return El centro de cableado donde se encuetra conetcado el puerto
	 */
	public String getWiringCenter() {
		return wiringCenter;
	}
	/**
	 * Método para asignar el centro de cableado donde se encuetra conetcado el puerto
	 * @param El centro de cableado donde se encuetra conetcado el puerto
	 */
	public void setWiringCenter(String wiringCenter) {
		this.wiringCenter = wiringCenter;
	}
	 /**
	  * Método para imprimir la instancia de la clase AllinfoPort
	  * 
	  */
	public String toString() {
		return "AllInfoPort [mac=" + mac + ", state=" + state + ", speed=" + speed + ", building=" + building
				+ ", portInSwitch=" + portInSwitch + ", switchs=" + switchs + ", floor=" + floor + ", wiringCenter="
				+ wiringCenter + "]";
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public AllInfoPort() {
		
	}

}
