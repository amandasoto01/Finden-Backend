package com.Finden.findenBackEnd.models.service;

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

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.PlaneDAO;
import com.Finden.findenBackEnd.models.dao.PortDAO;
import com.Finden.findenBackEnd.models.dao.SwitchDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.dao.WritingCenterDAO;
import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.Building;
import com.Finden.findenBackEnd.models.entity.Floor;
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.Plane;
import com.Finden.findenBackEnd.models.entity.Port;
import com.Finden.findenBackEnd.models.entity.PortList;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.Switch;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.WritingCenter;
/**
 * Esta clase es la logica de negocio de crear puertos
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeCreatePortImpl implements FacadeCreatePort{
	
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
	
	@Autowired
	private PortDAO  portDAO;
	
	@Autowired
	private PlaneDAO planeDAO;
	/**
	 * Método para agregar un edificio 
	 * @param Email correo de quien esta haciendo la acción
	 * @param add la información del nuevo puerto
	 * @return si la función funciono o no 
	 */
	@Transactional
	public Request CreatePort(String correo, AddPort add) {
		Request res = new Request();
		if(Check(correo, 1)) {
			if(checkBuildingsFloor(add.getBuilding(), add.getFloor())) {
						Port port= new Port();
						port.setName(add.getName());
						port.setType(CheckNamePort(add.getType()));
						port.setPortInSwitch(add.getNPortSwitch());
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
							port.setFloor_Building_Id(fl.get(0).getBuilding_Id());
							port.setFloor_Id(fl.get(0).getId());
							WritingCenter Wc= wcDAO.findByName(add.getWiringCenter());
							Switch s = new Switch();
							List<Switch> u= new ArrayList<Switch>();
							Iterable<Switch>SI;
							if(Wc!=null) {
								s.setWritingCenter_id(Wc.getId());
								port.setSwitch_WritingCenter_id(Wc.getId());
							}
							if(add.getSwitch()!=null) {
								s.setNumeroSwitch(add.getSwitch());
								s.setIndex(null);
								s.setId(null);
								Example<Switch>switchExample=Example.of(s);
								SI=switchDAO.findAll(switchExample);
								for(Switch swit:SI) {
									u.add(swit);
								}
								port.setSwitch_id(u.get(0).getId());
								
							}else {
								port.setSwitch_id(null);
							}
							portDAO.save(port);
							res.setRequest(true);
							res.setRes("Puerto Creado con exito");
							return res;
			}else {
				res.setRequest(false);
				res.setRes("El edificio o el piso no existe");
				return res;
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene los permisos suficientes");
			return res;
		}
	}
	/**
	 * Método que ve los puertos de un plano si no existen los crea
	 * @param Email correo de quien esta haciendo la acción
	 * @param plane información del plano
	 * @return si la función funciono o no 
	 */
	@Transactional
	public ArrayList<PortList> GetPlanePorts(String email,GetPlane plane) {
		ArrayList<PortList> ports = new ArrayList<>();
		if(Check(email, 1)) {
			if(plane.getVersion()!=null) {
				Plane plane2;
				plane2 = new Plane();
				plane2.setName(plane.getNamePlane());
				plane2.setVersion(plane.getVersion());
				List<Plane> pl= new ArrayList<Plane>();
				Iterable<Plane>I;
				Example<Plane>planeExample=Example.of(plane2);
				I=planeDAO.findAll(planeExample);
				for(Plane planes:I) {
					pl.add(planes);
				}
				if(pl.size()>0) {
					ArrayList<PortList> namePorts = new ArrayList<>();
					try {
						namePorts=GetNumberPorts(pl.get(0).getDir());
						Port p;
						AddPort ap;
						WritingCenter wc;
						PortList portl;
						StringTokenizer token = new StringTokenizer(plane.getNamePlane(),"-");
						String building=token.nextToken().trim();
						String wrc; 
						token = new StringTokenizer(token.nextToken(),".dxf");
						String number= token.nextToken().trim();
						for (PortList namePort : namePorts) {
							token= new StringTokenizer(namePort.getPort(),"-");
							wrc=token.nextToken().trim();
							p= new Port();
							ap= new AddPort();
							wc= new WritingCenter();
							portl= new PortList();
							p=portDAO.findByName(namePort.getPort());
							if(p==null) {
								ap.setBuilding(Integer.parseInt(building));
								if(number.contains("P")) {
									ap.setFloor(Integer.parseInt(number.substring(1).trim()));
								}else {
									ap.setFloor(Integer.parseInt(number.substring(1).trim())*-1);
								}
								ap.setName(namePort.getPort());
								ap.setType(namePort.getWritingCenter());
								wc=wcDAO.findByName(wrc);
								if(wc!=null) {
									ap.setWiringCenter(wrc);
									portl.setWritingCenter(wrc);
								}
								portl.setPort(namePort.getPort());
								portl.setPortInSwitch(null);
								portl.setSwitch(null);
								ap.setNPortSwitch(null);
								ap.setSwitch(null);
								ports.add(portl);
								portl.setType(namePort.getWritingCenter());
								this.CreatePort(email, ap);							
							}else {
								portl.setPort(p.getName());
								portl.setPortInSwitch(p.getPortInSwitch());
								portl.setType(namePort.getWritingCenter());
								if(p.getSwitch_WritingCenter_id()!=null) {
									portl.setWritingCenter(wcDAO.findById(p.getSwitch_WritingCenter_id()).get().getName());
								}
								if(p.getSwitch_id()!=null) {
									portl.setSwitch(switchDAO.findById(p.getSwitch_id()).get().getNumeroSwitch());
								}else {
									portl.setSwitch(null);
								}
								ports.add(portl);
							}
							
						}
						return ports;
					} catch (ParseException e) {
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
	 * Método que ve los puertos de un piso en especifico
	 * @param Email correo de quien esta haciendo la acción
	 * @param edificio numero del edificio a buscar
	 * @param piso numero del  piso a buscar
	 * @return si la función funciono o no 
	 */
    @Transactional
    public ArrayList<PortList> getPortsFloor(String email,Integer edificio, Integer piso){
    	if(Check(email, 1)) {
    		Building b= new Building();
    		b.setId(null);
    		b.setName(null);
    		b.setNumber(edificio);
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
    			f.setNumber(piso);
    			f.setId(null);
    			List<Floor> fl= new ArrayList<Floor>();
    			Iterable<Floor>F;
    			Example<Floor>floorExample=Example.of(f);
    			F=floorDAO.findAll(floorExample);
    			for(Floor floo:F) {
    				fl.add(floo);
    			}
    			if(fl.size()>0) {
    				Port p= new Port();
    				p.setFloor_Building_Id(bu.get(0).getId());
    				p.setFloor_Id(fl.get(0).getId());
    				ArrayList<PortList> ps= new ArrayList<PortList>();
    				Iterable<Port>P;
    				Example<Port>pExample=Example.of(p);
    				P=portDAO.findAll(pExample);
    				PortList pl;
    				Switch s;
    				WritingCenter wc;
    				for(Port port:P) {
    					wc= new WritingCenter();
    					s= new Switch();
    					pl= new PortList();
    					pl.setPort(port.getName());
    					pl.setPortInSwitch(port.getPortInSwitch());
    					if(port.getSwitch_WritingCenter_id()!=null) {
    						wc=wcDAO.findById(port.getSwitch_WritingCenter_id()).get();	
        					pl.setWritingCenter(wc.getName());	
    					}
    					if(port.getSwitch_id()!=null) {
    						s=switchDAO.findById(port.getSwitch_id()).get();
    						pl.setSwitch(s.getNumeroSwitch());
    					}
    					if(port.getType()==1) {
    						pl.setType("V");
    					}else if(port.getType()==2) {
    						pl.setType("D");
    					}else if(port.getType()==3){
    						pl.setType("VD");
    					}
    					ps.add(pl);
    				}
    				return ps;
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
	 * Método que verifica si un piso existe
	 * @param building numero del edificio a buscar 
	 * @param floor numero del piso a buscar
	 * @return si el piso existe o no
	 */
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
	/**
	 * Método que verifica que si el nombre del puerto ya existe
	 * @param name el nombre del puerto
	 * @param list lista de puertos en el sistema 
	 * @return si el puerto ya existe o no
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
	 * Método que mira los puertos en un plano y si no existen los crea
	 * @param filePath ruta del plano
	 * @return una lista de puertos
	 */
	private ArrayList<PortList> GetNumberPorts(String filePath) throws ParseException {
        StringTokenizer token;
        String type;
        ArrayList<String> namePorts = new ArrayList<>();
        ArrayList<PortList>ports= new ArrayList<PortList>();
        ArrayList<String> puertos = new ArrayList<>();
        PortList p;
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
    				namePorts.add(puertos.get(puertos.size()-1).substring(2).trim());
    				p= new PortList();
    				p.setPort(puertos.get(puertos.size()-1).substring(2).trim());
    				p.setWritingCenter("VD");
    				ports.add(p);
    			}
        	}else if(type.contentEquals("D")) {
        		if(ChecknamePorts(namePorts, puertos.get(puertos.size()-1).substring(1))) {
        			namePorts.add(puertos.get(puertos.size()-1).substring(1).trim());
        			p= new PortList();
    				p.setPort(puertos.get(puertos.size()-1).substring(2).trim());
    				p.setWritingCenter("D");
    				ports.add(p);
        		}
        	}else if(type.contentEquals("V")){
        		if(ChecknamePorts(namePorts, puertos.get(puertos.size()-1).substring(1))) {
        			namePorts.add(puertos.get(puertos.size()-1).substring(1).trim());
        			p= new PortList();
    				p.setPort(puertos.get(puertos.size()-1).substring(2).trim());
    				p.setWritingCenter("V");
    				ports.add(p);
        		}
        	}
        }
        return ports;
    }
}
