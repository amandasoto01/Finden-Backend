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
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.service.FacadeCreateUser;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/**
 * Esta clase es la encargada de recibir las peticiones de agregar un usuario
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public class WebServiceRestCreateUser {
	
	@Autowired
	private FacadeCreateUser user;
	/**
	 * Método para agregar un usuario 
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del usuario a agregar
	 * @return si la función funciono o no 
	 */
	@PostMapping("/create")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return user.Create(usuario, email);
	}

}
