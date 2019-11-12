package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.SendInfoBuildng;
import com.Finden.findenBackEnd.models.entity.SendInfoUser;

/**
 * Esta clase es la interface para la logica de negocio de obtener información general
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeGetGeneralInfo {
	
	/**
	 * Método para obtener los usuarios
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los usuarios registrados en el sistema
	 */
	public ArrayList<SendInfoUser>GetUsers(String email);
	/**
	 * Método para obtener los centros de cableado
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los centros de cableado registrados en el sistema
	 */
	public ArrayList<String> GetWritingCenter(String email);
	/**
	 * Método para obtener los edificios
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los edificios registrados en el sistema
	 */
	public ArrayList<SendInfoBuildng>GetBuildings(String email);
	/**
	 * Método para obtener los pisos
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los pisos registrados en el sistema
	 */
	public ArrayList<Integer>GetFloors(String email,String building);
	/**
	 * Método para obtener toda la información un usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario a buscar
	 * @return Toda la información de un usuario
	 */
	public User GetUser(String email,String user);
	/**
	 * Método para obtener los switches de un centro de cableado
	 * @param Email correo de quien esta haciendo la acción
	 * @param Wc nombre del centro de cableado a buscar
	 * @return Los pisos registrados en el sistema
	 */
	public ArrayList<Integer> getSwitches(String email,String Wc);
	/**
	 * Método para obtener el nombre del usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @return El nombre del usuario
	 */
	public String getUsername(String email);
}