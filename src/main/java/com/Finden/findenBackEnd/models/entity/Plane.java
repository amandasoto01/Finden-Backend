package com.Finden.findenBackEnd.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Esta clase representa genera la relación del objeto Plane con la tabla Plane de la base de datos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Entity
@Table(name="Plane")
public class Plane {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dateupload")
	private Date dateUpload;
	
	@Column(name="version")
	private Integer version;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="observation")
	private String observation;
	
	@Column(name="description")
	private String description;
	
	@Column(name="dateapproval")
	private Date dateApproval;
	
	@Column(name="dir")
	private String dir;
	
	@Column(name="Floor_id")
	private Integer Floor_id;
	
	@Column(name="Floor_building_id")
	private Integer Floor_Building_Id;
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public Plane() {
		
	}
	/**
	 * Método para obtener el id del plano
	 * @return El id del plano
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asignar el id del plano
	 * @param El id del plano
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el nombre del plano
	 * @return El nombre del plano
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para asignar el nombre del plano
	 * @param El nombre del plano
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Método para obtener la fecha que se cargo el plano
	 * @return La fecha que se aprobo el plano
	 */
	public Date getDateUpload() {
		return dateUpload;
	}
	/**
	 * Método para asignar la fecha que se cargo el plano
	 * @param La fecha que se aprobo el plano
	 */
	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	/**
	 * Método para obtener la versión plano
	 * @return La versión plano
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * Método para asignar la versión plano
	 * @param La versión plano
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * Método para obtener el estado del plano
	 * @return El estado del plano
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * Método para asignar el estado del plano
	 * @param El estado del plano
	 */
	public void setState(Integer state) {
		this.state = state;
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
	 * Método para obtener la fecha que se aprobo el plano
	 * @return La fecha que se aprobo el plano
	 */
	public Date getDateApproval() {
		return dateApproval;
	}
	/**
	 * Método para asignar la fecha que se aprobo el plano
	 * @param La fecha que se aprobo el plano
	 */
	public void setDateApproval(Date dateApproval) {
		this.dateApproval = dateApproval;
	}
	/**
	 * Método para obtener la dirección del plano
	 * @return La dirección del plano
	 */
	public String getDir() {
		return dir;
	}
	/**
	 * Método para asignar la dirección del plano
	 * @param La dirección del plano
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	/**
	 * Método para obtener el id del piso
	 * @return El id del piso
	 */
	public Integer getFloor_id() {
		return Floor_id;
	}
	/**
	 * Método para asignar el id del piso
	 * @param El id del piso
	 */
	public void setFloor_id(Integer floor_id) {
		Floor_id = floor_id;
	}
	/**
	 * Método para obtener el id del edificio
	 * @return El id del edifcio
	 */
	public Integer getFloor_Building_Id() {
		return Floor_Building_Id;
	}
	/**
	 * Método para asignar el id del edificio
	 * @param El id del edifcio
	 */
	public void setFloor_Building_Id(Integer floor_Building_Id) {
		Floor_Building_Id = floor_Building_Id;
	}

	/**
	  * Método para imprimir la instancia de la clase Plane
	  * 
	  */
	public String toString() {
		return "Plane [id=" + id + ", name=" + name + ", dateUpload=" + dateUpload + ", version=" + version + ", state="
				+ state + ", observation=" + observation + ", description=" + description + ", dateApproval="
				+ dateApproval + ", dir=" + dir + ", Floor_id=" + Floor_id + ", Floor_Building_Id=" + Floor_Building_Id
				+ "]";
	}
	
	
}
