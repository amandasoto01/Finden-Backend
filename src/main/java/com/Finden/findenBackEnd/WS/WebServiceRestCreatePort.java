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
/**
 * Esta clase es la encargada de recibir las peticiones de agregar un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestCreatePort {

	@Autowired
	private FacadeCreatePort createPort;
	/**
	 * Método para agregar un edificio 
	 * @param Email correo de quien esta haciendo la acción
	 * @param add la información del nuevo puerto
	 * @return si la función funciono o no 
	 */
	@PostMapping("/addPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreatePort(@RequestHeader("Email") String email,@RequestBody AddPort add) {
		return createPort.CreatePort(email,add);
	}
	/**
	 * Método que ve los puertos de un plano si no existen los crea
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane información del plano
	 * @return si la función funciono o no 
	 */
	@PostMapping("/planePorts")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ArrayList<PortList> GetPlanePorts(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return createPort.GetPlanePorts(email, plane);
	}
	/**
	 * Método que ve los puertos de un piso en especifico
	 * @param Email correo de quien esta haciendo la acción
	 * @param edificio numero del edificio a buscar
	 * @param piso numero del  piso a buscar
	 * @return si la función funciono o no 
	 */
	@PostMapping("/getPortsFloor/{edificio}/{piso}")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<PortList> getPortsFloor(@RequestHeader("Email") String email,@PathVariable Integer edificio,@PathVariable  Integer piso ) {
		return createPort.getPortsFloor(email,edificio,piso);
	}
}
