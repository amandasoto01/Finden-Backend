package com.Finden.findenBackEnd.models.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.Building;
import com.Finden.findenBackEnd.models.entity.Floor;
import com.Finden.findenBackEnd.models.entity.User;

@Service
public class FacadeContratistaImpl implements FacadeContratista {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired 
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	
	public String AddPLane(String Email, MultipartFile plane) {
		if(Check(Email, 1)||Check(Email, 3)) {
			return "Plano creado exitosamente";
			
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
    			if(!(ChecknamePorts(puertos, puertos.get(puertos.size()-1)))) {
    				portsVD+=1;	
    			}else {
    				RportsVD+=1;
    			}
        	}else if(type.contentEquals("D")) {
        		if(!(ChecknamePorts(puertos, puertos.get(puertos.size()-1)))) {
        			portsD+=1;
    			}else {
    				RportsD+=1;
    			}
        		
        	}else if(type.contentEquals("V")){
        		if(!(ChecknamePorts(puertos, puertos.get(puertos.size()-1)))) {
        			portsV+=1;
    			}else {
    				RportsV+=1;
    			}
        	}else {
        		others+=puertos.get(puertos.size()-1)+"-";
        	}
        }
        return "Se encontraron: "+(portsD+portsV+portsVD+RportsD+RportsV+RportsVD)+"Repartidons en:\n"
        		+portsVD+" tipo VD "+RportsVD+" Repetidos del tipo VD\n"
        		+portsV+" tipo V "+RportsV+" Repetidos del tipo V\n"
        		+ portsD+" tipo D "+RportsD+" Repetidos del tipo D\n"
        		+ "y otros que no se cuentas:\n"+others;
    }
	private boolean ChecknamePorts(ArrayList<String> list,String name) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(0).equals(name)) {
				return true;
			}
		}
		return false;
	}
	private String guardarVerification(MultipartFile f) throws IOException {
	String ruta="C:/Users/javier/Desktop/planos/"+f.getOriginalFilename();
	File archivo= new File(ruta);
	f.transferTo(archivo);
	return "exito";
	}
	
}
