package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.UpdatePort;
/**
 * Esta clase es la interface para la logica de negocio de modificar un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeUpdatePort {
	/**
	 * Método para modificar un puerto
	 * @param Email correo de quien esta haciendo la acción
	 * @param updatePort información del puerto a modificar
	 * @return si el servicio funciono o no 
	 */
	public Request UpdatePort(String email,UpdatePort updatePort);
	/**
	 * Método para modificar una lista de puertos
	 * @param Email correo de quien esta haciendo la acción
	 * @param listPorts lista con información de los puertos a modificar
	 * @return si el servicio funciono o no 
	 */
	public String Switches(String email,ListPorts listports);

}
