package com.Finden.findenBackEnd.WS;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@CrossOrigin(origins = "*")
	public Request login(@RequestBody User user) {
		return generalService.Login(user);
	}
	
	@PostMapping("/send")
	@CrossOrigin(origins = "*")
	public Request Send(@RequestBody User user) {
		return generalService.Enviar(user);
	}
		
	@PostMapping("/password")
	@CrossOrigin(origins = "*")
	public Request Correction(@RequestBody Correction nuevo) {
		return generalService.Correguir(nuevo);
	}
	
	@PostMapping("/create")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return dtiService.Create(usuario, email);
	}
	@PostMapping("/updateUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request UpdateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return dtiService.UpdateUser(usuario, email);
	}

	@PostMapping("/deleteUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request DeleteUser(@RequestHeader("Email") String email,@RequestBody String correo) {
		return dtiService.Delete(correo, email);
	}
	
	@PostMapping("/addBuilding")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreatBuilding(@RequestHeader("Email") String email,@RequestBody AddBuilding add) {
		return dtiService.CreateBuilding(email,add);
	}
	@PostMapping("/addWiringCenter")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreateWieringCenter(@RequestHeader("Email") String email,@RequestBody Addwritingcenter add) {
		System.out.println(add.getSwitches().toString());
		return dtiService.CreateWiringCenter(email,add);
	}
	
	@PostMapping("/addPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CreatePort(@RequestHeader("Email") String email,@RequestBody AddPort add) {
		return dtiService.CreatePort(email,add);
	}
	
	@PostMapping("/deletePort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request DeletePort(@RequestHeader("Email") String email,@RequestBody String port) {
		return dtiService.DeletePort(port, email);
	}
	
	@PostMapping("/updatePort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request UpdatePort(@RequestHeader("Email") String email,@RequestBody UpdatePort updatePort) {
		return dtiService.UpdatePort(email, updatePort);
	}
	@PostMapping("/checkPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request CheckPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File) {
		return ContratistaService.CheckPlane(email,File);
	}
	@PostMapping("/addPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request AddPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File,@RequestHeader("description")String Description) {
		return ContratistaService.AddPLane(email, File,Description); 
	}
	@PostMapping("/deletePlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request DeletePlane(@RequestHeader("Email") String email,@RequestBody String NamePlane) {
		return ContratistaService.DeletePlane(email,NamePlane);
	}
	
	@PostMapping("/findPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public SendPort findPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return mesaDeServicioServices.FindPort(email, port);
	}
	
	@PostMapping("/approve")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Request ApprovePlane(@RequestHeader("Email") String email,@RequestBody ApprovePlane approveplane) {
		return dtiService.ApprovePlane(email, approveplane);
	}
	@PostMapping("/getPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resource> GetPlane(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return dtiService.GetPlane(email, plane);
	}
	
	@GetMapping("/planePorts")
	@CrossOrigin(origins = "*")
	public ArrayList<PortList> GetPlanePorts(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return dtiService.GetPlanePorts(email, plane);
	}
	
	@PutMapping("/switches")
	@CrossOrigin(origins = "*")
	public String Switches(@RequestHeader("Email") String email,@RequestBody ListPorts listPorts) {
		return dtiService.Switches(email,listPorts);
	}

	@GetMapping("/history")
	@CrossOrigin(origins = "*")
	public ArrayList<HistorialPlane>Historial(@RequestHeader("Email") String email,@RequestBody String plane) {
		return dtiService.Historial(email, plane);
	}
	
	@GetMapping("/getApprovedPlanes")
	@CrossOrigin(origins = "*")
	public ArrayList<SendInfoPlane>GetApproved(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetApproved(email, user);
	}
	
	@GetMapping("/getRejectedPlanes")
	@CrossOrigin(origins = "*")
	public ArrayList<SendInfoPlane>GetRejected(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetRejected(email, user);
	}
	
	@GetMapping("/getAllPlanes")
	@CrossOrigin(origins = "*")
	public ArrayList<SendInfoPlane>GetAllPlanes(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetAllPlanes(email, user);
	}
	
	@PostMapping("/getAllPlanesDTI")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ArrayList<SendInfoPlane>GetAllPlanesDTI(@RequestHeader("Email") String email,@RequestBody SendInfoPlane user) {
		return dtiService.GetAllPlanesDTI(email, user);
	}
	
	@GetMapping("/getUsers")
	@CrossOrigin(origins = "*")
	public ArrayList<SendInfoUser>GetUsers(@RequestHeader("Email") String email) {
		return dtiService.GetUsers(email);
	}
	
	@GetMapping("/getBuildings")
	@CrossOrigin(origins = "*")
	public ArrayList<SendInfoBuildng>GetBuildings(@RequestHeader("Email") String email) {
		return dtiService.GetBuildings(email);
	}
	
	@PostMapping("/getFloors")
	@CrossOrigin(origins = "*")
	public ArrayList<Integer>GetFloors(@RequestHeader("Email") String email,@RequestBody String building) {
		return dtiService.GetFloors(email,building);
	}
	
	@PostMapping("/getUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public User GetUser(@RequestHeader("Email") String email,@RequestBody String user) {
		return dtiService.GetUser(email,user);
	}
	
	@GetMapping("/getWiringCenter")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ArrayList<String> GetWritingCenter(@RequestHeader("Email") String email) {
		return dtiService.GetWritingCenter(email);
	}
	
	@PostMapping("/getSwitches")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ArrayList<Integer> getSwitches(@RequestHeader("Email") String email,@RequestBody String Wc) {
		return dtiService.getSwitches(email,Wc);
	}
	
	@PostMapping("/getPort")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public AddPort getPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return dtiService.getPort(email,port);
	}
	
	@PostMapping("/getPortsFloor/{edificio}/{piso}")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public ArrayList<PortList> getPortsFloor(@RequestHeader("Email") String email,@PathVariable Integer edificio,@PathVariable  Integer piso ) {
		return dtiService.getPortsFloor(email,edificio,piso);
	}
	@PostMapping("/getUsername")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public String getUsername(@RequestBody String email) {
		return dtiService.getUsername(email);
	}
	@PostMapping("/planesToApprove")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer planesToApprove(@RequestHeader("Email") String email) {
		return dtiService.planesToApprove(email);
	}
}

