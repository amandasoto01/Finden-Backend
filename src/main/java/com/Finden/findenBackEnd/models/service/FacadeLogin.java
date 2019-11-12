package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la interface para la logica de negocio del login al sistema
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeLogin {
	/**
	 * Método para obtener el tipo de un usurio si este existe
	 * @param usuario correo de quien esta haciendo la acción
	 * @return Tipo de usuario
	 */
	public Request Login(User usuario);
	
}
