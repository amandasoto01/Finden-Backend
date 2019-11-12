package com.Finden.findenBackEnd.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeDeletePlane;
/**
 * Esta clase es la encargada de recibir las peticiones de eliminar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestDeletePlane {

	@Autowired
	private FacadeDeletePlane deletePlane;
	/**
	 * Método para Eliminar un plano 
	 * @param Email correo de quien esta haciendo la acción
	 * @param NamePlane nombre del plano a eliminar
	 * @return si la función funciono o no 
	 */
	@PostMapping("/deletePlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request DeletePlane(@RequestHeader("Email") String email,@RequestBody String NamePlane) {
		return deletePlane.DeletePlane(email,NamePlane);
	}
}
