package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.PortList;
import com.Finden.findenBackEnd.models.entity.Request;
/**
 * Esta clase es la interface para la logica de negocio de crear puertos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FacadeCreatePort {
	/**
	 * Método para agregar un edificio 
	 * @param Email correo de quien esta haciendo la acción
	 * @param add la información del nuevo puerto
	 * @return si la función funciono o no 
	 */
	public Request CreatePort(String correo, AddPort add);
	/**
	 * Método que ve los puertos de un plano si no existen los crea
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane información del plano
	 * @return si la función funciono o no 
	 */
	public ArrayList<PortList> GetPlanePorts(String email,GetPlane plane);
	/**
	 * Método que ve los puertos de un piso en especifico
	 * @param Email correo de quien esta haciendo la acción
	 * @param edificio numero del edificio a buscar
	 * @param piso numero del  piso a buscar
	 * @return si la función funciono o no 
	 */
	public ArrayList<PortList> getPortsFloor(String email,Integer edificio, Integer piso);

}
