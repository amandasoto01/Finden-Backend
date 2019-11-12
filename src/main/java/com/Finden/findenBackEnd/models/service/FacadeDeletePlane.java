package com.Finden.findenBackEnd.models.service;

/**
 * Esta clase es la interface para la logica de negocio de eliminar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeDeletePlane {
	/**
	 * Método para Eliminar un plano 
	 * @param Email correo de quien esta haciendo la acción
	 * @param NamePlane nombre del plano a eliminar
	 * @return si la función funciono o no 
	 */
	public Request DeletePlane(String Email,String NamePlane);

}
