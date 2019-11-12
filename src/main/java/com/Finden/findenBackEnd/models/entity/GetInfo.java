package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase contiene la información extraida de HPeIMC
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class GetInfo {
	private String phyAddress;
	private String operationStatusDesc;
	private String ifDescription;
	private Integer ifspeed;
	/**
	 * Método para obtener la mac del computador conectado
	 * @return La mac del computador conectado
	 */
	public String getPhyAddress() {
		return phyAddress;
	}
	/**
	 * Método para asignar la mac del computador conectado
	 * @param La mac del computador conectado
	 */
	public void setPhyAddress(String phyAddress) {
		this.phyAddress = phyAddress;
	}
	/**
	 * Método para obtener el estado de un puerto
	 * @return El estado de un puerto
	 */
	public String getAdminStatusDesc() {
		return operationStatusDesc;
	}
	/**
	 * Método para asignar el estado de un puerto
	 * @param El estado de un puerto
	 */
	public void setAdminStatusDesc(String adminStatusDesc) {
		this.operationStatusDesc = adminStatusDesc;
	}
	/**
	 * Método para obtener la descripción de un puerto
	 * @return la descripción de un puerto
	 */
	public String getIfDescription() {
		return ifDescription;
	}
	/**
	 * Método para asignar la descripción de un puerto
	 * @param La descripción de un puerto
	 */
	public void setIfDescription(String ifDescription) {
		this.ifDescription = ifDescription;
	}
	/**
	 * Método para obtener la velocidad de conexion de un puerto
	 * @return La velocidad de conexion de un puerto
	 */
	public Integer getIfspeed() {
		return ifspeed;
	}
	/**
	 * Método para asignar la velocidad de conexion de un puerto
	 * @param La velocidad de conexion de un puerto
	 */
	public void setIfspeed(Integer ifspeed) {
		this.ifspeed = ifspeed;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public GetInfo() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase ApprovePlane
	  * 
	  */
	public String toString() {
		return "GetInfo [phyAddress=" + phyAddress + ", adminStatusDesc=" + operationStatusDesc + ", ifAlias="
				+ ifDescription + ", ifspeed=" + ifspeed + "]";
	}

	
}
