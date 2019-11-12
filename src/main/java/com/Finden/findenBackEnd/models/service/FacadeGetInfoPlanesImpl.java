package com.Finden.findenBackEnd.models.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.HistorialPlane;
import com.Finden.findenBackEnd.models.entity.Plane;
import com.Finden.findenBackEnd.models.entity.PlaneXUser;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.SendInfoPlane;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la logica de negocio de obtener información general de los planos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeGetInfoPlanesImpl implements FacadeGetInfoPlanes{
	
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
	/**
	 * Método que genere una prevalidación del plano (numero de puertos encontrados por cada tipo)
	 * @param Email correo de quien esta haciendo la acción
	 * @param file el archivo .DXF a verificar
	 * @return el resultado de la prevalidación
	 */
	@Override
	@Transactional
	public Request CheckPlane(String Email, MultipartFile file) {
		Request req= new Request();
		if(Check(Email, 1)||Check(Email, 3)) {
			if(file==null) {
				req.setRequest(false);
				req.setRes("no se envio un archivo");
				return req;
			}
			if(file.getOriginalFilename()==null) {
				req.setRequest(false);
				req.setRes("No se seleciono un archivo");
				return req;
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
							req.setRequest(false);
							req.setRes("El edificio o el piso no existe");
							return req;
						}else {
							req.setRequest(true);
							req.setRes(GetNumberPorts(convert(file).getAbsolutePath()));
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
							req.setRes(GetNumberPorts(convert(file).getAbsolutePath()));
							return req;
							
						}	
					}else {
						req.setRequest(true);
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
	/**
	 * Método para obtener el archivo .dxf
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane información del plano a buscar
	 * @return el archivo .dxf
	 */
	@Transactional
	public ResponseEntity<Resource> GetPlane(String email,GetPlane plane) {
		if(Check(email, 1)||Check(email, 3)) {
				Plane plane2;
				plane2 = new Plane();
				plane2.setName(plane.getNamePlane());
				if(plane.getVersion()!=null) {
					plane2.setVersion(plane.getVersion());
				}else {
					plane2.setVersion(null);
				}
				
				List<Plane> pl= new ArrayList<Plane>();
				Iterable<Plane>I;
				Example<Plane>planeExample=Example.of(plane2);
				I=planeDAO.findAll(planeExample);
				for(Plane planes:I) {
					pl.add(planes);
				}
				if(pl.size()>0) {
					File file = new File(pl.get(0).getDir());
					InputStreamResource resource;
					try {
						resource = new InputStreamResource(new FileInputStream(file));
						HttpHeaders header= new HttpHeaders();
						header.add(HttpHeaders.CONTENT_LOCATION, "attachment;filename=plano.dxf");
						header.add("Cache-Control", "no-cache,no-store,must-revalidate");
						header.add("Pragma", "no-cache");
						header.add("Expires", "0");
						return ResponseEntity.ok()
								.headers(header)
								.contentLength(file.length())
								.contentType(MediaType.parseMediaType("image/vnd.dxf"))
								.body(resource);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
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
	 * Método para obtener todos los planos segun un nombre
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane nombre del plano a buscar
	 * @return lista de planos
	 */
	@Transactional
	public ArrayList<HistorialPlane> Historial(String email,String plane){
		if(Check(email, 1)||Check(email, 3)) {
			ArrayList<HistorialPlane>historialPlane= new ArrayList<HistorialPlane>();
			Plane p= new Plane();
			p.setId(null);
			p.setDateApproval(null);
			p.setDateUpload(null);
			p.setDescription(null);
			p.setDir(null);
			p.setFloor_Building_Id(null);
			p.setFloor_id(null);
			p.setName(plane);
			p.setObservation(null);
			p.setState(null);
			p.setVersion(null);
			List<Plane> planes= new ArrayList<Plane>();
			Iterable<Plane>I;
			Example<Plane>planeExample=Example.of(p);
			I=planeDAO.findAll(planeExample);
			for(Plane pl:I) {
				if(pl.getState()==3||pl.getState()==4) {
					planes.add(pl);
				}
			}
			HistorialPlane hp;
			for (int j = 0; j < planes.size(); j++) {
				hp= new HistorialPlane();
				hp.setDescription(planes.get(j).getDescription());
				hp.setVersion(planes.get(j).getVersion());
				historialPlane.add(hp);
			}
			return historialPlane;
		}else {
			return null;
		}
	}
	/**
	 * Método para obtener todos los planos aprovados por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
    @Transactional
    public ArrayList<SendInfoPlane> GetApproved(String email,String user){
    	if(!Check(email, 2)) {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    		ArrayList<SendInfoPlane>ip= new ArrayList<SendInfoPlane>();
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
    			List<PlaneXUser>pxuList = new ArrayList<PlaneXUser>();
    			PlaneXUser pxu= new PlaneXUser();
    			pxu.setPlane_Id(null);
    			pxu.setUser_Id(u.get(0).getId());
    			Example<PlaneXUser>planexuExample=Example.of(pxu);
    			Iterable<PlaneXUser>Ipxu;
    			Ipxu=pxuDAO.findAll(planexuExample);
    			for(PlaneXUser usu:Ipxu) {
    				pxuList.add(usu);
    			}
    			if(pxuList.size()>0) {
    				Plane plane;
    	    		SendInfoPlane sip;
    	    		for (int j = 0; j < pxuList.size(); j++) {
    					plane= new Plane();
    					sip= new SendInfoPlane();
    					plane=planeDAO.findById(pxuList.get(j).getPlane_Id()).get();
    					if(plane.getState()==4||plane.getState()==3) {
    						sip.setStatus("Aprobado");
    						sip.setName(plane.getName());
    						sip.setDescription(plane.getDescription());
    						sip.setBuilding(buildingDAO.findById(plane.getFloor_Building_Id()).get().getNumber()+"-"+buildingDAO.findById(plane.getFloor_Building_Id()).get().getName());
    						if(floorDAO.findById(plane.getFloor_id()).get().getNumber()>0) {
        						sip.setFloor("Piso - "+floorDAO.findById(plane.getFloor_id()).get().getNumber());
        					}else {
        						sip.setFloor("Sotano "+floorDAO.findById(plane.getFloor_id()).get().getNumber());
        					}
    						sip.setVersion(plane.getVersion());
    						sip.setObservation(plane.getObservation());
    						sip.setDateApproval(formatter.format(plane.getDateApproval()));	
    						sip.setDateUpload(formatter.format(plane.getDateUpload()));
    						ip.add(sip);
    					}
    				}
    	    		return ip;
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
	 * Método para obtener todos los planos rechazados por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
    @Transactional
    public ArrayList<SendInfoPlane> GetRejected(String email,String user){
    	if(!Check(email, 2)) {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    		ArrayList<SendInfoPlane>ip= new ArrayList<SendInfoPlane>();
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
    			List<PlaneXUser>pxuList = new ArrayList<PlaneXUser>();
    			PlaneXUser pxu= new PlaneXUser();
    			pxu.setPlane_Id(null);
    			pxu.setUser_Id(u.get(0).getId());
    			Example<PlaneXUser>planexuExample=Example.of(pxu);
    			Iterable<PlaneXUser>Ipxu;
    			Ipxu=pxuDAO.findAll(planexuExample);
    			for(PlaneXUser usu:Ipxu) {
    				pxuList.add(usu);
    			}
    			if(pxuList.size()>0) {
    				Plane plane;
    	    		SendInfoPlane sip;
    	    		for (int j = 0; j < pxuList.size(); j++) {
    					plane= new Plane();
    					sip= new SendInfoPlane();
    					plane=planeDAO.findById(pxuList.get(j).getPlane_Id()).get();
    					if(plane.getState()==2) {
    						sip.setStatus("Rechazado");
    						sip.setName(plane.getName());
    						sip.setDescription(plane.getDescription());
    						sip.setBuilding(buildingDAO.findById(plane.getFloor_Building_Id()).get().getNumber()+"-"+buildingDAO.findById(plane.getFloor_Building_Id()).get().getName());
    						if(floorDAO.findById(plane.getFloor_id()).get().getNumber()>0) {
        						sip.setFloor("Piso - "+floorDAO.findById(plane.getFloor_id()).get().getNumber());
        					}else {
        						sip.setFloor("Sotano "+floorDAO.findById(plane.getFloor_id()).get().getNumber());
        					}
    						sip.setObservation(plane.getObservation());
    						sip.setDateUpload(formatter.format(plane.getDateUpload()));
    						ip.add(sip);
    					}
    				}
    	    		return ip;
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
	 * Método para obtener todos los planos por usuario
	 * @param Email correo de quien esta haciendo la acción
	 * @param user correo del usuario quien subio el plano
	 * @return lista de planos
	 */
    @Transactional
    public ArrayList<SendInfoPlane> GetAllPlanes(String email,String user){
    	if(!Check(email, 2)) {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    		ArrayList<SendInfoPlane>ip= new ArrayList<SendInfoPlane>();
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
    			List<PlaneXUser>pxuList = new ArrayList<PlaneXUser>();
    			PlaneXUser pxu= new PlaneXUser();
    			pxu.setPlane_Id(null);
    			pxu.setUser_Id(u.get(0).getId());
    			Example<PlaneXUser>planexuExample=Example.of(pxu);
    			Iterable<PlaneXUser>Ipxu;
    			Ipxu=pxuDAO.findAll(planexuExample);
    			for(PlaneXUser usu:Ipxu) {
    				pxuList.add(usu);
    			}
    			if(pxuList.size()>0) {
    				Floor floor;
    				Plane plane;
    	    		SendInfoPlane sip;
    	    		for (int j = 0; j < pxuList.size(); j++) {
    					plane= new Plane();
    					sip= new SendInfoPlane();
    					plane=planeDAO.findById(pxuList.get(j).getPlane_Id()).get();
    					floor=floorDAO.findById(plane.getFloor_id()).get();
    					if(plane.getState()==4) {
    						sip.setStatus("actual");
    					}else if(plane.getState()==3) {
    						sip.setStatus("aprobado");
    					}else if(plane.getState()==2) {
    						sip.setStatus("rechazado");
    					}else if(plane.getState()==1) {
    						sip.setStatus("en revisión");
    					}
    					sip.setBuilding(buildingDAO.findById(plane.getFloor_Building_Id()).get().getNumber()+"-"+buildingDAO.findById(plane.getFloor_Building_Id()).get().getName());
						if(floor.getNumber()> 0) {
    						sip.setFloor("Piso - "+floor.getNumber());
    					}else {
    						sip.setFloor("Sotano "+floor.getNumber());
    					}
    					sip.setName(plane.getName());
						sip.setDescription(plane.getDescription());
						if(plane.getVersion()==null) {
							sip.setVersion(0);
						}else {
							sip.setVersion(plane.getVersion());
						}
						sip.setObservation(plane.getObservation());
						if(plane.getDateApproval()!=null) {
							
							sip.setDateApproval(formatter.format(plane.getDateApproval()));	
						}
						sip.setDateUpload(formatter.format(plane.getDateUpload()));
						ip.add(sip);
    				}
    	    		return ip;
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
	 * Método para obtener todos los planos 
	 * @param Email correo de quien esta haciendo la acción
	 * @return lista de planos
	 */
    @Transactional
    public ArrayList<SendInfoPlane> getDTIPlanes (String email){
    	if(!Check(email, 2)) {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    		ArrayList<SendInfoPlane>ip= new ArrayList<SendInfoPlane>();
    				Floor floor;
    	    		SendInfoPlane sip;
    	    		Iterable<Plane> P = planeDAO.findAll();
    	    		for (Plane p :P) {
    					sip= new SendInfoPlane();
    					floor=floorDAO.findById(p.getFloor_id()).get();
    					if(p.getState()==4) {
    						sip.setStatus("actual");
    					}else if(p.getState()==3) {
    						sip.setStatus("aprobado");
    					}else if(p.getState()==2) {
    						sip.setStatus("rechazado");
    					}else if(p.getState()==1) {
    						sip.setStatus("en revisión");
    					}
    					sip.setBuilding(buildingDAO.findById(p.getFloor_Building_Id()).get().getNumber()+"-"+buildingDAO.findById(p.getFloor_Building_Id()).get().getName());
						if(floor.getNumber()> 0) {
    						sip.setFloor("Piso - "+floor.getNumber());
    					}else {
    						sip.setFloor("Sotano "+floor.getNumber());
    					}
    					sip.setName(p.getName());
						sip.setDescription(p.getDescription());
						if(p.getVersion()==null) {
							sip.setVersion(0);
						}else {
							sip.setVersion(p.getVersion());
						}
						sip.setObservation(p.getObservation());
						if(p.getDateApproval()!=null) {
							
							sip.setDateApproval(formatter.format(p.getDateApproval()));	
						}
						sip.setDateUpload(formatter.format(p.getDateUpload()));
						ip.add(sip);
    				}
    	    		return ip;
    	}else {
    		return null;
    	}
    }
    /**
	 * Método para obtener todos los planos segun una información
	 * @param Email correo de quien esta haciendo la acción
	 * @user plane información de los planos a buscar
	 * @return lista de planos
	 */
    @Transactional
    public ArrayList<SendInfoPlane> GetAllPlanesDTI(String email,SendInfoPlane user){
    	if(!Check(email, 2)) {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    		ArrayList<SendInfoPlane>ip= new ArrayList<SendInfoPlane>();
    		Building building= new Building();
    		List<Building> bu= new ArrayList<Building>();
    		Iterable<Building>I;
    		building.setId(null);
    		building.setName(null);
    		building.setNumber(Integer.parseInt(user.getBuilding()));
			Example<Building>buExample=Example.of(building);
    		I=buildingDAO.findAll(buExample);
    		for(Building b:I) {
    			bu.add(b);
    		}
    		if(bu.size()>0) {
    			Floor floor= new Floor();
        		List<Floor> fl= new ArrayList<Floor>();
        		Iterable<Floor>FI;
        		floor.setNumber(Integer.parseInt(user.getFloor()));
        		floor.setBuilding_Id(bu.get(0).getId());
    			Example<Floor>flExample=Example.of(floor);
        		FI=floorDAO.findAll(flExample);
        		for(Floor b:FI) {
        			fl.add(b);
        		}
        		if(fl.size()>0) {
        			Plane plane= new Plane();
            		List<Plane> pls= new ArrayList<Plane>();
            		Iterable<Plane>PI;
            		plane.setFloor_Building_Id(bu.get(0).getId());
            		plane.setFloor_id(fl.get(0).getId());
        			Example<Plane>plExample=Example.of(plane);
            		PI=planeDAO.findAll(plExample);
            		for(Plane b:PI) {
            			pls.add(b);
            		}
            		if(pls.size()>0) {
            			SendInfoPlane sip;
        	    		for (int j = 0; j < pls.size(); j++) {
        					plane= new Plane();
        					sip= new SendInfoPlane();
        					plane=planeDAO.findById(pls.get(j).getId()).get();
        					if(plane.getState()==4) {
        						sip.setStatus("actual");
        					}else if(plane.getState()==3) {
        						sip.setStatus("aprobado");
        					}else if(plane.getState()==2) {
        						sip.setStatus("rechazado");
        					}else if(plane.getState()==1) {
        						sip.setStatus("en revisión");
        					}
        					sip.setBuilding(bu.get(0).getNumber()+"-"+bu.get(0).getName());
        					if(floor.getNumber()> 0) {
        						sip.setFloor("Piso - "+floor.getNumber());
        					}else {
        						sip.setFloor("Sotano "+floor.getNumber());
        					}
        					sip.setName(plane.getName());
    						sip.setDescription(plane.getDescription());
    						if(plane.getVersion()==null) {
    							sip.setVersion(0);
    						}else {
    							sip.setVersion(plane.getVersion());
    						}
    						sip.setObservation(plane.getObservation());
    						if(plane.getDateApproval()!=null) {
    							
    							sip.setDateApproval(formatter.format(plane.getDateApproval()));	
    						}
    						sip.setDateUpload(formatter.format(plane.getDateUpload()));
    						ip.add(sip);
        				}
        	    		return this.Order(ip);
            		}else {
            			return null;
            		}
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
	 * Método para obtener todos los planos aprovados segun una información
	 * @param Email correo de quien esta haciendo la acción
	 * @user plane información de los planos a buscar
	 * @return lista de planos
	 */
    @Transactional
    public ArrayList<SendInfoPlane> getAllPlanesActual(String email,SendInfoPlane user) {
    	if(!Check(email, 2)) {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    		ArrayList<SendInfoPlane>ip= new ArrayList<SendInfoPlane>();
    		Building building= new Building();
    		List<Building> bu= new ArrayList<Building>();
    		Iterable<Building>I;
    		building.setId(null);
    		building.setName(null);
    		building.setNumber(Integer.parseInt(user.getBuilding()));
			Example<Building>buExample=Example.of(building);
    		I=buildingDAO.findAll(buExample);
    		for(Building b:I) {
    			bu.add(b);
    		}
    		if(bu.size()>0) {
    			Floor floor= new Floor();
        		List<Floor> fl= new ArrayList<Floor>();
        		Iterable<Floor>FI;
        		floor.setNumber(Integer.parseInt(user.getFloor()));
        		floor.setBuilding_Id(bu.get(0).getId());
    			Example<Floor>flExample=Example.of(floor);
        		FI=floorDAO.findAll(flExample);
        		for(Floor b:FI) {
        			fl.add(b);
        		}
        		if(fl.size()>0) {
        			Plane plane= new Plane();
            		List<Plane> pls= new ArrayList<Plane>();
            		Iterable<Plane>PI;
            		plane.setFloor_Building_Id(bu.get(0).getId());
            		plane.setFloor_id(fl.get(0).getId());
        			Example<Plane>plExample=Example.of(plane);
            		PI=planeDAO.findAll(plExample);
            		for(Plane b:PI) {
            			pls.add(b);
            		}
            		if(pls.size()>0) {
            			SendInfoPlane sip;
        	    		for (int j = 0; j < pls.size(); j++) {
        					plane= new Plane();
        					sip= new SendInfoPlane();
        					plane=planeDAO.findById(pls.get(j).getId()).get();
        					if(plane.getState()==4) {
        						sip.setStatus("actual");
        					}else if(plane.getState()==3) {
        						sip.setStatus("aprobado");
        					}else if(plane.getState()==2) {
        						sip.setStatus("rechazado");
        					}else if(plane.getState()==1) {
        						sip.setStatus("en revisión");
        					}
        					sip.setBuilding(bu.get(0).getNumber()+"-"+bu.get(0).getName());
        					if(floor.getNumber()> 0) {
        						sip.setFloor("Piso - "+floor.getNumber());
        					}else {
        						sip.setFloor("Sotano "+floor.getNumber());
        					}
        					sip.setName(plane.getName());
    						sip.setDescription(plane.getDescription());
    						if(plane.getVersion()==null) {
    							sip.setVersion(0);
    						}else {
    							sip.setVersion(plane.getVersion());
    						}
    						sip.setObservation(plane.getObservation());
    						if(plane.getDateApproval()!=null) {
    							
    							sip.setDateApproval(formatter.format(plane.getDateApproval()));	
    						}
    						sip.setDateUpload(formatter.format(plane.getDateUpload()));
    						ip.add(sip);
        				}
        	    		return this.getAprove(ip);
            		}else {
            			return null;
            		}
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
	 * Método para obtener el numero de planos por aprovar
	 * @param Email correo de quien esta haciendo la acción
	 * @return El numero de planos por aprovar
	 */
    @Transactional
    public Integer planesToApprove(String email) {
    	if(Check(email, 1)) {
    		Plane plane = new Plane();
    		List<Plane> planes= new ArrayList<Plane>();
    		Iterable<Plane>I;
    		plane.setState(1);
    		Example<Plane>planeExample=Example.of(plane);
    		I=planeDAO.findAll(planeExample);
    		for(Plane pl:I) {
    			planes.add(pl);
    		}
    			return planes.size();
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
	/**
	 * Método que verifica si un piso y un edificio existe
	 * @param building numero del eidificio a buscar 
	 * @param floor numero del piso a buscar
	 * @return una lista de enteros donde se retorna el id de edificio y piso
	 */
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
	/**
	 * Método convierte un multiparFile a File
	 * @param file archivo a convertir
	 * @return archivo convertido
	 */
	private  File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos= new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	/**
	 * Método que mira los puertos en un plano y si no existen los crea
	 * @param filePath ruta del plano
	 * @return una lista de puertos
	 */
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
        return "Se encontraron: "+(portsD+portsV+portsVD+RportsD+RportsV+RportsVD)+"Repartidos en:\n"
        		+portsVD+" tipo VD "+RportsVD+" Repetidos del tipo VD\n"
        		+portsV+" tipo V "+RportsV+" Repetidos del tipo V\n"
        		+ portsD+" tipo D "+RportsD+" Repetidos del tipo D\n"
        		+ "y otros que no se cuentas:\n"+others;
    }
	/**
	 * Método que verifica que si un puerto ya existe en una lista
	 * @param name nombre del puerto
	 * @param list lista de puertos
	 * @return si existe o no 
	 */
	private boolean ChecknamePorts(ArrayList<String> list,String name) {
		boolean exist=true;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).trim().matches(name.trim())) {
				exist=false;
			}
		}
		return exist;
	}
	/**
	 * Método que ordena los planos en: en revision, actual, aprovados , rechazados
	 * @param list lista de planos
	 * @return lista de planos ordena
	 */
	private ArrayList<SendInfoPlane> Order(ArrayList<SendInfoPlane> planes){
		ArrayList<SendInfoPlane>order= new ArrayList<SendInfoPlane>();
		for (int i = 0; i < planes.size(); i++) {
			if(planes.get(i).getStatus()=="en revisión") {
				order.add(planes.get(i));
			}
		}
		for (int i = 0; i < planes.size(); i++) {
			if(planes.get(i).getStatus()=="actual") {
				order.add(planes.get(i));
				break;
			}
		}
		for (int i = 0; i < planes.size(); i++) {
			if(planes.get(i).getStatus()=="aprobado") {
				order.add(planes.get(i));
			}
		}
		for (int i = 0; i < planes.size(); i++) {
			if(planes.get(i).getStatus()=="rechazado") {
				order.add(planes.get(i));
			}
		}
		return order;
	}
	/**
	 * Método para obtener solo los planos aprobados de una lista
	 * @param list lista de planos
	 * @return lista de planos aprobados
	 */
	private ArrayList<SendInfoPlane> getAprove(ArrayList<SendInfoPlane> planes){
		ArrayList<SendInfoPlane>order= new ArrayList<SendInfoPlane>();
		for (int i = 0; i < planes.size(); i++) {
			if(planes.get(i).getStatus()=="actual") {
				order.add(planes.get(i));
				break;
			}
		}
		for (int i = 0; i < planes.size(); i++) {
			if(planes.get(i).getStatus()=="aprobado") {
				order.add(planes.get(i));
			}
		}
		for (int i = 0; i < order.size(); i++) {
			
		}
		return order;
	}
}
