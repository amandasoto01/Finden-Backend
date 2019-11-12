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
import com.Finden.findenBackEnd.models.service.FacadeDeleteUser;
/**
 * Esta clase es la encargada de recibir las peticiones de eliminar un usuario
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestDeleteUser {

	@Autowired
	private FacadeDeleteUser delete;
	/**
	 * Método para Eliminar un puerto 
	 * @param Email correo de quien esta haciendo la acción
	 * @param correo email del usuario a eliminar
	 * @return si la función funciono o no 
	 */
	@PostMapping("/deleteUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request DeleteUser(@RequestHeader("Email") String email,@RequestBody String correo) {
		return delete.Delete(correo, email);
	}

}
