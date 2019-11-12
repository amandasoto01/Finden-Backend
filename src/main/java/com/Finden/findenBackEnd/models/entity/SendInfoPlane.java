package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información de un piso que se le envia al front 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class SendInfoPlane {
	private String Building;
	private String Floor;
	private String Name;
	private String Description;
	private String Status;
	private int version;
	private String dateApproval;
	private String observation;
	private String dateUpload;
	public SendInfoPlane() {
		
	}
	/**
	 * Método para obtener el nombre del edificio
	 * @return El nombre del edifcio
	 */
	public String getBuilding() {
		return Building;
	}
	/**
	 * Método para asignar el nombre del edificio
	 * @param El nombre del edifcio
	 */
	public void setBuilding(String building) {
		Building = building;
	}
	/**
	 * Método para obtener el piso
	 * @return El piso
	 */
	public String getFloor() {
		return Floor;
	}
	/**
	 * Método para asignar el piso
	 * @param El piso
	 */
	public void setFloor(String floor) {
		Floor = floor;
	}
	/**
	 * Método para obtener el nombre del plano
	 * @return El nombre del plano
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Método para asignar el nombre del plano
	 * @param El nombre del plano
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * Método para obtener la descripción del plano
	 * @return La descripción del plano
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * Método para asignar la descripción del plano
	 * @param La descripción del plano
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * Método para obtener el estado del plano
	 * @return El estado del plano
	 */
	public String getStatus() {
		return Status;
	}
	/**
	 * Método para asignar el estado del plano
	 * @param El estado del plano
	 */
	public void setStatus(String status) {
		Status = status;
	}
	/**
	 * Método para obtener la versión del plano
	 * @return La versión del plano
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * Método para asignar la versión del plano
	 * @param La versión del plano
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * Método para obtener la fecha que se aprobo el plano
	 * @return La fecha que se aprobo el plano
	 */
	public String getDateApproval() {
		return dateApproval;
	}
	/**
	 * Método para asignar la fecha que se aprobo el plano
	 * @param La fecha que se aprobo el plano
	 */
	public void setDateApproval(String dateApproval) {
		this.dateApproval = dateApproval;
	}
	/**
	 * Método para obtener la observación del plano
	 * @return La observación del plano
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * Método para asignar la observación del plano
	 * @param La observación del plano
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * Método para obtener la fecha que se subio el plano
	 * @return La fecha que se subio el plano
	 */
	public String getDateUpload() {
		return dateUpload;
	}
	/**
	 * Método para asignar la fecha que se subio el plano
	 * @param La fecha que se subio el plano
	 */
	public void setDateUpload(String dateUpload) {
		this.dateUpload = dateUpload;
	}
	/**
	  * Método para imprimir la instancia de la clase SendInfoPlane
	  * 
	  */
	public String toString() {
		return "SendInfoPlane [Building=" + Building + ", Floor=" + Floor + ", Name=" + Name + ", Description="
				+ Description + ", Status=" + Status + ", version=" + version + ", dateApproval=" + dateApproval
				+ ", observation=" + observation + ", dateUpload=" + dateUpload + "]";
	}
	
	
}
