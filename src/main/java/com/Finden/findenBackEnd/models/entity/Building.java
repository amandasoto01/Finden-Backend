package com.Finden.findenBackEnd.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Esta clase representa genera la relación del objeto Building con la tabla Building de la base de datos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Entity
@Table(name="Building")
public class Building {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="number")
	private int number;
	
	@Column (name="name")
	private String name;
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public Building() {
	}
	/**
	 * Método para obtener el id del Edificio
	 * @return El id del edificio
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asignar el id del Edificio
	 * @param El id del edificio
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el numero del Edificio
	 * @return El numero del edificio
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Método para asignar el numero del Edificio
	 * @param El numero del edificio
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Método para obtener el nombre del Edificio
	 * @return El nombre del edificio
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para asignar el nombre del Edificio
	 * @param El nombre del edificio
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	  * Método para imprimir la instancia de la clase Building
	  * 
	  */
	public String toString() {
		return "Building [id=" + id + ", number=" + number + ", name=" + name + "]";
	}
	
}
