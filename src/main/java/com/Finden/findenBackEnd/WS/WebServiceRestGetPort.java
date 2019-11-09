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

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.AllInfoPort;
import com.Finden.findenBackEnd.models.service.FacadeGetPort;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestGetPort {

	@Autowired
	private FacadeGetPort FindPort;
	
	@PostMapping("/findPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public AllInfoPort findPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return FindPort.FindPort(email, port);
	}
	
	@PostMapping("/getPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public AddPort getPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return FindPort.getPort(email,port);
	}
}
