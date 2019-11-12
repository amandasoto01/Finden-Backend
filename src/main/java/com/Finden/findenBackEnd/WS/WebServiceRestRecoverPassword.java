package com.Finden.findenBackEnd.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Finden.findenBackEnd.models.entity.Correction;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.service.FacadeRecoverPassword;
/**
 * Esta clase es la encargada de recibir las peticiones de cambio de contraseña
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestRecoverPassword {

	@Autowired
	private FacadeRecoverPassword recover;
	/**
	 * Método para generar un codigo con el cual se genera el cambio de la contraseña
	 * @param user correo de quien esta haciendo la acción
	 * @return si el servicio funciono o no
	 */
	@PostMapping("/send")
	@CrossOrigin(origins = "*")
	public Request Send(@RequestBody User user) {
		return recover.Send(user);
	}
	/**
	 * Método para hacer el cambio de contraseña
	 * @param user información para la generación de la nueva contraseña 
	 * @return si el servicio funciono o no
	 */
	@PostMapping("/password")
	@CrossOrigin(origins = "*")
	public Request Correction(@RequestBody Correction nuevo) {
		return recover.CorrectPassword(nuevo);
	}
}
