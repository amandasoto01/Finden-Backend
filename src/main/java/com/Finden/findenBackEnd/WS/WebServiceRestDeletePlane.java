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
import com.Finden.findenBackEnd.models.service.FacadeDeletePlane;
/*
*Sentencencia que se utiliza para crear el servicio que recibe una respuesta HTTP buscando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de elimianr plano
*/
public class WebServiceRestDeletePlane {
	/*
	*Instancia de la logica de negocio que se utiliza para presentar la pantalla de eliminar un plano
	*/
	@Autowired
	private FacadeDeletePlane deletePlane;
	/*
	*Servicio DELETE hacia el formulario deletePlane
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de infromar acerca de elimiancioón correcta del plano
	*/
	@PostMapping("/deletePlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public Request DeletePlane(@RequestHeader("Email") String email,@RequestBody String NamePlane) {
		return deletePlane.DeletePlane(email,NamePlane);
	}
}
