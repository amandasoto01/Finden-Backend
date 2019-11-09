package com.Finden.findenBackEnd.models.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.PlaneDAO;
import com.Finden.findenBackEnd.models.dao.PlaneXUserDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.Building;
import com.Finden.findenBackEnd.models.entity.Floor;
import com.Finden.findenBackEnd.models.entity.Plane;
import com.Finden.findenBackEnd.models.entity.PlaneXUser;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

@Service
public class FacadeAddPlaneImpl implements FacadeAddPlane{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired 
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	
	@Autowired
	private PlaneDAO planeDAO;
	
	@Autowired
	private PlaneXUserDAO pxuDAO;
	
	@Transactional
	public Request AddPLane(String Email, MultipartFile plane,String description) {
		Request req= new Request();
		if(Check(Email, 1)||Check(Email, 3)) {
			if(plane.getOriginalFilename()==null) {
				req.setRequest(false);
				req.setRes("No se seleciono un archivo");
				return req;
			}else if(plane.getOriginalFilename().contains(".dxf")){
				try {
					StringTokenizer token= new StringTokenizer(plane.getOriginalFilename(),"-");
					int building= Integer.parseInt(token.nextToken().trim());
					String floor=token.nextToken();
					List<Integer>res= new ArrayList<Integer>();
					if(floor.contains("S")) {
						StringTokenizer token2= new StringTokenizer(floor,"."); 
						String number=token2.nextToken().substring(1);
						int floorReal= Integer.parseInt(number)*-1;
						res=CheckBuildingFloor(building, floorReal);
						if(res==null) {
							req.setRequest(false);
							req.setRes("El edificio o el piso no existe");
							return req;
						}else {
							req.setRequest(true);
							req.setRes(SavePlane(plane,Email, "Edificio "+building+"/sotano "+floorReal*-1+"/revisión", description, res.get(1), res.get(0)));
							return req;
						}
					}else if(floor.contains("P")) {
						StringTokenizer token2= new StringTokenizer(floor,".");
						String number=token2.nextToken().substring(1);
						int floorReal= Integer.parseInt(number);
						res=CheckBuildingFloor(building, floorReal);
						if(res==null) {
							req.setRequest(false);
							req.setRes("El edificio o el piso no existe");
							return req;
						}else {
							req.setRequest(true);
							req.setRes(SavePlane(plane,Email, "Edificio "+building+"/piso "+floorReal* 1+"/revisión", description, res.get(1), res.get(0)));
							return req;
							
						}	
					}else {
						req.setRequest(false);
						req.setRes("El nombre se encuentra mal escrito");
						return req;
					}
					
				} catch (Exception e) {
					req.setRequest(false);
					req.setRes("El nombre se encuentra mal escrito"+ e);
					return req;
				}
				
				
			}else {
				req.setRequest(false);
				req.setRes("El plano no es un archivo .dxf");
				return req;
			}
			
		}else {
			req.setRequest(false);
			req.setRes("El usuario no tiene los permisos necesarios");
			return req;
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
	
	private List<Integer>CheckBuildingFloor(int building,int floor){
		Boolean NoProblem=true;
		List<Integer>res= new ArrayList<Integer>();
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
			res=null;
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
				res=null;
			}else {
				
				res.add(bu.get(0).getId());
				res.add(fl.get(0).getId());
			}
		}
		return res;
	}
	
	@Transactional
	private String SavePlane(MultipartFile plane,String email,String path,String description,int floor,int Building) {
		Plane p= new Plane();
		File dir= new File("C:/Users/javier/Desktop/planos/"+path);
		Date date= new Date();
		StringTokenizer token= new StringTokenizer(plane.getOriginalFilename(),".");
		String name,auxname;
		int aux;
		String [] NFiles=dir.list();
		if(NFiles==null) {
			name=token.nextToken()+"-R-1"+".dxf";
		}else {
			auxname=token.nextToken();
			aux=1;
			for (int i = 0; i < NFiles.length; i++) {
				if(NFiles[i].compareToIgnoreCase(auxname+"-R-"+aux+".dxf")==0) {
					aux++;
					i=0;
				}
			}
			name=auxname+"-R-"+aux+".dxf";
		}
		p.setDateUpload(date);
		p.setDescription(description);
		p.setFloor_Building_Id(Building);
		p.setFloor_id(floor);
		p.setName(name);
		p.setState(1);
		p.setDir("C:/Users/javier/Desktop/planos/"+path+"/"+name);
		try {
			Save(plane,"C:/Users/javier/Desktop/planos/"+path+"/"+name);
			planeDAO.save(p);
			p= new Plane();
			p=planeDAO.findByName(name);
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
			PlaneXUser pxu= new PlaneXUser();
			pxu.setPlane_Id(p.getId());
			pxu.setUser_Id(u.get(0).getId());
			pxuDAO.save(pxu);
			return "El plano se a agregado exitosamente";
		} catch (IOException e) {
			return "Se presento el error: "+e;
		}
		
	}
	
	private void Save(MultipartFile f,String path) throws IOException {
	File archivo= new File(path);
	f.transferTo(archivo);
	}
}


