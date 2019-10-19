package com.Finden.findenBackEnd.models.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFText;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
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
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.Plane;
import com.Finden.findenBackEnd.models.entity.PlaneXUser;

@Service
public class FacadeContratistaImpl implements FacadeContratista {

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
	public String AddPLane(String Email, MultipartFile plane,String description) {
		if(Check(Email, 1)||Check(Email, 3)) {
			if(plane.getOriginalFilename()==null) {
				return "No se seleciono un archivo";
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
							return "El edificio o el piso no existe";
						}else {
							return SavePlane(plane,Email, "Edificio "+building+"/sotano "+floorReal*-1+"/revisión", description, res.get(1), res.get(0));
						}
					}else if(floor.contains("P")) {
						StringTokenizer token2= new StringTokenizer(floor,".");
						String number=token2.nextToken().substring(1);
						int floorReal= Integer.parseInt(number);
						res=CheckBuildingFloor(building, floorReal);
						if(res==null) {
							return "El edificio o el piso no existe";
						}else {
							return SavePlane(plane,Email, "Edificio "+building+"/piso "+floorReal* 1+"/revisión", description, res.get(1), res.get(0));
							
						}	
					}else {
						return "El nombre se encuentra mal escrito";
					}
					
				} catch (Exception e) {
					return "El nombre se encuentra mal escrito"+ e;
				}
				
				
			}else {
				return "El plano no es un archivo .dxf";
			}
			
		}else {
			return "El usuario no tiene los permisos necesarios";
		}
	}
	
	@Override
	@Transactional
	public String CheckPlane(String Email, MultipartFile file) {
		if(Check(Email, 1)||Check(Email, 3)) {
			if(file==null) {
				return"holis";
			}
			if(file.getOriginalFilename()==null) {
				return "No se seleciono un archivo";
			}else if(file.getOriginalFilename().contains(".dxf")){
				try {
					StringTokenizer token= new StringTokenizer(file.getOriginalFilename(),"-");
					int building= Integer.parseInt(token.nextToken().trim());
					String floor=token.nextToken();
					List<Integer>res= new ArrayList<Integer>();
					if(floor.contains("S")) {
						StringTokenizer token2= new StringTokenizer(floor,"."); 
						String number=token2.nextToken().substring(1);
						int floorReal= Integer.parseInt(number)*-1;
						res=CheckBuildingFloor(building, floorReal);
						if(res==null) {
							return "El edificio o el piso no existe";
						}else {
							return GetNumberPorts(convert(file).getAbsolutePath());
						}
					}else if(floor.contains("P")) {
						StringTokenizer token2= new StringTokenizer(floor,".");
						String number=token2.nextToken().substring(1);
						int floorReal= Integer.parseInt(number);
						res=CheckBuildingFloor(building, floorReal);
						if(res==null) {
							return "El edificio o el piso no existe";
						}else {
							return GetNumberPorts(convert(file).getAbsolutePath());
							
						}	
					}else {
						return "El nombre se encuentra mal escrito";
					}
					
				} catch (Exception e) {
					return "El nombre se encuentra mal escrito"+ e;
				}
				
				
			}else {
				return "El plano no es un archivo .dxf";
			}
		}else {
			return "El usuario no tiene los permisos necesarios";
		}
	}
	
	@Transactional
	public String DeletePlane(String Email, String NamePlane) {
		if(Check(Email, 1)||Check(Email, 3)) {
			Plane plane;
			plane=planeDAO.findByName(NamePlane);
			if(plane!=null) {
				if((plane.getState()==1||plane.getState()==2)) {
					List<PlaneXUser>pxuList = new ArrayList<PlaneXUser>();
					PlaneXUser pxu= new PlaneXUser();
					pxu.setPlane_Id(plane.getId());
					pxu.setUser_Id(null);
					System.out.println(pxu.toString());
					Example<PlaneXUser>userExample=Example.of(pxu);
					Iterable<PlaneXUser>I;
					I=pxuDAO.findAll(userExample);
					for(PlaneXUser usu:I) {
						pxuList.add(usu);
					}
					if(pxuList.size()>0) {
						pxuDAO.delete(pxuList.get(0));
						File file= new File(plane.getDir());
						if(file.delete()) {
							planeDAO.delete(plane);
							return "El plano se elimino exitosamente";	
						}else {
							return "Problema inesperado por favor comuniquese con la DTI javeriana";	
						}
					}else {
						return "Problema inesperado por favor comuniquese con la DTI javeriana";
					}
				}else {
					return "El plano no puede ser eliminado";
				}
				
			}else {
				return"El plano no existe";
			}
		}else {
			return "El usuario no tiene los permisos necesarios";
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

	private  File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos= new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	} 
    
	private String GetNumberPorts(String filePath) throws ParseException {
        int portsVD=0,portsV=0,portsD=0,RportsVD=0,RportsV=0,RportsD=0;
        StringTokenizer token;
        String type,others="";
        ArrayList<String> namePorts = new ArrayList<>();
        ArrayList<String> puertos = new ArrayList<>();
        Parser parser = ParserBuilder.createDefaultParser();
        parser.parse(filePath, DXFParser.DEFAULT_ENCODING);
        DXFDocument doc = parser.getDocument();
        @SuppressWarnings("unchecked")
		List<DXFText> lst = doc.getDXFLayer("PUERTOS").getDXFEntities(DXFConstants.ENTITY_TYPE_TEXT);
        for (int index = 0; index < lst.size(); index++) {
        	puertos.add(lst.get(index).getText());
        	token=new StringTokenizer(puertos.get(puertos.size()-1)," ");
        	type=token.nextToken().trim();
    		if(type.contentEquals("VD")) {
    			if(ChecknamePorts(namePorts, puertos.get(puertos.size()-1).substring(2))) {
    				namePorts.add(puertos.get(puertos.size()-1).substring(2));
    				portsVD+=1;
    			}else {
    				RportsVD+=1;
    			}
        	}else if(type.contentEquals("D")) {
        		if(ChecknamePorts(namePorts, puertos.get(puertos.size()-1).substring(1))) {
    				namePorts.add(puertos.get(puertos.size()-1).substring(1));
    				portsD+=1;
    			}else {
    				RportsD+=1;
    			}
        		
        	}else if(type.contentEquals("V")){
        		if(ChecknamePorts(namePorts, puertos.get(puertos.size()-1).substring(1))) {
    				namePorts.add(puertos.get(puertos.size()-1).substring(1));
    				portsV+=1;
    			}else {
    				RportsV+=1;
    			}
        	}else {
        		others+=puertos.get(puertos.size()-1)+"-";
        	}
        }
        for (String s : namePorts) {
			System.out.println(s);
		}
        return "Se encontraron: "+(portsD+portsV+portsVD+RportsD+RportsV+RportsVD)+"Repartidons en:\n"
        		+portsVD+" tipo VD "+RportsVD+" Repetidos del tipo VD\n"
        		+portsV+" tipo V "+RportsV+" Repetidos del tipo V\n"
        		+ portsD+" tipo D "+RportsD+" Repetidos del tipo D\n"
        		+ "y otros que no se cuentas:\n"+others;
    }
	
	private boolean ChecknamePorts(ArrayList<String> list,String name) {
		boolean exist=true;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).trim().matches(name.trim())) {
				exist=false;
			}
		}
		return exist;
	}
	
	private void Save(MultipartFile f,String path) throws IOException {
	File archivo= new File(path);
	f.transferTo(archivo);
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
		//Problema ni el HPTA !!! de aqui 
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
		System.out.println(name);
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
}
