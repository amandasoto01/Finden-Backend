package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Esta clase representa genera la relación del objeto Port con la tabla Port de la base de datos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Entity
@Table(name="port")
public class Port {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer Id;
	
	@Column(name="\"name\"")
	private String name;
	
	@Column(name="\"portinswitch\"")
	private Integer portInSwitch;
	
	@Column(name="\"type\"")
	private Integer type;
	
	@Column(name="\"floor_id\"")
	private Integer Floor_Id;
	
	@Column(name="\"floor_building_id\"")
	private Integer Floor_Building_Id;
	
	@Column(name="\"switch_id\"")
	private Integer Switch_id;
	
	@Column(name="\"Switch_writingcenter_id\"")
	private Integer Switch_WritingCenter_id;

	/**
	 * Método para obtener el id del puerto
	 * @return El id del plano
	 */
	public Integer getId() {
		return Id;
	}
	
	/**
	 * Método para asignar el id del puerto
	 * @return El id del puerto
	 */
	public void setId(Integer id) {
		Id = id;
	}
	/**
	 * Método para obtener el nombre del puerto
	 * @return El nombre del puerto
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para asignar el nombre del puerto
	 * @param El nombre del puerto
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Método para obtener el puerto en el siwtch del puerto
	 * @return El puerto en el siwtch del puerto
	 */
	public Integer getPortInSwitch() {
		return portInSwitch;
	}
	/**
	 * Método para asignar el puerto en el siwtch del puerto
	 * @param El puerto en el siwtch del puert
	 */
	public void setPortInSwitch(Integer portInSwitch) {
		this.portInSwitch = portInSwitch;
	}
	/**
	 * Método para obtener el tipo del puerto
	 * @return El tipo del puerto
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * Método para asginar el tipo del puerto
	 * @param El tipo del puerto
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * Método para obtener el id del piso
	 * @return El id del piso
	 */
	public Integer getFloor_Id() {
		return Floor_Id;
	}
	/**
	 * Método para asignar el id del piso
	 * @param El id del piso
	 */
	public void setFloor_Id(Integer floor_Id) {
		Floor_Id = floor_Id;
	}
	/**
	 * Método para obtener el id del edificio
	 * @return El id del edificio
	 */
	public Integer getFloor_Building_Id() {
		return Floor_Building_Id;
	}
	/**
	 * Método para asignar el id del edificio
	 * @param El id del edificio
	 */
	public void setFloor_Building_Id(Integer floor_Building_Id) {
		Floor_Building_Id = floor_Building_Id;
	}
	/**
	 * Método para obtener el id del switch
	 * @return El id del switch
	 */
	public Integer getSwitch_id() {
		return Switch_id;
	}
	/**
	 * Método para asignar el id del switch
	 * @param El id del switch
	 */

	public void setSwitch_id(Integer switch_id) {
		Switch_id = switch_id;
	}

	/**
	 * Método para obtener el id del centro de cableado
	 * @return El id del centro de cableado
	 */
	public Integer getSwitch_WritingCenter_id() {
		return Switch_WritingCenter_id;
	}
	/**
	 * Método para asignar el id del centro de cableado
	 * @param El id del centro de cableado
	 */
	public void setSwitch_WritingCenter_id(Integer switch_WritingCenter_id) {
		Switch_WritingCenter_id = switch_WritingCenter_id;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public Port () {
	}

	/**
	  * Método para imprimir la instancia de la clase Plane
	  * 
	  */
	public String toString() {
		return "Port [Id=" + Id + ", name=" + name + ", portInSwitch=" + portInSwitch + ", type=" + type + ", Floor_Id="
				+ Floor_Id + ", Floor_Building_Id=" + Floor_Building_Id + ", Switch_id=" + Switch_id
				+ ", Switch_WritingCenter_id=" + Switch_WritingCenter_id + "]";
	}
	
	
}
