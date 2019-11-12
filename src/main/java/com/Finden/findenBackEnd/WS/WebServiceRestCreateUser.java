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
/*
*Sentencencia que se utiliza para crear el servicio que recibe una respuesta HTTP buscando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de crear usuario
*/
public class WebServiceRestCreateUser {
	/*
	*Instancia de la logica de negocio que se utiliza para presentar la pantalla de crear usuario
	*/
	@Autowired
	private FacadeCreateUser user;
	/*
	*Servicio POST hacia el formulario create
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de determinar el tipo de servicio que se va a usar, crear un usuario
	*/
	@PostMapping("/create")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public Request CreateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return user.Create(usuario, email);
	}

}
