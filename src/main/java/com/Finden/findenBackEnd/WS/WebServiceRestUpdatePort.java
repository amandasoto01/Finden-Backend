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

import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.UpdatePort;
import com.Finden.findenBackEnd.models.service.FacadeUpdatePort;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestUpdatePort {
	
	@Autowired
	private FacadeUpdatePort update;
	
	@PostMapping("/updatePort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request UpdatePort(@RequestHeader("Email") String email,@RequestBody UpdatePort updatePort) {
		return update.UpdatePort(email, updatePort);
	}
	
	@PostMapping("/switches")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public String Switches(@RequestHeader("Email") String email,@RequestBody ListPorts listPorts) {
		return update.Switches(email,listPorts);
	}

}
