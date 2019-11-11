package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa la información obtenida desde el front para el cambio de contraseña
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class Correction {
private String email;
private String password;
private String code;
/**
 * Método para obtener el correo de quien va a cambiar la contraseña
 * @return El correo de quien va a cambiar la contraseña
 */
public String getEmail() {
	return email;
}
/**
 * Método para asignar el correo de quien va a cambiar la contraseña
 * @param El correo de quien va a cambiar la contraseña
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * Método para obtener la nueva contraseña 
 * @return La nueva contraseña
 */
public String getPassword() {
	return password;
}
/**
 * Método para asignar la nueva contraseña 
 * @param La nueva contraseña
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * Método para obtener el codigo para la nueva contraseña 
 * @return El codigo para la nueva contraseña 
 */
public String getCode() {
	return code;
}
/**
 * Método para asignar el codigo para la nueva contraseña 
 * @param El codigo para la nueva contraseña 
 */
public void setCode(String code) {
	this.code = code;
}
}
