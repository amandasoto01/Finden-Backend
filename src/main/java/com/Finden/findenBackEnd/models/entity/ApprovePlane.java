package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para aprobar o rechazar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class ApprovePlane {

	private String Email;
	private String NamePlane;
	private boolean Status;
	private String description;
	/**
	 * Método para obtener el correo de quien aprueba el plano
	 * @return El correo de quien aprueba el plano
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Método para asignar el correo de quien aprueba el plano
	 * @param El correo de quien aprueba el plano
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * Método para obtener el nombre del plano a aprobar 
	 * @return El nombre del plano a aprobar
	 */
	public String getNamePlane() {
		return NamePlane;
	}
	/**
	 * Método para asignar el nombre del plano a aprobar 
	 * @param El nombre del plano a aprobar
	 */
	public void setNamePlane(String namePlane) {
		NamePlane = namePlane;
	}
	/**
	 * Método para obtener el estado del plano 
	 * @return El estado del plano
	 */
	public boolean isStatus() {
		return Status;
	}
	/**
	 * Método para asignar el estado del plano 
	 * @param El estado del plano
	 */
	public void setStatus(boolean status) {
		Status = status;
	}
	/**
	 * Método para obtener la descripción del plano 
	 * @return La descripción del plano
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Método para asignar la descripción del plano 
	 * @param La descripción del plano
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public ApprovePlane() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase ApprovePlane
	  * 
	  */
	public String toString() {
		return "ApprovePlane [Email=" + Email + ", NamePlane=" + NamePlane + ", Status=" + Status + ", description="
				+ description + "]";
	}
	
}
