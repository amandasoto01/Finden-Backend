package com.Finden.findenBackEnd.WS;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.PortList;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.service.FacadeCreatePort;
/*
*Sentencencia que se utiliza para crear el servicio que recibe una respuesta HTTP buscando en el formulario finden
*/
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
/*
*Clase encargada de recibir las peticiones de crear puerto
*/

public class WebServiceRestCreatePort {
	/*
	*Instancia de la logica de negocio que se utiliza para presentar la pantalla de crear puerto
	*/
	@Autowired
	private FacadeCreatePort createPort;
	
	/*
	*Servicio POST hacia el formulario addPort
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de determinar el tipo de servicio que se a usar, crear un puerto
	*/
	@PostMapping("/addPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public Request CreatePort(@RequestHeader("Email") String email,@RequestBody AddPort add) {
		return createPort.CreatePort(email,add);
	}
	/*
	*Servicio POST hacia el formulario addPlane
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de determinar el tipo de servicio que se va a usar, crear un plano
	*/
	@PostMapping("/planePorts")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public ArrayList<PortList> GetPlanePorts(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return createPort.GetPlanePorts(email, plane);
	}
	/*
	*Servicio POST hacia el formulario addPlane
	*origins, habilita todos los permisos del HTTP.
	*responseStatus, Se encarga de determinar el tipo de servicio que se va a usar, crearun piso
	*/
	@PostMapping("/getPortsFloor/{edificio}/{piso}")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	/*
	*Metodo encargado de la creacion del JASON para luego enviarlo
	*/
	public ArrayList<PortList> getPortsFloor(@RequestHeader("Email") String email,@PathVariable Integer edificio,@PathVariable  Integer piso ) {
		return createPort.getPortsFloor(email,edificio,piso);
	}
}
