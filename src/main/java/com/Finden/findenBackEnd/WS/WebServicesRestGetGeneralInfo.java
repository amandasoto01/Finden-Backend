package com.Finden.findenBackEnd.WS;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Finden.findenBackEnd.models.entity.*;
import com.Finden.findenBackEnd.models.service.FacadeGetGeneralInfo;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServicesRestGetGeneralInfo {
	
	@Autowired
	private FacadeGetGeneralInfo GeneralService;
	
	@GetMapping("/getUsers")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoUser>GetUsers(@RequestHeader("Email") String email) {
		return GeneralService.GetUsers(email);
	}
	
	@GetMapping("/getBuildings")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoBuildng>GetBuildings(@RequestHeader("Email") String email) {
		return GeneralService.GetBuildings(email);
	}
	
	@PostMapping("/getFloors")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<Integer>GetFloors(@RequestHeader("Email") String email,@RequestBody String building) {
		return GeneralService.GetFloors(email,building);
	}
	
	@PostMapping("/getUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public User GetUser(@RequestHeader("Email") String email,@RequestBody String user) {
		return GeneralService.GetUser(email,user);
	}
	
	@GetMapping("/getWiringCenter")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<String> GetWritingCenter(@RequestHeader("Email") String email) {
		return GeneralService.GetWritingCenter(email);
	}
	
	@PostMapping("/getSwitches")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<Integer> getSwitches(@RequestHeader("Email") String email,@RequestBody String Wc) {
		return GeneralService.getSwitches(email,Wc);
	}
	
	@PostMapping("/getUsername")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public String getUsername(@RequestBody String email) {
		return GeneralService.getUsername(email);
	}
	
}

