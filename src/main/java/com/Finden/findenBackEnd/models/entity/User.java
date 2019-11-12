package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa genera la relación del objeto User con la tabla User de la base de datos
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
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="type")
	private Integer type;
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public User() {
		
	}
	/**
	 * Método para obtener el id del usario
	 * @return El id del usuario
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Método para asignar el id del usario
	 * @param El id del usuario
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Método para obtener el nombre del usario
	 * @return El nombre del usuario
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para asignar el nombre del usario
	 * @param El nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Método para obtener la contraseña del usario
	 * @return La contraseña del usuario
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Método para asignar la contraseña del usario
	 * @param La contraseña del usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Método para obtener el correo del usario
	 * @return El correo del usuario
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Método para asignar el correo del usario
	 * @param El correo del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Método para obtener el tipo del usario
	 * @return El tipo del usuario
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * Método para asignar el tipo del usario
	 * @param El tipo del usuario
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	  * Método para imprimir la instancia de la clase User
	  * 
	  */
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", type=" + type
				+ "]";
	}
	
	
}
