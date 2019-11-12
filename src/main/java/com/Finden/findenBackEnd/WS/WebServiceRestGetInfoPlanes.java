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
/**
 * Esta clase es la encargada de recibir las peticiones de información de los planos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServiceRestGetInfoPlanes {
	
	@Autowired
	private FacadeGetInfoPlanes infoPlanes;
	/**
	 * Método que genere una prevalidación del plano (numero de puertos encontrados por cada tipo)
	 * @param Email correo de quien esta haciendo la acción
	 * @param file el archivo .DXF a verificar
	 * @return el resultado de la prevalidación
	 */
	@PostMapping("/checkPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Request CheckPlane(@RequestHeader("Email") String email,@RequestBody MultipartFile File) {
		return infoPlanes.CheckPlane(email,File);
	}
	/**
	 * Método para obtener el archivo .dxf
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane información del plano a buscar
	 * @return el archivo .dxf
	 */
	@PostMapping("/getPlane")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource> GetPlane(@RequestHeader("Email") String email,@RequestBody GetPlane plane) {
		return infoPlanes.GetPlane(email, plane);
	}
	/**
	 * Método para obtener todos los planos segun un nombre
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane nombre del plano a buscar
	 * @return lista de planos
	 */
	@GetMapping("/history")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<HistorialPlane>Historial(@RequestHeader("Email") String email,@RequestBody String plane) {
		return infoPlanes.Historial(email, plane);
	}
	/**
	 * Método para obtener todos los planos aprovados por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
	@PostMapping("/getApprovedPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetApproved(@RequestHeader("Email") String email) {
		return infoPlanes.GetApproved(email, email);
	}
	/**
	 * Método para obtener todos los planos rechazados por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
	@PostMapping("/getRejectedPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetRejected(@RequestHeader("Email") String email) {
		return infoPlanes.GetRejected(email, email);
	}
	/**
	 * Método para obtener todos los planos por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
	@PostMapping("/getAllPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetAllPlanes(@RequestHeader("Email") String email) {
		return infoPlanes.GetAllPlanes(email, email);
	}
	/**
	 * Método para obtener todos los planos segun una información
	 * @param Email correo de quien esta haciendo la acción
	 * @user plane información de los planos a buscar
	 * @return lista de planos
	 */
	@PostMapping("/getAllPlanesDTI")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane>GetAllPlanesDTI(@RequestHeader("Email") String email,@RequestBody SendInfoPlane user) {
		return infoPlanes.GetAllPlanesDTI(email, user);
	}
	/**
	 * Método para obtener todos los planos aprovados segun una información
	 * @param Email correo de quien esta haciendo la acción
	 * @user plane información de los planos a buscar
	 * @return lista de planos
	 */
	@PostMapping("/getAllPlanesActual")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane> getAllPlanesActual(@RequestHeader("Email") String email,@RequestBody SendInfoPlane user) {
		return infoPlanes.getAllPlanesActual(email, user);
	}
	/**
	 * Método para obtener el numero de planos por aprovar
	 * @param Email correo de quien esta haciendo la acción
	 * @return El numero de planos por aprovar
	 */
	@PostMapping("/planesToApprove")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public Integer planesToApprove(@RequestHeader("Email") String email) {
		return infoPlanes.planesToApprove(email);
	}
	/**
	 * Método para obtener todos los planos 
	 * @param Email correo de quien esta haciendo la acción
	 * @return lista de planos
	 */
	@PostMapping("/getDTIPlanes")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoPlane> getDTIPlanes (@RequestHeader("Email") String email) {
		return infoPlanes.getDTIPlanes (email);
	}
}
