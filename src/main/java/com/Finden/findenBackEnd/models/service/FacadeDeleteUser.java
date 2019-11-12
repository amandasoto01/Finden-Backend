package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de eliminar un usuario
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeDeleteUser {
	/**
	 * Método para Eliminar un puerto 
	 * @param Email correo de quien esta haciendo la acción
	 * @param correo email del usuario a eliminar
	 * @return si la función funciono o no 
	 */
	public Request Delete(String correo, String email);

}
