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
/*
*Sentencencia que se utiliza para crear el servicio que recibe una respuesta HTTP buscando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de eliminar usuario
*/
public class WebServiceRestDeleteUser {
	/*
	*Instancia de la logica de negocio que se utiliza para presentar la pantalla de eliminar usuario
	*/
	@Autowired
	private FacadeDeleteUser delete;
	/*
	*Servicio DELETE hacia el formulario create
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de informar la eliminación de correcta de un usuario
	*/
	@PostMapping("/deleteUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public Request DeleteUser(@RequestHeader("Email") String email,@RequestBody String correo) {
		return delete.Delete(correo, email);
	}

}
