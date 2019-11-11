package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Esta clase representa genera la relación del objeto Floor con la tabla Floor de la base de datos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Entity
@Table(name="Floor")
public class Floor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="number")
	private Integer number;
	
	@Column(name="Building_Id")
	private int Building_Id;
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public Floor() {
		
	}
	/**
	 * Método para obtener el id del piso
	 * @return El id del piso
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asignar el id del piso
	 * @param El id del piso
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el numero del piso
	 * @return El numero del piso
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * Método para asignar el numero del piso
	 * @param El numero del piso
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * Método para obtener el id del edificio
	 * @return El id del edificio
	 */
	public int getBuilding_Id() {
		return Building_Id;
	}
	/**
	 * Método para asignar el id del edificio
	 * @param El id del edificio
	 */
	public void setBuilding_Id(int building_Id) {
		Building_Id = building_Id;
	}
	
}
