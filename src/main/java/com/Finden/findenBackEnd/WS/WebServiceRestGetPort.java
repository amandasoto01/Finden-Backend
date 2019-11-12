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

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.AllInfoPort;
import com.Finden.findenBackEnd.models.service.FacadeGetPort;
/**
 * Esta clase es la encargada de recibir las peticiones de información de los puertos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestGetPort {

	@Autowired
	private FacadeGetPort FindPort;
	/**
	 * Método para obtener toda la información un puerto con la integración a HPeIMC
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a buscar
	 * @return Toda la información de un usuario
	 */
	@PostMapping("/findPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public AllInfoPort findPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return FindPort.FindPort(email, port);
	}
	/**
	 * Método para obtener toda la información un puerto en el sistema
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a buscar
	 * @return Toda la información de un usuario
	 */
	@PostMapping("/getPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public AddPort getPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return FindPort.getPort(email,port);
	}
}
