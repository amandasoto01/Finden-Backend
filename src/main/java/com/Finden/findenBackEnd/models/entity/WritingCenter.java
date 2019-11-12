package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa genera la relación del objeto wiringcenter con la tabla wiringcenter de la base de datos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wiringcenter")
public class WritingCenter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column (name="name")
	private String name;
	
	@Column (name="idwiringcenter")
	private int idWirtingCenter;
	
	@Column(name="Floor_Building_Id")
	private int Floor_Building_Id;
	
	@Column(name="Floor_Id")
	private int Floor_Id;
	/**
	 * Método para obtener el id del wiringcenter
	 * @return El id del wiringcenter
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asignar el id del wiringcenter
	 * @param El id del wiringcenter
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el nombre del wiringcenter
	 * @return El nombre del wiringcenter
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para asignar el nombre del wiringcenter
	 * @param El nombre del wiringcenter
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Método para obtener el id del wiringcenter segun HPeIMC
	 * @return El id del wiringcenter segun HPeIMC
	 */
	public int getIdWirtingCenter() {
		return idWirtingCenter;
	}
	/**
	 * Método para asignar el id del wiringcenter segun HPeIMC
	 * @param El id del wiringcenter segun HPeIMC
	 */
	public void setIdWirtingCenter(int idWirtingCenter) {
		this.idWirtingCenter = idWirtingCenter;
	}
	/**
	 * Método para obtener el id del edificio
	 * @return El id del edificio
	 */
	public int getFloor_Building_Id() {
		return Floor_Building_Id;
	}
	/**
	 * Método para asignar el id del edificio
	 * @param El id del edificio
	 */
	public void setFloor_Building_Id(int floor_Building_Id) {
		Floor_Building_Id = floor_Building_Id;
	}
	/**
	 * Método para obtener el id del piso
	 * @return El id del piso
	 */
	public int getFloor_Id() {
		return Floor_Id;
	}
	/**
	 * Método para asignar el id del piso
	 * @param El id del piso
	 */
	public void setFloor_Id(int floor_Id) {
		Floor_Id = floor_Id;
	}

	/**
	  * Método para imprimir la instancia de la clase wiringcenter
	  * 
	  */
	public String toString() {
		return "WritingCenter [id=" + id + ", name=" + name + ", idWirtingCenter=" + idWirtingCenter
				+ ", Floor_Building_Id=" + Floor_Building_Id + ", Floor_Id=" + Floor_Id + "]";
	}


	
}
