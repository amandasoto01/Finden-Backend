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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestCreatePort {

	@Autowired
	private FacadeCreatePort createPort;
	
	@PostMapping("/addPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreatePort(@RequestHeader("Email") String email,@RequestBody AddPort add) {
		return createPort.CreatePort(email,add);
	}
	
	@PostMapping("/planePorts")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ArrayList<PortList> GetPlanePorts(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return createPort.GetPlanePorts(email, plane);
	}
	
	@PostMapping("/getPortsFloor/{edificio}/{piso}")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<PortList> getPortsFloor(@RequestHeader("Email") String email,@PathVariable Integer edificio,@PathVariable  Integer piso ) {
		return createPort.getPortsFloor(email,edificio,piso);
	}
}
