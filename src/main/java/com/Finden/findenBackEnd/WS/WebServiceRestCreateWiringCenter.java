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

import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeCreateWiringCenter;
/**
 * Esta clase es la interface para la logica de negocio de crear un centro de cableado
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")

public class WebServiceRestCreateWiringCenter {

	@Autowired
	private FacadeCreateWiringCenter createWiringCenter;
	/**
	 * Método para agregar un centro de cableado 
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del centro de cableado a agregar
	 * @return si la función funciono o no 
	 */
	@PostMapping("/addWiringCenter")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreateWieringCenter(@RequestHeader("Email") String email,@RequestBody Addwritingcenter add) {
		System.out.println(add.getSwitches().toString());
		return createWiringCenter.CreateWiringCenter(email,add);
	}

}
