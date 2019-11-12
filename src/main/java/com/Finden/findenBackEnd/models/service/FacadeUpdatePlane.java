package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.ApprovePlane;
import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de modificar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeUpdatePlane {
	/**
	 * Método para aprovar o rechazar un plano
	 * @param Email correo de quien esta haciendo la acción
	 * @param approvePlane nombre del plano y si se recazo o aprovo
	 * @return si el servicio funciono o no 
	 */
	public Request ApprovePlane(String email,ApprovePlane approvePlane);

}
