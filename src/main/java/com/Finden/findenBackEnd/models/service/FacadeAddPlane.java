package com.Finden.findenBackEnd.models.service;

import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de agregar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeAddPlane {
	/**
	 * Método para agregar un plano 
	 * @param Email- correo de quien esta haciendo la acción
	 * @param File - Archivo .DXF
	 * @param description - Breve descripción del plano
	 * @return si la función funciono o no 
	 */
	public Request AddPLane(String Email, MultipartFile file,String description);

}
