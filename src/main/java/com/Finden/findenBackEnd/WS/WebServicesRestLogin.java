package com.Finden.findenBackEnd.WS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.service.FacadeLogin;
/**
 * Esta clase es la encargada de recibir las peticiones de ingreso al sistema
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServicesRestLogin {

	@Autowired
	private FacadeLogin login;
	/**
	 * Método para obtener el tipo de un usurio si este existe
	 * @param usuario correo de quien esta haciendo la acción
	 * @return Tipo de usuario
	 */
	@PostMapping("/login")
	@CrossOrigin(origins = "*")
	public Request login(@RequestBody User user) {
		return login.Login(user);
	}
	
}
