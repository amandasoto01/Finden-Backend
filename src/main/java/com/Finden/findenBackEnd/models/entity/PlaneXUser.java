package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esta clase representa genera la relación del objeto PlaneXUser con la tabla PlaneXuser de la base de datos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Entity
@Table(name="Planexuser")
public class PlaneXUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="User_id")
	private Integer User_Id;
	
	@Column(name="Plane_id")
	private Integer Plane_Id;
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public PlaneXUser() {
		
	}
	/**
	 * Método para obtener el id del plano por usuario
	 * @return El id del plano por usuario
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asginar el id del plano por usuario
	 * @param El id del plano por usuario
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el id del usuario
	 * @return El id del usuario
	 */
	public Integer getUser_Id() {
		return User_Id;
	}
	/**
	 * Método para asignar el id del usuario
	 * @param El id del usuario
	 */
	public void setUser_Id(Integer user_Id) {
		User_Id = user_Id;
	}
	/**
	 * Método para obtener el id del plano
	 * @return El id del plano
	 */
	public Integer getPlane_Id() {
		return Plane_Id;
	}
	/**
	 * Método para asignar el id del plano
	 * @param El id del plano
	 */
	public void setPlane_Id(Integer plane_Id) {
		Plane_Id = plane_Id;
	}

	/**
	  * Método para imprimir la instancia de la clase PlaneXUser
	  * 
	  */
	public String toString() {
		return "PlaneXUser [id=" + id + ", User_Id=" + User_Id + ", Plane_Id=" + Plane_Id + "]";
	}
	

}
