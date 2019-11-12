package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de crear un centro de cableado
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeCreateWiringCenter {
	/**
	 * Método para agregar un centro de cableado 
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del centro de cableado a agregar
	 * @return si la función funciono o no 
	 */
	public Request CreateWiringCenter(String correo, Addwritingcenter add);

}
