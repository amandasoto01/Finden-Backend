package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para obtener el historial de un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class HistorialPlane {
	private Integer Version;
	private String Description;
	/**
	 * Método para obtener la versión del plano
	 * @return La versión del plano
	 */
	public Integer getVersion() {
		return Version;
	}
	/**
	 * Método para asignar la versión del plano
	 * @param La versión del plano
	 */
	public void setVersion(Integer version) {
		Version = version;
	}
	/**
	 * Método para obtener la descripción de un plano
	 * @return La descripción de un plano
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * Método para asignar la descripción de un plano
	 * @param La descripción de un plano
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public HistorialPlane() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase HistorialPlane
	  * 
	  */
	public String toString() {
		return "HistorialPlane [Version=" + Version + ", Description=" + Description + "]";
	}
	
}
