package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la interface para la logica de negocio de modificar un usuario
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeUpdateUser {
	/**
	 * Método para modificar un usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del usario a modificar
	 * @return si el servicio funciono o no 
	 */
	public Request UpdateUser(User usuario, String email);

}
