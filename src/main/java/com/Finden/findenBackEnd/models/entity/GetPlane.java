package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para obtener un plano en especifico
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class GetPlane {
	private String namePlane;
	private Integer version;
	/**
	 * Método para obtener el nombre el plano
	 * @return El nombre del plano
	 */
	public String getNamePlane() {
		return namePlane;
	}
	/**
	 * Método para asignar el nombre el plano
	 * @param El nombre del plano
	 */
	public void setNamePlane(String namePlane) {
		this.namePlane = namePlane;
	}
	/**
	 * Método para obtener la versión del plano
	 * @return La versión del plano
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * Método para asignar la versión del plano
	 * @param La versión del plano
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public GetPlane() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase GetPlane
	  * 
	  */
	public String toString() {
		return "GetPlane [namePlane=" + namePlane + ", version=" + version + "]";
	}
	
}
