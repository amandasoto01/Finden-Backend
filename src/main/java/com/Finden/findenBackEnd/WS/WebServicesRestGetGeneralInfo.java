package com.Finden.findenBackEnd.WS;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Finden.findenBackEnd.models.entity.*;
import com.Finden.findenBackEnd.models.service.FacadeGetGeneralInfo;
/**
 * Esta clase es la encargada de recibir las peticiones de información general del sistema
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class WebServicesRestGetGeneralInfo {
	
	@Autowired
	private FacadeGetGeneralInfo GeneralService;
	/**
	 * Método para obtener los usuarios
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los usuarios registrados en el sistema
	 */
	@GetMapping("/getUsers")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoUser>GetUsers(@RequestHeader("Email") String email) {
		return GeneralService.GetUsers(email);
	}
	/**
	 * Método para obtener los edificios
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los edificios registrados en el sistema
	 */
	@GetMapping("/getBuildings")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<SendInfoBuildng>GetBuildings(@RequestHeader("Email") String email) {
		return GeneralService.GetBuildings(email);
	}
	/**
	 * Método para obtener los pisos
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los pisos registrados en el sistema
	 */
	@PostMapping("/getFloors")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<Integer>GetFloors(@RequestHeader("Email") String email,@RequestBody String building) {
		return GeneralService.GetFloors(email,building);
	}
	/**
	 * Método para obtener toda la información un usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario a buscar
	 * @return Toda la información de un usuario
	 */
	@PostMapping("/getUser")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public User GetUser(@RequestHeader("Email") String email,@RequestBody String user) {
		return GeneralService.GetUser(email,user);
	}
	/**
	 * Método para obtener los centros de cableado
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los centros de cableado registrados en el sistema
	 */
	@GetMapping("/getWiringCenter")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<String> GetWritingCenter(@RequestHeader("Email") String email) {
		return GeneralService.GetWritingCenter(email);
	}
	/**
	 * Método para obtener los switches de un centro de cableado
	 * @param Email correo de quien esta haciendo la acción
	 * @param Wc nombre del centro de cableado a buscar
	 * @return Los pisos registrados en el sistema
	 */
	@PostMapping("/getSwitches")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<Integer> getSwitches(@RequestHeader("Email") String email,@RequestBody String Wc) {
		return GeneralService.getSwitches(email,Wc);
	}
	/**
	 * Método para obtener el nombre del usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @return El nombre del usuario
	 */
	@PostMapping("/getUsername")
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.OK)
	public String getUsername(@RequestBody String email) {
		return GeneralService.getUsername(email);
	}
	
}

