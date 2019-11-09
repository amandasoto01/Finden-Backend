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
