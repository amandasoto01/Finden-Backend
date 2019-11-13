package com.Finden.findenBackEnd.models.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.Building;
import com.Finden.findenBackEnd.models.entity.Floor;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la logica de negocio de agregar un edificio
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeCreateBuildingImpl implements FacadeCreateBuilding{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	
	/**
	 * Método para agregar un edificio 
	 * @param Email correo de quien esta haciendo la acción
	 * @param add la información del nuevo edificio
	 * @return si la función funciono o no 
	 */
	@Transactional
	public Request CreateBuilding(String correo, AddBuilding add) {
		Request res= new Request();
		boolean problem= false;
		if(Check(correo, 1)) {
			if(add.getName()==null) {
				res.setRequest(false);
				res.setRes("No se a ingresado nombre del edificio");
				return res;
			}else if(!(add.getNumber()>=0||add.getNumber()<0)) {
				res.setRequest(false);
				res.setRes("No se a ingresado el numero del edificio");
				return res;
			}else if(!(add.getNBasement()>=0||add.getNBasement()<0)) {
				res.setRequest(false);
				res.setRes("No se a ingresado el numero de sotanos");
				return res;
			}else if(!(add.getNFloors()>=0||add.getNFloors()<0)) {
				res.setRequest(false);
				res.setRes("No se a ingresado el numero de pisos");
				return res;
			}else {
				Building building = new Building();
				building.setName(add.getName());
				building.setNumber(add.getNumber());
				if(!checkBuildings(building)) {
					buildingDAO.save(building);
					List<Building> b= new ArrayList<Building>();
					Iterable<Building>I;
					Example<Building>userExample=Example.of(building);
					I=buildingDAO.findAll(userExample);
					for(Building bui:I) {
						b.add(bui);
					}
					Floor floor= new Floor();
					if(CreateFolder(building.getNumber())) {
						for (int i = 1; i <= add.getNFloors(); i++) {
								floor= new Floor();
								floor.setBuilding_Id(b.get(0).getId());
								floor.setNumber(i);
								floorDAO.save(floor);
								CreateFolderFloor(building.getNumber(),i);
						}
							for (int j = 1; j <= add.getNBasement(); j++) {
									floor= new Floor();
									floor.setBuilding_Id(b.get(0).getId());
									floor.setNumber(j*-1);
									floorDAO.save(floor);
									CreateFolderBasement(building.getNumber(),j);
							}
							if(!problem) {
								res.setRequest(true);
								res.setRes("Edificio agregado con exito");
								return res;
							}else {
								res.setRequest(false);
								res.setRes("La ruta para guardar los planos de los sotanos tienen problemas");
								return res;
							}
					}else {
						res.setRequest(false);
						res.setRes("La ruta para guardar los planos del edificio tiene problemas");
						return res;
					}
					
				}else {
					res.setRequest(false);
					res.setRes("El nombre o el numero del edificio ya existe");
					return res;
				}
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene Permisos");
			return res;
		}
	}
	/**
	 * Método que verifica si un piso y un edificio existe
	 * @param building numero del eidificio a buscar 
	 * @param floor numero del piso a buscar
	 * @return una lista de enteros donde se retorna el id de edificio y piso
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
	 * Método que verifica si  edificio existe
	 * @param building numero del edificio a buscar 
	 * @return si el edificio existe o no
	 */
	private boolean checkBuildings(Building building) {
		boolean is= false;
		Iterable<Building>I;
		I=buildingDAO.findAll();
		for(Building build :I) {
			if(build.getName().equals(building.getName())||build.getNumber()==building.getNumber()) {
				is=true;
			}
		}
		return is;
	}
	/**
	 * Método que crea una carpeta donde se va guardar los planos del edificio
	 * @param number numero del edificio
	 * @return si existia o no la carpeta
	 */
	private boolean CreateFolder(int number) {
		File direc= new File("C:/Users/finden/Desktop/planos/Edificio "+number);
		 return direc.mkdir();
}
	/**
	 * Método que crea una carpeta donde se va guardar los planos de los distintos sotanos del edificio
	 * @param number numero del edificio
	 * @param j numero de sotanos
	 */
	private void CreateFolderBasement(int number, int j) {
		File direc= new File("C:/Users/finden/Desktop/planos/Edificio "+number+"/sotano "+j);
		direc.mkdir();
		CreateFolderApprovedReview("C:/Users/finden/Desktop/planos/Edificio "+number+"/sotano "+j);
	}
	/**
	 * Método que crea una carpeta donde se va guardar los planos aprobados del edificio
	 * @param ruta lugar donde se va crear la carpeta
	 */
	private void CreateFolderApprovedReview(String ruta) {
		File direc= new File(ruta+"/aprobado");
		direc.mkdir();
		direc=new File(ruta+"/revisión");
		direc.mkdir();
	}
	/**
	 * Método que crea una carpeta donde se va guardar los planos de los distintos pisos del edificio
	 * @param number numero del edificio
	 * @param i numero de pisos
	 */
	private void CreateFolderFloor(int number, int i) {
		File direc= new File("C:/Users/finden/Desktop/planos/Edificio "+number+"/piso "+i);
		direc.mkdir();
		CreateFolderApprovedReview("C:/Users/finden/Desktop/planos/Edificio "+number+"/piso "+i);
		
	}
}
