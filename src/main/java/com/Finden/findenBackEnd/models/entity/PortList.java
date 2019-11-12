package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información de una lista de puertos segun un edificio y un piso que se le envia al front 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class PortList {
	private String wiringCenter;
	private Integer Switch;
	private String Port;
	private Integer portInSwitch;
	private String type;
	/**
	 * Método para obtener el nombre del centro de cableado en que se encuentra un puerto
	 * @return El nombre del centro de cableado en que se encuentra un puerto
	 */
	public String getWritingCenter() {
		return wiringCenter;
	}
	/**
	 * Método para asignar el nombre del centro de cableado en que se encuentra un puerto
	 * @param El nombre del centro de cableado en que se encuentra un puerto
	 */
	public void setWritingCenter(String writingCenter) {
		this.wiringCenter = writingCenter;
	}
	/**
	 * Método para obtener el switch en que se encuentra un puerto
	 * @return El swtich en que se encuentra un puerto
	 */
	public Integer getSwitch() {
		return Switch;
	}
	/**
	 * Método para asignar el switch en que se encuentra un puerto
	 * @param El swtich en que se encuentra un puerto
	 */
	public void setSwitch(Integer switch1) {
		Switch = switch1;
	}
	/**
	 * Método para obtener el nombre del puerto
	 * @return El nombre puerto
	 */
	public String getPort() {
		return Port;
	}
	/**
	 * Método para asignar el nombre del puerto
	 * @param El nombre puerto
	 */
	public void setPort(String port) {
		Port = port;
	}
	/**
	 * Método para obtener el puerto en el siwtch al que esta conencatado un puerto
	 * @return El puerto en el siwtch al que esta conencatado un puerto
	 */
	public Integer getPortInSwitch() {
		return portInSwitch;
	}
	/**
	 * Método para asignar el puerto en el siwtch al que esta conencatado un puerto
	 * @param El puerto en el siwtch al que esta conencatado un puerto
	 */
	public void setPortInSwitch(Integer portInSwitch) {
		this.portInSwitch = portInSwitch;
	}
	/**
	 * Método para obtener el tipo de un puerto
	 * @return El tipo de un puerto
	 */
	public String getType() {
		return type;
	}
	/**
	 * Método para asignar el tipo de un puerto
	 * @param El tipo de un puerto
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	  * Método para imprimir la instancia de la clase Plane
	  * 
	  */
	public String toString() {
		return "PortList [writingCenter=" + wiringCenter + ", Switch=" + Switch + ", Port=" + Port + ", portInSwitch="
				+ portInSwitch + ", type=" + type + "]";
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public PortList() {
		
	}
}
