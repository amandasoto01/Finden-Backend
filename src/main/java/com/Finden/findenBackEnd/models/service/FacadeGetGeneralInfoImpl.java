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
import com.Finden.findenBackEnd.models.entity.*;
/**
 * Esta clase es la logica de negocio de obtener información general
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeGetGeneralInfoImpl implements FacadeGetGeneralInfo{
	
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
	
	/**
	 * Método para obtener toda la información un usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario a buscar
	 * @return Toda la información de un usuario
	 */
	@Override
	@Transactional
	public User GetUser(String email,String user) {
		User us = new User();
		List<User> u= new ArrayList<User>();
		Iterable<User>I;
		us.setEmail(user);
		us.setName(null);
		us.setType(null);
		us.setPassword(null);
		Example<User>userExample=Example.of(us);
		I=userDAO.findAll(userExample);
		for(User usu:I) {
			u.add(usu);
		}
		if(u.size()>0) {
		return u.get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * Método para obtener los usuarios
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los usuarios registrados en el sistema
	 */
    public ArrayList<SendInfoUser>GetUsers(String email){
    	if(Check(email, 1)) {
    		ArrayList<SendInfoUser>siu= new ArrayList<SendInfoUser>();
    		SendInfoUser send;
    		List<User> u = userDAO.findAll();
    		for (int i = 0; i < u.size(); i++) {
				send= new SendInfoUser();
				send.setEmail(u.get(i).getEmail());
				send.setName(u.get(i).getName());
				if(u.get(i).getType()==1) {
					send.setType("DTI");
				}else if(u.get(i).getType()==2) {
					send.setType("mesa de servicios");
				}else {
					send.setType("contratista");
				}
				siu.add(send);
			}
    		return siu;
    	}else {
    		return null;
    	}
    }
    /**
	 * Método para obtener los centros de cableado
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los centros de cableado registrados en el sistema
	 */
    @Transactional
    public ArrayList<String> GetWritingCenter(String email){
    	if(Check(email, 1)) {
    		ArrayList<String> Wcs= new ArrayList<String>();
    		List<WritingCenter> w = wcDAO.findAll();
    		for (int i = 0; i < w.size(); i++) {
				Wcs.add(w.get(i).getName());
			}
    		return Wcs;
    	}else {
    		return null;
    	}
    }
    /**
	 * Método para obtener los switches de un centro de cableado
	 * @param Email correo de quien esta haciendo la acción
	 * @param Wc nombre del centro de cableado a buscar
	 * @return Los pisos registrados en el sistema
	 */
    @Transactional
    public ArrayList<Integer> getSwitches(String email,String Wc){
    	ArrayList<Integer> sws= new ArrayList<Integer>();
    	WritingCenter wc= wcDAO.findByName(Wc);
    	if(wc!=null) {
    		Switch s= new Switch();
    		s.setId(null);
    		s.setIndex(null);
    		s.setNumeroSwitch(null);
    		s.setWritingCenter_id(wc.getId());
    		List<Switch> switches= new ArrayList<Switch>();
    		Iterable<Switch>I;
    		Example<Switch>switchExample=Example.of(s);
    		I=switchDAO.findAll(switchExample);
    		for(Switch swit:I) {
    			switches.add(swit);
    			sws.add(swit.getNumeroSwitch());
    		}
    		if(sws.size()==0) {
    			return null;
    		}else {
    			return sws;
    		}
    	}else {
    		return null;
    	}
    }
    /**
	 * Método para obtener los edificios
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los edificios registrados en el sistema
	 */
    @Transactional
    public ArrayList<SendInfoBuildng>GetBuildings(String email){
    	if(Check(email, 1)) {
    		ArrayList<SendInfoBuildng>sib= new ArrayList<SendInfoBuildng>();
    		SendInfoBuildng send;
    		List<Building> bu = buildingDAO.findAll();
    		for (int i = 0; i < bu.size(); i++) {
				send= new SendInfoBuildng();
				send.setName(bu.get(i).getName());
				send.setNumber(bu.get(i).getNumber());
				sib.add(send);
			}
    		return sib;
    	}else {
    		return null;
    	}
    }
    /**
	 * Método para obtener los pisos
	 * @param Email correo de quien esta haciendo la acción
	 * @return Los pisos registrados en el sistema
	 */
    @Transactional
    public ArrayList<Integer>GetFloors(String email, String building){
    	ArrayList<Integer>floors= new ArrayList<Integer>();
    	if(Check(email, 1)) {
    		Building b= new Building();
    		b.setId(null);
    		b.setName(null);
    		b.setNumber(Integer.parseInt(building));
    		List<Building> bu= new ArrayList<Building>();
    		Iterable<Building>I;
    		Example<Building>buildingExample=Example.of(b);
    		I=buildingDAO.findAll(buildingExample);
    		for(Building build:I) {
    			bu.add(build);
    		}
    		if(bu.size()>0) {
    			Floor f= new Floor();
    			f.setBuilding_Id(bu.get(0).getId());
    			f.setNumber(null);
    			f.setId(null);
    			List<Floor> fl= new ArrayList<Floor>();
    			Iterable<Floor>F;
    			Example<Floor>floorExample=Example.of(f);
    			F=floorDAO.findAll(floorExample);
    			for(Floor floo:F) {
    				fl.add(floo);
    				floors.add(floo.getNumber());
    			}
    			if(fl.size()>0) {
    				return floors;
    			}else {
    				return null;
    			}
    		}else {
    			return null;
    		}
    	}else {
    		return null;
    	}
    }
    /**
	 * Método para obtener el nombre del usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @return El nombre del usuario
	 */
    @Transactional
    public String getUsername(String email) {
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
		return u.get(0).getName();
		}else {
			return null;
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
	
}
