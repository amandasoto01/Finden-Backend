package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Correction;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la interface para la logica de negocio cambio de contraseña
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeRecoverPassword {
	/**
	 * Método para generar un codigo con el cual se genera el cambio de la contraseña
	 * @param user correo de quien esta haciendo la acción
	 * @return si el servicio funciono o no
	 */
	public Request Send(User user);
	/**
	 * Método para hacer el cambio de contraseña
	 * @param user información para la generación de la nueva contraseña 
	 * @return si el servicio funciono o no
	 */
	public Request CorrectPassword (Correction nuevo);

}
