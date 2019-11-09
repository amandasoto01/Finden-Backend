package com.Finden.findenBackEnd.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Finden.findenBackEnd.models.entity.Correction;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.service.FacadeRecoverPassword;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestRecoverPassword {

	@Autowired
	private FacadeRecoverPassword recover;
	
	@PostMapping("/send")
	@CrossOrigin(origins = "*")
	public Request Send(@RequestBody User user) {
		return recover.Send(user);
	}
		
	@PostMapping("/password")
	@CrossOrigin(origins = "*")
	public Request Correction(@RequestBody Correction nuevo) {
		return recover.CorrectPassword(nuevo);
	}
}
