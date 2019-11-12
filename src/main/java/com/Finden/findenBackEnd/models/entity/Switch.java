package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa genera la relación del objeto Switch con la tabla Switch de la base de datos
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
@Table(name="Switch")
public class Switch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="indication")
	private Integer index;
	
	@Column(name="Writingcenter_id")
	private Integer WritingCenter_id;
	
	@Column(name="Numeroswitch")
	private Integer NumeroSwitch;
	/**
	 * Método para obtener el id del switch
	 * @return El id del switch
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asignar el id del switch
	 * @param El id del switch
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el indice del primer puerto en el switch segun HPeIMC
 	 * @return El indice del primer puerto en el switch segun HPeIMC
	 */
	public Integer getIndex() {
		return index;
	}
	/**
	 * Método para asignar el indice del primer puerto en el switch segun HPeIMC
 	 * @param El indice del primer puerto en el switch segun HPeIMC
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	/**
	 * Método para obtener el id del centro de cableado
 	 * @return El id del centro de cableado
	 */
	public Integer getWritingCenter_id() {
		return WritingCenter_id;
	}
	/**
	 * Método para asignar el id del centro de cableado
 	 * @param El id del centro de cableado
	 */
	public void setWritingCenter_id(Integer writingCenter_id) {
		WritingCenter_id = writingCenter_id;
	}
	/**
	 * Método para obtener el numero del switch
 	 * @return El numero del switch
	 */
	public Integer getNumeroSwitch() {
		return NumeroSwitch;
	}
	/**
	 * Método para asignar el numero del switch  
 	 * @param El numero del switch
	 */
	public void setNumeroSwitch(Integer numeroSwitch) {
		NumeroSwitch = numeroSwitch;
	}

	/**
	  * Método para imprimir la instancia de la clase Switch
	  * 
	  */
	public String toString() {
		return "Switch [id=" + id + ", index=" + index + ", WritingCenter_id=" + WritingCenter_id + ", NumeroSwitch="
				+ NumeroSwitch + "]";
	}

	
	
	
}