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

import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeCreateBuilding;
/*
*Sentencencia que se utiliza para crear el servicio que recibe una respuesta HTTP buscando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de crear edificio
*/
public class WebServiceRestCreateBuilding {
	/*
	*Instancia de la logica de negocio que se utiliza para presentar la pantalla de crear edificio
	*/
	@Autowired
	private FacadeCreateBuilding createBuilding;
	/*
	*Servicio POST hacia el formulario addBuilding
	*origins, habilita todos los permisos del HTTP. Se especifica en el metodo ResponseStatus el metodo que se va a utilizar
	*responseStatus, Se encarga de determinar el tipo de metodo que se va a utilizar
	*/
	@PostMapping("/addBuilding")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public Request CreatBuilding(@RequestHeader("Email") String email,@RequestBody AddBuilding add) {
		return createBuilding.CreateBuilding(email,add);
	}

}
