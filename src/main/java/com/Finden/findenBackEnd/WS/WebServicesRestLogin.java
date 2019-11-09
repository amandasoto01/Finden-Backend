package com.Finden.findenBackEnd.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.service.FacadeLogin;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServicesRestLogin {

	@Autowired
	private FacadeLogin login;
	
	@PostMapping("/login")
	@CrossOrigin(origins = "*")
	public Request login(@RequestBody User user) {
		return login.Login(user);
	}
	
}
