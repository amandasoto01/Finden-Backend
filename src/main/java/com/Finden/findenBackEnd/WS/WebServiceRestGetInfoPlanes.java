package com.Finden.findenBackEnd.WS;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.HistorialPlane;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.SendInfoPlane;
import com.Finden.findenBackEnd.models.service.FacadeGetInfoPlanes;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestGetInfoPlanes {
	
	@Autowired
	private FacadeGetInfoPlanes infoPlanes;
	
	@PostMapping("/checkPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request CheckPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File) {
		return infoPlanes.CheckPlane(email,File);
	}
	
	@PostMapping("/getPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource> GetPlane(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return infoPlanes.GetPlane(email, plane);
	}
	
	@GetMapping("/history")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<HistorialPlane>Historial(@RequestHeader("Email") String email,@RequestBody String plane) {
		return infoPlanes.Historial(email, plane);
	}
	
	@PostMapping("/getApprovedPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetApproved(@RequestHeader("Email") String email) {
		return infoPlanes.GetApproved(email, email);
	}
	
	@PostMapping("/getRejectedPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetRejected(@RequestHeader("Email") String email) {
		return infoPlanes.GetRejected(email, email);
	}
	
	@PostMapping("/getAllPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetAllPlanes(@RequestHeader("Email") String email) {
		return infoPlanes.GetAllPlanes(email, email);
	}
	
	@PostMapping("/getAllPlanesDTI")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetAllPlanesDTI(@RequestHeader("Email") String email,@RequestBody SendInfoPlane user) {
		return infoPlanes.GetAllPlanesDTI(email, user);
	}
	
	@PostMapping("/getAllPlanesActual")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane> getAllPlanesActual(@RequestHeader("Email") String email,@RequestBody SendInfoPlane user) {
		return infoPlanes.getAllPlanesActual(email, user);
	}
	
	@PostMapping("/planesToApprove")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Integer planesToApprove(@RequestHeader("Email") String email) {
		return infoPlanes.planesToApprove(email);
	}
}
