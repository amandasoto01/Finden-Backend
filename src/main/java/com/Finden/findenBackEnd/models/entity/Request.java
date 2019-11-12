package com.Finden.findenBackEnd.models.entity;
/**
 * Esta clase representa si la función de un servicio se ejecuto correctamente
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class Request {
private String res;
private boolean request;
/**
 * Método para obtener el respuesta del servicio
 * @return La respuesta de un servicio
 */
public String getRes() {
	return res;
}
/**
 * Método para asignar el respuesta del servicio
 * @param La respuesta de un servicio
 */
public void setRes(String res) {
	this.res = res;
}
/**
 * Método para saber si el serivicio se realizo corretamente
 * @return Boolean si se realizo o no el servicio
 */
public boolean isRequest() {
	return request;
}
/**
 * Método para asignar si el serivicio se realizo corretamente
 * @param Boolean si se realizo o no el servicio
 */
public void setRequest(boolean request) {
	this.request = request;
}
/**
 * Constructor de la clase
 * @return Una instancia de la clase con sus campos en null
 */
public Request() {
	
}
/**
 * Método para imprimir la instancia de la clase Request
 * 
 */
public String toString() {
	return "Request [res=" + res + ", request=" + request + "]";
}

}
