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
import com.Finden.findenBackEnd.models.service.FacadeUpdateUser;
/**
 * Esta clase es la encargada de recibir las peticiones de modificación de los usuarios
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestUpdateUser {
	
	@Autowired
	private FacadeUpdateUser updateUser;
	/**
	 * Método para modificar un usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del usario a modificar
	 * @return si el servicio funciono o no 
	 */
	@PostMapping("/updateUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request UpdateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return updateUser.UpdateUser(usuario, email);
	}

}
