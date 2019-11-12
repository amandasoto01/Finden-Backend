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

import com.Finden.findenBackEnd.models.entity.ApprovePlane;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeUpdatePlane;
/**
 * Esta clase es la encargada de recibir las peticiones de modificación de los planos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestUpdatePlane {
	
	@Autowired
	private FacadeUpdatePlane updatePlane;
	/**
	 * Método para aprovar o rechazar un plano
	 * @param Email correo de quien esta haciendo la acción
	 * @param approvePlane nombre del plano y si se recazo o aprovo
	 * @return si el servicio funciono o no 
	 */
	@PostMapping("/approve")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request ApprovePlane(@RequestHeader("Email") String email,@RequestBody ApprovePlane approveplane) {
		return updatePlane.ApprovePlane(email, approveplane);
	}

}
