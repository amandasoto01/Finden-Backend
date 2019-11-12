package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de eliminar un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeDeletePort {
	/**
	 * Método para Eliminar un puerto 
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a eliminar
	 * @return si la función funciono o no 
	 */
	public Request DeletePort(String port, String email);

}
