package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de agregar un edificio
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeCreateBuilding {
	/**
	 * Método para agregar un edificio 
	 * @param Email correo de quien esta haciendo la acción
	 * @param add la información del nuevo edificio
	 * @return si la función funciono o no 
	 */
	public Request CreateBuilding(String correo, AddBuilding add);
	
}
