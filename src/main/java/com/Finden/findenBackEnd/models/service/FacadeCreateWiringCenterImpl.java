package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.SwitchDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.dao.WritingCenterDAO;
import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.Building;
import com.Finden.findenBackEnd.models.entity.Floor;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.Switch;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.WritingCenter;

@Service
public class FacadeCreateWiringCenterImpl implements FacadeCreateWiringCenter{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	
	@Autowired
	private WritingCenterDAO wcDAO;
	
	@Autowired
	private SwitchDAO  switchDAO;
	
	@Override
	@Transactional
	public Request CreateWiringCenter(String correo, Addwritingcenter add) {
		Request res= new Request();
		if(Check(correo, 1)) {
			if(add.getName()==null) {
				res.setRequest(false);
				res.setRes("No se ingreso el nombre del centro de cableado");
				return res;
			}else if(add.getId()==0) {
				res.setRequest(false);
				res.setRes("No se ingreso el id del centro de cableado");
				return res;
			}else if(add.getBuilding()==0) {
				res.setRequest(false);
				res.setRes("No se ingreso el numero del edificio");
				return res;
			}else if(add.getFloor()==0) {
				res.setRequest(false);
				res.setRes("No se ingreso el numero del piso");
				return res;
			}else if(add.getSwitches()==null) {
				res.setRequest(false);
				res.setRes("No se ingresaron los switches del centro de cableado");
				return res;
			}else {
				WritingCenter wc= new WritingCenter();
				if(checkBuildingsFloor(add.getBuilding(),add.getFloor())) {
					Building b= new Building();
					b.setId(null);
					b.setName(null);
					b.setNumber(add.getBuilding());
					List<Building> bu= new ArrayList<Building>();
					Iterable<Building>I;
					Example<Building>buildingExample=Example.of(b);
					I=buildingDAO.findAll(buildingExample);
					for(Building build:I) {
						bu.add(build);
					}
					Floor f= new Floor();
					f.setBuilding_Id(bu.get(0).getId());
					f.setNumber(add.getFloor());
					f.setId(null);
					List<Floor> fl= new ArrayList<Floor>();
					Iterable<Floor>F;
					Example<Floor>floorExample=Example.of(f);
					F=floorDAO.findAll(floorExample);
					for(Floor floo:F) {
						fl.add(floo);
					}
					wc.setFloor_Building_Id(fl.get(0).getBuilding_Id());
					wc.setFloor_Id(fl.get(0).getId());
					wc.setIdWirtingCenter(add.getId());
					wc.setName(add.getName());
					wcDAO.save(wc);
					List<WritingCenter>listWirting= new ArrayList<WritingCenter>();
					Example<WritingCenter> W= Example.of(wc);
					Iterable<WritingCenter>WR;
					WR=wcDAO.findAll(W);
					for(WritingCenter writing:WR){
						listWirting.add(writing);
					}
					Switch switches= new Switch();
					for (int j = 0; j < add.getSwitches().size(); j++) {
						switches.setIndex(add.getSwitches().get(j).getIndex());
						switches.setNumeroSwitch(add.getSwitches().get(j).getSwitch());
						switches.setWritingCenter_id(listWirting.get(0).getId());
						switchDAO.save(switches);
						switches= new Switch();
					}
					res.setRequest(true);
					res.setRes("Centro de cableado Creado");
					return res;	
				}else {
					res.setRequest(false);
					res.setRes("el edificio o el piso no existe");
					return res;
				}
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene los persmisos para realizar esta operación");
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

	private boolean checkBuildingsFloor(int building, int floor) {
		boolean NoProblem= true;
		Building b= new Building();
		b.setId(null);
		b.setName(null);
		b.setNumber(building);
		List<Building> bu= new ArrayList<Building>();
		Iterable<Building>I;
		Example<Building>buildingExample=Example.of(b);
		I=buildingDAO.findAll(buildingExample);
		for(Building build:I) {
			bu.add(build);
		}
		if(bu.size()==0) {
			NoProblem= false;
		}else if(NoProblem){
			Floor f= new Floor();
			f.setBuilding_Id(bu.get(0).getId());
			f.setNumber(floor);
			f.setId(null);
			List<Floor> fl= new ArrayList<Floor>();
			Iterable<Floor>F;
			Example<Floor>floorExample=Example.of(f);
			F=floorDAO.findAll(floorExample);
			for(Floor floo:F) {
				fl.add(floo);
			}
			if(fl.size()==0) {
				NoProblem= false;
			}
		}else {
			NoProblem=true;
		}
		return NoProblem;
	}
}
