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

import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeCreateWiringCenter;
/*
*Sentencencia que se utiliza para crear el servicio que recibe una respuesta HTTP buscando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de crear centro de cableado
*/
public class WebServiceRestCreateWiringCenter {
	/*
	*Instancia de la logica de negocio que se utiliza para presentar la pantalla de crear un centro de cableado
	*/
	@Autowired
	private FacadeCreateWiringCenter createWiringCenter;
	/*
	*Servicio POST hacia el formulario addWiringCenter
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de determinar el tipo de servicio que se va a usar, crear un centro de cableado
	*/
	@PostMapping("/addWiringCenter")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public Request CreateWieringCenter(@RequestHeader("Email") String email,@RequestBody Addwritingcenter add) {
		System.out.println(add.getSwitches().toString());
		return createWiringCenter.CreateWiringCenter(email,add);
	}

}
