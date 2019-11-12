package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.PortDAO;
import com.Finden.findenBackEnd.models.dao.SwitchDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.dao.WritingCenterDAO;
import com.Finden.findenBackEnd.models.entity.Building;
import com.Finden.findenBackEnd.models.entity.Floor;
import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.Port;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.Switch;
import com.Finden.findenBackEnd.models.entity.UpdatePort;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.WritingCenter;
/**
 * Esta clase es la logica de negocio de modificar un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeUpdatePortImpl implements FacadeUpdatePort{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PortDAO  portDAO;
	
	@Autowired
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	
	@Autowired
	private WritingCenterDAO wcDAO;
	
	@Autowired
	private SwitchDAO  switchDAO;
	/**
	 * Método para modificar un puerto
	 * @param Email correo de quien esta haciendo la acción
	 * @param updatePort información del puerto a modificar
	 * @return si el servicio funciono o no 
	 */
	@Transactional
	public Request UpdatePort(String email,UpdatePort updatePort) {
		Request res = new Request();
		Port port;
		Port newPort= new Port();
		if(Check(email, 1)) {
			if(updatePort.getPort()==null) {
				res.setRequest(false);
				res.setRes("No se ingreso un puerto a modificar");
				return res;
			}else {
				port= portDAO.findByName(updatePort.getPort());
				if(port==null) {
					res.setRequest(false);
					res.setRes("No se encontro el puerto: "+updatePort.getPort());
					return res;
				}else {
					if(updatePort.getBuilding()!=null) {
						Building b= new Building();
						b.setId(null);
						b.setName(null);
						b.setNumber(updatePort.getBuilding());
						List<Building> bu= new ArrayList<Building>();
						Iterable<Building>I;
						Example<Building>buildingExample=Example.of(b);
						I=buildingDAO.findAll(buildingExample);
						for(Building build:I) {
							bu.add(build);
						}
						if(bu.size()>0) {
							newPort.setFloor_Building_Id(bu.get(0).getId());
						}else {
							res.setRequest(false);
							res.setRes("No se encontro el edificio: "+ updatePort.getBuilding());
							return res;
						}
					}else {
						newPort.setFloor_Building_Id(port.getFloor_Building_Id());
					}
					if(updatePort.getFloor()!=null) {
						Floor f= new Floor();
						f.setBuilding_Id(newPort.getFloor_Building_Id());
						f.setNumber(updatePort.getFloor());
						f.setId(null);
						List<Floor> fl= new ArrayList<Floor>();
						Iterable<Floor>F;
						Example<Floor>floorExample=Example.of(f);
						F=floorDAO.findAll(floorExample);
						for(Floor floo:F) {
							fl.add(floo);
						}
						if(fl.size()>0) {
							newPort.setFloor_Id(fl.get(0).getId());
						}else {
							res.setRequest(false);
							res.setRes("No se encontro el piso: "+updatePort.getFloor());
							return res;
						}
					}else {
						newPort.setFloor_Id(port.getFloor_Id());
					}
					if(updatePort.getName()!=null) {
						newPort.setName(updatePort.getName());
					}else {
						newPort.setName(port.getName());
					}
					if(updatePort.getNPortSwitch()!=null) {
						newPort.setPortInSwitch(updatePort.getNPortSwitch());
					}else {
						newPort.setPortInSwitch(port.getPortInSwitch());
					}
					if(updatePort.getType()!=null) {
						if(CheckNamePort(updatePort.getType())!=0){
							newPort.setType(CheckNamePort(updatePort.getType()));
						}else {
							res.setRequest(false);
							res.setRes("No es valido el tipo de puerto recuerde que son: V,D y VD");
							return res;
						}
					}else {
						newPort.setType(port.getType());
					}
					if(updatePort.getWiringCenter()!=null) {
						WritingCenter wc;
						wc= wcDAO.findByName(updatePort.getWiringCenter());
						if(wc!=null) {
							newPort.setSwitch_WritingCenter_id(wc.getId());
						}else {
							res.setRequest(false);
							res.setRes("No se encontro el centro de cableado: "+updatePort.getWiringCenter());
							return res;
						}
					}else {
						newPort.setSwitch_WritingCenter_id(port.getSwitch_WritingCenter_id());
					}
					if(updatePort.getSwitch()!=null) {
						Switch s = new Switch();
						List<Switch> u= new ArrayList<Switch>();
						Iterable<Switch>SI;
						s.setWritingCenter_id(newPort.getSwitch_WritingCenter_id());
						s.setNumeroSwitch(updatePort.getSwitch());
						s.setIndex(null);
						s.setId(null);
						Example<Switch>switchExample=Example.of(s);
						SI=switchDAO.findAll(switchExample);
						for(Switch swit:SI) {
							u.add(swit);
						}
						if(u.size()>0) {
						newPort.setSwitch_id(u.get(0).getId());
						}else {
							res.setRequest(false);
							res.setRes("No se encontro el switch");
							return res;
						}
					}else {
						newPort.setSwitch_id(port.getSwitch_id());
					}
					newPort.setId(port.getId());
					portDAO.save(newPort);
					res.setRequest(true);
					res.setRes("Puerto actualizado con exito");
					return res;
				}
				
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene permiso para realizar esta acción");
			return res;
		}
		
	}
	/**
	 * Método para modificar una lista de puertos
	 * @param Email correo de quien esta haciendo la acción
	 * @param listPorts lista con información de los puertos a modificar
	 * @return si el servicio funciono o no 
	 */
	@Transactional
	public String Switches(String email,ListPorts listports) {
		if(Check(email, 1)) {
			String res="",aux;
			for (int i = 0; i < listports.getPorts().size(); i++) {
				listports.getPorts().get(i).setBuilding(listports.getBuilding());
				listports.getPorts().get(i).setFloor(listports.getFloor());
				aux=this.UpdatePort(email, listports.getPorts().get(i)).getRes();
				if(!aux.trim().matches("Puerto actualizado con exito")) {
					res+="Problema en el puerto: "+listports.getPorts().get(i).getPort()+" error: "+aux+"\n";
				}
			}
			if(res.matches("")) {
				res="Todos los puertos han sido cargados exitosamente";
			}
			return res;
		}else {
			return "El usuario no tiene permiso para realizar esta acción";
		}
	}
	/**
	 * Método que verifica si un usuario es un tipo en especifico
	 * @param Email- correo de quien esta haciendo la acción 
	 * @param i - El tipo de usuario donde 1 es DTI, 2 es mesa de servicio, 3 es contratista
	 * @return Si el usuario es de un tipo o no
	 */
	private boolean Check(String email, int i) {
		User us = new User();
		List<User> u= new ArrayList<User>();
		Iterable<User>I;
		us.setEmail(email);
		us.setName(null);
		us.setType(null);
		us.setPassword(null);
		Example<User>userExample=Example.of(us);
		I=userDAO.findAll(userExample);
		for(User usu:I) {
			u.add(usu);
		}
		if(u.size()>0) {
			if(u.get(0).getType()==i) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	/**
	 * Método que verifica que tipo de puerto tiene
	 * @param name el puerto para comvertir un tipo en numero 
	 * @return el tipo en numero para guardarlo en el sistema
	 */
	private int CheckNamePort(String name) {
		if(name.equals("V")) {
			return 1;
		}else if(name.equals("D")) {
			return 2;
		}else if(name.equals("VD")){
			return 3;
		}else {
			return 0;
		}
	}

}
