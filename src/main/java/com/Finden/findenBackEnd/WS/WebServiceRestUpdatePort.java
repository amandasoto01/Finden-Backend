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

import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.UpdatePort;
import com.Finden.findenBackEnd.models.service.FacadeUpdatePort;
/**
 * Esta clase es la encargada de recibir las peticiones de modificación de los puertos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestUpdatePort {
	
	@Autowired
	private FacadeUpdatePort update;
	/**
	 * Método para modificar un puerto
	 * @param Email correo de quien esta haciendo la acción
	 * @param updatePort información del puerto a modificar
	 * @return si el servicio funciono o no 
	 */
	@PostMapping("/updatePort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request UpdatePort(@RequestHeader("Email") String email,@RequestBody UpdatePort updatePort) {
		return update.UpdatePort(email, updatePort);
	}
	/**
	 * Método para modificar una lista de puertos
	 * @param Email correo de quien esta haciendo la acción
	 * @param listPorts lista con información de los puertos a modificar
	 * @return si el servicio funciono o no 
	 */
	@PostMapping("/switches")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public String Switches(@RequestHeader("Email") String email,@RequestBody ListPorts listPorts) {
		return update.Switches(email,listPorts);
	}

}
