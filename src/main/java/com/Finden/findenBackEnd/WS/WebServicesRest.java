package com.Finden.findenBackEnd.WS;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.ReadAutocadFile;
import com.Finden.findenBackEnd.models.entity.*;
import com.Finden.findenBackEnd.models.service.FacadeContratista;
import com.Finden.findenBackEnd.models.service.FacadeDTI;
import com.Finden.findenBackEnd.models.service.FacadeGeneral;
import com.Finden.findenBackEnd.models.service.FacadeMesaDeServicio;
import com.sun.mail.handlers.multipart_mixed;


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
	
	@PostMapping("/Login")
	public String login(@RequestBody User usuario) {
		return generalService.Login(usuario);
	}
	
	@PostMapping("/Enviar")
	public String Send(@RequestBody String correo) {
		return generalService.Enviar(correo);
	}
		
	@PostMapping("/Contrasena")
	public String Correction(@RequestBody Correction nuevo) {
		return generalService.Correguir(nuevo);
	}
	
	@PostMapping("/Create")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return dtiService.Create(usuario, email);
	}
	
	@PutMapping("/UpdateUser")
	@ResponseStatus(HttpStatus.CREATED)
	public String UpdateUser(@RequestHeader("Email") String email,@RequestBody User usuario) {
		return dtiService.UpdateUser(usuario, email);
	}

	@DeleteMapping("/DeleteUser")
	@ResponseStatus
	public String DeleteUser(@RequestHeader("Email") String email,@RequestBody String correo) {
		return dtiService.Delete(correo, email);
	}
	
	@PostMapping("/AddBuilding")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreatBuilding(@RequestHeader("Email") String email,@RequestBody AddBuilding add) {
		return dtiService.CreateBuilding(email,add);
	}
	@PostMapping("/AddWiringCenter")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreateWieringCenter(@RequestHeader("Email") String email,@RequestBody Addwritingcenter add) {
		return dtiService.CreateWiringCenter(email,add);
	}
	
	@PostMapping("/AddPuerto")
	@ResponseStatus(HttpStatus.CREATED)
	public String CreatePort(@RequestHeader("Email") String email,@RequestBody AddPort add) {
		return dtiService.CreatePort(email,add);
	}
	
	@DeleteMapping("/DeletePuerto")
	@ResponseStatus
	public String DeletePort(@RequestHeader("Email") String email,@RequestBody String port) {
		return dtiService.DeletePort(port, email);
	}
	
	@PutMapping("/UpdatePuerto")
	@ResponseStatus(HttpStatus.CREATED)
	public String UpdatePort(@RequestHeader("Email") String email,@RequestBody UpdatePort updatePort) {
		return dtiService.UpdatePort(email, updatePort);
	}
	@GetMapping("/CheckPlane")
	public String CheckPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File) {
		return ContratistaService.CheckPlane(email,File);
	}
	@PostMapping("/AddPlane")
	@ResponseStatus(HttpStatus.CREATED)
	public String AddPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File, String Description) {
		return ContratistaService.AddPLane(email, File,Description); 
	}
	@DeleteMapping("/DeletePlane")
	@ResponseStatus
	public String DeletePlane(@RequestHeader("Email") String email,@RequestBody String NamePlane) {
		return ContratistaService.DeletePlane(email,NamePlane);
	}
	
	@GetMapping("/FindPort")
	public SendPort findPort(@RequestHeader("Email") String email,@RequestBody String port) {
		return mesaDeServicioServices.FindPort(email, port);
	}
	
	@PutMapping("/Approve")
	@ResponseStatus(HttpStatus.CREATED)
	public String ApprovePlane(@RequestHeader("Email") String email,@RequestBody ApprovePlane approveplane) {
		return dtiService.ApprovePlane(email, approveplane);
	}
	
	@GetMapping("/GetPlane")
	public File GetPlane(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return dtiService.GetPlane(email, plane);
	}
	// Esto para abajo es la lectura del plano no queda asi es solo pruebas
	@PostMapping("/holis")
	public  String plano(@RequestBody MultipartFile file) {
		try {
			
			System.out.println("You successfully uploaded " + file.getOriginalFilename() + "!");
			ArrayList<String> puertos  = ReadAutocadFile.getAutocadFile(convert(file).getAbsolutePath());//se hace la lectura del plano y se guarda en una lista los puertos.
			//for (int i = 0; i <=puertos.size(); i++) {
				//System.out.println(puertos.get(i));
			//}
			Crear();
			System.out.println(guardar(file));
			return "Lectura exitosa";
        } catch (Exception e) {
        	System.err.println(e);
        	return e.toString();
        	
        }
	}


	public  File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos= new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
	public void Crear() {
		File direc= new File("C:/Users/javier/Desktop/planos");
		direc.mkdir();
	}
	public String guardar(MultipartFile f) throws IOException {
	String ruta="C:/Users/javier/Desktop/planos/"+f.getOriginalFilename();
	File archivo= new File(ruta);
	f.transferTo(archivo);
	return "exito";
	}

}

