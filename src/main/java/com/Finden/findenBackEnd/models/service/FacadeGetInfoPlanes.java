package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.HistorialPlane;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.SendInfoPlane;
/**
 * Esta clase es la interface para la logica de negocio de obtener información general de los planos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeGetInfoPlanes {
	/**
	 * Método que genere una prevalidación del plano (numero de puertos encontrados por cada tipo)
	 * @param Email correo de quien esta haciendo la acción
	 * @param file el archivo .DXF a verificar
	 * @return el resultado de la prevalidación
	 */
	public Request CheckPlane(String Email, MultipartFile file);
	/**
	 * Método para obtener el archivo .dxf
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane información del plano a buscar
	 * @return el archivo .dxf
	 */
	public ResponseEntity<Resource> GetPlane(String email,GetPlane plane);
	/**
	 * Método para obtener todos los planos segun un nombre
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane nombre del plano a buscar
	 * @return lista de planos
	 */
	public ArrayList<HistorialPlane> Historial(String email,String plane);
	/**
	 * Método para obtener todos los planos aprovados por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
	public ArrayList<SendInfoPlane> GetApproved(String email,String user);
	/**
	 * Método para obtener todos los planos rechazados por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
	public ArrayList<SendInfoPlane> GetRejected(String email,String user);
	/**
	 * Método para obtener todos los planos por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
	public ArrayList<SendInfoPlane> GetAllPlanes(String email,String user);
	/**
	 * Método para obtener todos los planos segun una información
	 * @param Email correo de quien esta haciendo la acción
	 * @user plane información de los planos a buscar
	 * @return lista de planos
	 */
	public ArrayList<SendInfoPlane>GetAllPlanesDTI(String email,SendInfoPlane plane);
	/**
	 * Método para obtener todos los planos aprovados segun una información
	 * @param Email correo de quien esta haciendo la acción
	 * @user plane información de los planos a buscar
	 * @return lista de planos
	 */
	public ArrayList<SendInfoPlane> getAllPlanesActual(String email,SendInfoPlane plane);
	/**
	 * Método para obtener el numero de planos por aprovar
	 * @param Email correo de quien esta haciendo la acción
	 * @return El numero de planos por aprovar
	 */
	public Integer planesToApprove(String email);
	/**
	 * Método para obtener todos los planos 
	 * @param Email correo de quien esta haciendo la acción
	 * @return lista de planos
	 */
	public ArrayList<SendInfoPlane> getDTIPlanes (String email);
}
