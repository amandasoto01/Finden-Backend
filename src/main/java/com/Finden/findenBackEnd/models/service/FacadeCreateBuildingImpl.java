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

@Service
public class FacadeCreateBuildingImpl implements FacadeCreateBuilding{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	
	@Override
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
	
	private boolean CreateFolder(int number) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number);
		 return direc.mkdir();
}

	private void CreateFolderBasement(int number, int j) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number+"/sotano "+j);
		direc.mkdir();
		CreateFolderApprovedReview("C:/Users/javier/Desktop/planos/Edificio "+number+"/sotano "+j);
	}

	private void CreateFolderApprovedReview(String ruta) {
		File direc= new File(ruta+"/aprobado");
		direc.mkdir();
		direc=new File(ruta+"/revisión");
		direc.mkdir();
	}
	
	private void CreateFolderFloor(int number, int i) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number+"/piso "+i);
		direc.mkdir();
		CreateFolderApprovedReview("C:/Users/javier/Desktop/planos/Edificio "+number+"/piso "+i);
		
	}
}
