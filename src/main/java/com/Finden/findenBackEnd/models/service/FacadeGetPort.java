package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.AllInfoPort;
/**
 * Esta clase es la interface para la logica de negocio de obtener información general un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeGetPort {
	/**
	 * Método para obtener toda la información un puerto con la integración a HPeIMC
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a buscar
	 * @return Toda la información de un usuario
	 */
	public AllInfoPort FindPort(String email,String port);
	/**
	 * Método para obtener toda la información un puerto en el sistema
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a buscar
	 * @return Toda la información de un usuario
	 */
	public AddPort getPort(String email,String port);

}
