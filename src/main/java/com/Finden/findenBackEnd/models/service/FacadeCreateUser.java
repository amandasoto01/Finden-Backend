package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la interface para la logica de negocio de crear un usuarios
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeCreateUser {
	/**
	 * Método para agregar un usuario 
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del usuario a agregar
	 * @return si la función funciono o no 
	 */
	public Request Create(User usuario, String email);

}
