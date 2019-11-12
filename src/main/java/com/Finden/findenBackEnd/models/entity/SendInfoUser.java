package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información de un usuario que se le envia al front 
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class SendInfoUser {
	private String name;
	private String email;
	private String type;
	/**
	 * Método para obtener el nombre del usuario
	 * @return El nombre del usuario
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método para administrar el nombre del usuario
	 * @param El nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Método para obtener el correo del usuario
	 * @return El corre del usuario
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Método para administrar el correo del usuario
	 * @param El corre del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Método para obtener el tipo del usuario
	 * @return El tipo del usuario
	 */
	public String getType() {
		return type;
	}
	/**
	 * Método para asignar el tipo del usuario
	 * @param El tipo del usuario
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Constructor de la clase
	 * @return Una instancia de la clase con sus campos en null
	 */
	public SendInfoUser() {
		
	}
	/**
	  * Método para imprimir la instancia de la clase SendInfoUser
	  * 
	  */
	public String toString() {
		return "SendInfoUser [name=" + name + ", email=" + email + ", type=" + type + "]";
	}
	
}
