package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información de un puerto que se le envia al front 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class SendPort {
	private String Mac;
	private String State;
	private String Description;
	private String Speed;
	private String building;
	private String floor;
	private String port;
	private String wc;
	/**
	 * Método para obtener la Mac de un puerto
	 * @return La Mac de un puerto
	 */
	public String getMac() {
		return Mac;
	}
	/**
	 * Método para asignar la Mac de un puerto
	 * @param La Mac de un puerto
	 */
	public void setMac(String mac) {
		Mac = mac;
	}
	/**
	 * Método para obtener el estado de un puerto
	 * @return El estado de un puerto
	 */
	public String getState() {
		return State;
	}
	/**
	 * Método para asignar el estado de un puerto
	 * @param El estado de un puerto
	 */
	public void setState(String state) {
		State = state;
	}
	/**
	 * Método para obtener la descripción de un puerto
	 * @return La descripción de un puerto
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * Método para asignar la descripción de un puerto
	 * @param La descripción de un puerto
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * Método para obtener la velocidad de conexión de un puerto
	 * @return La velocidad de conexión de un puerto
	 */
	public String getSpeed() {
		return Speed;
	}
	/**
	 * Método para asignar la velocidad de conexión de un puerto
	 * @param La velocidad de conexión de un puerto
	 */
	public void setSpeed(String speed) {
		Speed = speed;
	}
	/**
	 * Método para obtener el edificio
	 * @return El edificio
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * Método para asignar el edificio
	 * @param El edificio
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**
	 * Método para obtener el piso donde se ubica el puerto
	 * @return El piso donde se ubica el puerto
	 */
	public String getFloor() {
		return floor;
	}
	/**
	 * Método para asignar el piso donde se ubica el puerto
	 * @param El piso donde se ubica el puerto
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * Método para obtener el nombre del puerto
	 * @return El nombre del puerto
	 */
	public String getPort() {
		return port;
	}
	/**
	 * Método para asignar el nombre del puerto
	 * @param El nombre del puerto
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * Método para obtener el nombre del centro de cableado donde se ubica el puerto
	 * @return El nombre del centro de cableado donde se ubica el puerto
	 */
	public String getWc() {
		return wc;
	}
	/**
	 * Método para asignar el nombre del centro de cableado donde se ubica el puerto
	 * @param El nombre del centro de cableado donde se ubica el puerto
	 */
	public void setWc(String wc) {
		this.wc = wc;
	}
	/**
	  * Método para imprimir la instancia de la clase SendInfoPort
	  * 
	  */
	public String toString() {
		return "SendPort [Mac=" + Mac + ", State=" + State + ", Description=" + Description + ", Speed=" + Speed
				+ ", building=" + building + ", floor=" + floor + ", switchs=" + port + ", wc=" + wc + "]";
	}

}
