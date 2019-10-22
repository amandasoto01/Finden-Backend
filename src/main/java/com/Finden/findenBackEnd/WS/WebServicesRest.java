package com.Finden.findenBackEnd.WS;


import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.*;
import com.Finden.findenBackEnd.models.service.FacadeContratista;
import com.Finden.findenBackEnd.models.service.FacadeDTI;
import com.Finden.findenBackEnd.models.service.FacadeGeneral;
import com.Finden.findenBackEnd.models.service.FacadeMesaDeServicio;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServicesRest {
	
	@Autowired
	private FacadeGeneral generalService;
	
	@Autowired
	private FacadeDTI dtiService;
	
	@Autowired
	private FacadeContratista ContratistaService;
	
	@Autowired
	private FacadeMesaDeServicio mesaDeServicioServices;
	
	@PostMapping("/login")
	public String login(@RequestBody User usuario) {
		return generalService.Login(usuario);
	}
	
	@PostMapping("/send")
	public String Send(@RequestBody String correo) {
		return generalService.Enviar(correo);
	}
		
	@PostMapping("/password")
	public String Correction(@RequestBody Correction nuevo) {
		return generalService.Correguir(nuevo);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return dtiService.Create(usuario, email);
	}
	
	@PutMapping("/updateUser")
	@ResponseStatus(HttpStatus.CREATED)
	public String UpdateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return dtiService.UpdateUser(usuario, email);
	}

	@DeleteMapping("/deleteUser")
	@ResponseStatus
	public String DeleteUser(@RequestHeader("Email") String email,@RequestBody String correo) {
		return dtiService.Delete(correo, email);
	}
	
	@PostMapping("/addBuilding")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreatBuilding(@RequestHeader("Email") String email,@RequestBody AddBuilding add) {
		return dtiService.CreateBuilding(email,add);
	}
	@PostMapping("/addWiringCenter")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreateWieringCenter(@RequestHeader("Email") String email,@RequestBody Addwritingcenter add) {
		return dtiService.CreateWiringCenter(email,add);
	}
	
	@PostMapping("/addPort")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreatePort(@RequestHeader("Email") String email,@RequestBody AddPort add) {
		return dtiService.CreatePort(email,add);
	}
	
	@DeleteMapping("/deletePort")
	@ResponseStatus
	public String DeletePort(@RequestHeader("Email") String email,@RequestBody String port) {
		return dtiService.DeletePort(port, email);
	}
	
	@PutMapping("/updatePort")
	@ResponseStatus(HttpStatus.CREATED)
	public String UpdatePort(@RequestHeader("Email") String email,@RequestBody UpdatePort updatePort) {
		return dtiService.UpdatePort(email, updatePort);
	}
	@GetMapping("/checkPlane")
	public String CheckPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File) {
		return ContratistaService.CheckPlane(email,File);
	}
	@PostMapping("/addPlane")
	@ResponseStatus(HttpStatus.CREATED)
	public String AddPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File, String Description) {
		return ContratistaService.AddPLane(email, File,Description); 
	}
	@DeleteMapping("/deletePlane")
	@ResponseStatus
	public String DeletePlane(@RequestHeader("Email") String email,@RequestBody String NamePlane) {
		return ContratistaService.DeletePlane(email,NamePlane);
	}
	
	@GetMapping("/findPort")
	public SendPort findPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return mesaDeServicioServices.FindPort(email, port);
	}
	
	@PutMapping("/approve")
	@ResponseStatus(HttpStatus.CREATED)
	public String ApprovePlane(@RequestHeader("Email") String email,@RequestBody ApprovePlane approveplane) {
		return dtiService.ApprovePlane(email, approveplane);
	}
	@GetMapping("/getPlane")
	public File GetPlane(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return dtiService.GetPlane(email, plane);
	}
	
	@GetMapping("/planePorts")
	public ArrayList<PortList> GetPlanePorts(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return dtiService.GetPlanePorts(email, plane);
	}
	
	@PutMapping("/switches")
	public String Switches(@RequestHeader("Email") String email,@RequestBody ListPorts listPorts) {
		return dtiService.Switches(email,listPorts);
	}

	@GetMapping("/history")
	public ArrayList<HistorialPlane>Historial(@RequestHeader("Email") String email,@RequestBody String plane) {
		return dtiService.Historial(email, plane);
	}
	
	@GetMapping("/getApprovedPlanes")
	public ArrayList<SendInfoPlane>GetApproved(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetApproved(email, user);
	}
	
	@GetMapping("/getRejectedPlanes")
	public ArrayList<SendInfoPlane>GetRejected(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetRejected(email, user);
	}
	
	@GetMapping("/getAllPlanes")
	public ArrayList<SendInfoPlane>GetAllPlanes(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetAllPlanes(email, user);
	}
	
	@GetMapping("/getUsers")
	public ArrayList<SendInfoUser>GetUsers(@RequestHeader("Email") String email) {
		return dtiService.GetUsers(email);
	}
	
	@GetMapping("/getBuildings")
	public ArrayList<SendInfoBuildng>GetBuildings(@RequestHeader("Email") String email) {
		return dtiService.GetBuildings(email);
	}
}

