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
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeAddPlane;
/*
*Setencencia que se utiliza para crear el servicio que recibe una respuesta HTTP busacando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de añadir plano
*/
public class WebServiceRestAddPlane {
	/*
	*Atributo de donde se extrae la logica necesaria para presentar la pantalla de añadir plano
	*/
	@Autowired
	private FacadeAddPlane addPlane;
	/*
	*Servicio POST hacia el formulario addPlane
	*/
	@PostMapping("/addPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request AddPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File,@RequestHeader("description")String Description) {
		return addPlane.AddPLane(email, File,Description); 
	}

}
