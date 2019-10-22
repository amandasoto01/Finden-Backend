package com.Finden.findenBackEnd.models.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.Finden.findenBackEnd.models.dao.PlaneXUserDAO;
import com.Finden.findenBackEnd.models.dao.PortDAO;
import com.Finden.findenBackEnd.models.dao.SwitchDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.dao.WritingCenterDAO;
import com.Finden.findenBackEnd.models.entity.*;

@Service
public class FacadeDTIImpl implements FacadeDTI{
	
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
	
	@Autowired
	private PlaneXUserDAO pxuDAO;
	
	@Override
	@Transactional
	public String Create(User usuario, String email) {
		if(Check(email, 1)) {
			if(usuario.getEmail()==null) {
				return "No se ingreso email";
			}else if (usuario.getName()==null) {
				return "No se ingreso nombre";
			}else if(usuario.getPassword()==null) {
				return "No se ingreso contraseña";
			}else if(usuario.getType()==null) {
				return "No se ingreso Correo";
			}else {
				usuario.setId(null);
				usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
				if(Verificar(usuario)) {
					try {
						userDAO.save(usuario);
						return "Usuario creado exitosamente";
					} catch (Exception e) {
						return e.toString();
					}
				}else {
					return "el usuario ya exite";
				}

			}
		}else {
			return "el usuario no tiene permiso";
		}

	}
	
	@Override
	@Transactional
	public String UpdateUser(User usuario, String email) {
		if(Check(email)) {
			if(usuario.getEmail()==null) {
				return"No se enivo correo";
			}else {
				User us = new User();
				List<User> u= new ArrayList<User>();
				Iterable<User>I;
				us.setEmail(usuario.getEmail());
				us.setName(null);
				us.setType(null);
				us.setPassword(null);
				Example<User>userExample=Example.of(us);
				I=userDAO.findAll(userExample);
				for(User usu:I) {
					u.add(usu);
				}
				if(u.size()>0) {
					if(usuario.getName()==null) {
						usuario.setName(u.get(0).getName());
					}
					if(usuario.getPassword()!=null) {
						usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
					}else {
						usuario.setPassword(u.get(0).getPassword());
					}
					if(usuario.getType()==null) {
						usuario.setType(u.get(0).getType());
					}
					usuario.setId(u.get(0).getId());
					userDAO.save(usuario);
					return "usuario modificado exitosamente";
				}else {
					return"El correo no existe";
				}
			}
		}else {
			return "El usuario no tiene permiso";
		}
	}
	
	@Override
	@Transactional
	public String Delete(String correo, String email) {
		if(Check(email, 1)) {
			if(correo==null) {
				return"No se enivo email";
			}else {
				User us = new User();
				List<User> u= new ArrayList<User>();
				Iterable<User>I;
				us.setEmail(correo);
				us.setName(null);
				us.setType(null);
				us.setPassword(null);
				Example<User>userExample=Example.of(us);
				I=userDAO.findAll(userExample);
				for(User usu:I) {
					u.add(usu);
				}
				if(u.size()>0) {
				userDAO.deleteById(u.get(0).getId());
				return"Usuario eliminado exitosamente";
				}else {
					return"el email no se encuentra en el sistema";
				}
			}
		}else {
			return "El usuario no tiene permisos";
		}
	}

	@Override
	@Transactional
	public String CreateBuilding(String correo, AddBuilding add) {
		boolean problem= false;
		if(Check(correo, 1)) {
			if(add.getName()==null) {
				return "No se a ingresado nombre del edificio";
			}else if(!(add.getNumber()>=0||add.getNumber()<0)) {
				return "No se a ingresado el numero del edificio";
			}else if(!(add.getNBasement()>=0||add.getNBasement()<0)) {
				return "No se a ingresado el numero de sotanos";
			}else if(!(add.getNFloors()>=0||add.getNFloors()<0)) {
				return "No se a ingresado el numero de pisos";
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
							return"Edificio agregado con exito";
							}else {
								return "La ruta para guardar los planos de los sotanos tienen problemas";
							}
					}else {
						return "La ruta para guardar los planos del edificio tiene problemas";
					}
					
				}else {
					return "El nombre o el numero del edificio ya existe";
				}
			}
		}else {
			return "El usuario no tiene Permisos";
		}
	}
	
	@Override
	@Transactional
	public String CreateWiringCenter(String correo, Addwritingcenter add) {
		if(Check(correo, 1)) {
			if(add.getName()==null) {
				return "No se ingreso el nombre del centro de cableado";
			}else if(add.getId()==0) {
				return "No se ingreso el id del centro de cableado";
			}else if(add.getBuilding()==0) {
				return "No se ingreso el numero del edificio";
			}else if(add.getFloor()==0) {
				return "No se ingreso el numero del piso";
			}else if(add.getSwitches()==null) {
				return"No se ingresaron los switches del centro de cableado";
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
					return "Centro de cableado Creado";	
				}else {
					return "el edificio o el piso no existe";
				}
			}
		}else {
			return"El usuario no tiene los persmisos para realizar esta operación";
		}
	}	
	
	public String CreatePort(String correo, AddPort add) {
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
								
							}else {
							s.setWritingCenter_id(null);	
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
								port.setSwitch_WritingCenter_id(u.get(0).getWritingCenter_id());
							}else {
								port.setSwitch_id(null);
								port.setSwitch_WritingCenter_id(null);
							}
							System.out.println(port);
							portDAO.save(port);
						return "Puerto Creado con exito";			
			}else {
				return "El edificio o el piso no existe";
			}
		}else {
			return"El usuario no tiene los permisos suficientes";
		}
	}
	
	public String DeletePort(String port, String email) {
		if(Check(email, 1)) {
			if(email==null) {
				return "no se envio el nombre del puerto";
			}else {
				Port p=portDAO.findByName(port);
				if(p==null) {
					return "El puerto no existe recuerde que el nombre es: tipo “Espacio” nombre del centro de cableado “-” número de identificación del puerto en el patchpanel. ";
				}else {
					portDAO.delete(p);
					return "Puerto eliminado exitosamente";
				}
				
			}
		}else {
			return "El usuario no tiene permiso para realizar esta acción";
		}
	}
	
	public String UpdatePort(String email,UpdatePort updatePort) {
		Port port;
		Port newPort= new Port();
		if(Check(email, 1)) {
			if(updatePort.getPort()==null) {
				return "No se ingreso un puerto a modificar";
			}else {
				port= portDAO.findByName(updatePort.getPort());
				if(port==null) {
					return"No se encontro el puerto: "+updatePort.getPort();
				}else {
					if(updatePort.getBuilding()!=null) {
						Building b= new Building();
						b.setId(null);
						b.setName(null);
						b.setNumber(updatePort.getBuilding());
						List<Building> bu= new ArrayList<Building>();
						Iterable<Building>I;
						Example<Building>buildingExample=Example.of(b);
						I=buildingDAO.findAll(buildingExample);
						for(Building build:I) {
							bu.add(build);
						}
						if(bu.size()>0) {
							newPort.setFloor_Building_Id(bu.get(0).getId());
						}else {
							return "No se encontro el piso: "+ updatePort.getBuilding();
						}
					}else {
						newPort.setFloor_Building_Id(port.getFloor_Building_Id());
					}
					if(updatePort.getFloor()!=null) {
						Floor f= new Floor();
						f.setBuilding_Id(newPort.getFloor_Building_Id());
						f.setNumber(updatePort.getFloor());
						f.setId(null);
						List<Floor> fl= new ArrayList<Floor>();
						Iterable<Floor>F;
						Example<Floor>floorExample=Example.of(f);
						F=floorDAO.findAll(floorExample);
						for(Floor floo:F) {
							fl.add(floo);
						}
						if(fl.size()>0) {
							newPort.setFloor_Id(fl.get(0).getId());
						}else {
							return "No se encontro el piso: "+updatePort.getFloor();
						}
					}else {
						newPort.setFloor_Id(port.getFloor_Id());
					}
					if(updatePort.getName()!=null) {
						newPort.setName(updatePort.getName());
					}else {
						newPort.setName(port.getName());
					}
					if(updatePort.getNPortSwitch()!=null) {
						newPort.setPortInSwitch(updatePort.getNPortSwitch());
					}else {
						newPort.setPortInSwitch(port.getPortInSwitch());
					}
					if(updatePort.getType()!=null) {
						if(CheckNamePort(updatePort.getType())!=0){
							newPort.setType(CheckNamePort(updatePort.getType()));
						}else {
							return "No es valido el tipo de puerto recuerde que son: V,D y VD";
						}
					}else {
						newPort.setType(port.getType());
					}
					if(updatePort.getWiringCenter()!=null) {
						WritingCenter wc;
						wc= wcDAO.findByName(updatePort.getWiringCenter());
						if(wc!=null) {
							newPort.setSwitch_WritingCenter_id(wc.getId());
						}else {
							return "No se encontro el centro de cableado: "+updatePort.getWiringCenter();
						}
					}else {
						newPort.setSwitch_WritingCenter_id(port.getSwitch_WritingCenter_id());
					}
					if(updatePort.getSwitch()!=null) {
						Switch s = new Switch();
						List<Switch> u= new ArrayList<Switch>();
						Iterable<Switch>SI;
						s.setWritingCenter_id(newPort.getSwitch_WritingCenter_id());
						s.setNumeroSwitch(updatePort.getSwitch());
						s.setIndex(null);
						s.setId(null);
						Example<Switch>switchExample=Example.of(s);
						SI=switchDAO.findAll(switchExample);
						for(Switch swit:SI) {
							u.add(swit);
						}
						if(u.size()>0) {
						newPort.setSwitch_id(u.get(0).getId());
						}else {
							return "No se encontro el switch";
						}
					}else {
						newPort.setSwitch_id(port.getSwitch_id());
					}
					newPort.setId(port.getId());
					portDAO.save(newPort);
					return "Puerto actualizado con exito";
				}
				
			}
		}else {
			return "El usuario no tiene permiso para realizar esta acción";
		}
		
	}

	@Transactional
	public String ApprovePlane(String email, ApprovePlane approvePlane) {
		if(Check(email, 1)) {
			User us = new User();
			List<User> u= new ArrayList<User>();
			Iterable<User>Iu;
			us.setEmail(email);
			us.setName(null);
			us.setType(null);
			us.setPassword(null);
			Example<User>userExample=Example.of(us);
			Iu=userDAO.findAll(userExample);
			for(User usu:Iu) {
				u.add(usu);
			}
			Plane plane,actual;
			plane= planeDAO.findByName(approvePlane.getNamePlane());
			if(plane!=null) {
				if(approvePlane.isStatus()) {
					StringTokenizer token = new StringTokenizer(plane.getName(),"-");
					String building=token.nextToken().trim();
					String number= token.nextToken().trim();
					String pathNumber;
					if(number.contains("P")) {
						pathNumber= "piso "+number.substring(1).trim();
					}else {
						pathNumber= "sotano "+number.substring(1).trim();
					}
					actual = new Plane();
					actual.setState(4);
					actual.setName(building+"-"+number+".dxf");
					List<Plane> pl= new ArrayList<Plane>();
					Iterable<Plane>I;
					Example<Plane>planeExample=Example.of(actual);
					I=planeDAO.findAll(planeExample);
					for(Plane planes:I) {
						pl.add(planes);
					}
					if(pl.size()>0) {
						plane.setState(4);
						Date date= new Date();
						plane.setDateApproval(date);
						File dir = new File("C:/Users/javier/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado");
						String [] NFiles=dir.list();
						String path="C:/Users/javier/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado/"+building+"-"+number+"-A-"+(NFiles.length+1)+".dxf";
						pathNumber=plane.getDir();
						plane.setDir(path);
						plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName());
						plane.setVersion(NFiles.length+1);
						try {
							File file = new File(pathNumber);
							copyPlane(pathNumber, path);
							plane.setName(building+"-"+number+".dxf");
							file.delete();
							pl.get(0).setState(3);
							planeDAO.save(pl.get(0));
							planeDAO.save(plane);
						} catch (IOException e) {
							System.out.println(e.toString());
						}
						return "se aprobo exitosamente el plano";
					}else {
						plane.setState(4);
						Date date= new Date();
						plane.setDateApproval(date);
						String path="C:/Users/javier/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado/"+building+"-"+number+"-A-1.dxf";
						pathNumber=plane.getDir();
						plane.setDir(path);
						plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName());
						plane.setVersion(1);
						try {
							File file = new File(pathNumber);
							copyPlane(pathNumber, path);
							plane.setName(building+"-"+number+".dxf");
							file.delete();
							planeDAO.save(plane);
						} catch (IOException e) {
							System.out.println(e.toString());
						}
						return "se aprobo exitosamente el plano";
					}
				}else {
					if(plane.getState()==1) {
						plane.setState(2);
						plane.setObservation("El plano a sido rechazado por el usuario: "+u.get(0).getName());
						planeDAO.save(plane);
						return "Se rechazo el plano exitosamente";
					}else {
						return "Este plano ya a sido aprobado anteriormente";
					}
				}
			}else {
				return "No existe plano con ese nombre";
			}
		}else {
			return"El usuario no tiene permisos para esto";
		}
	}
	
	@Transactional
	public File GetPlane(String email,GetPlane plane) {
		if(Check(email, 1)||Check(email, 3)) {
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
					File file = new File(pl.get(0).getDir());
					return file;
					
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
								ap.setWiringCenter(null);
								ports.add(portl);
								this.CreatePort(email, ap);							
							}else {
								portl.setPort(p.getName());
								portl.setPortInSwitch(p.getPortInSwitch());
								if(p.getSwitch_id()!=null) {
									portl.setSwitch(switchDAO.findById(p.getSwitch_id()).get().getNumeroSwitch());
									portl.setWritingCenter(wcDAO.findById(p.getSwitch_WritingCenter_id()).get().getName());
								}else {
									portl.setSwitch(null);
									portl.setWritingCenter(null);
								}
								ports.add(portl);
							}
							
						}
						return ports;
					} catch (ParseException e) {
						System.out.println(e.toString());
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
	
	@Transactional
	public String Switches(String email,ListPorts listports) {
		if(Check(email, 1)) {
			String res="",aux;
			for (int i = 0; i < listports.getPorts().size(); i++) {
				listports.getPorts().get(i).setBuilding(listports.getBuilding());
				listports.getPorts().get(i).setFloor(listports.getFloor());
				aux=this.UpdatePort(email, listports.getPorts().get(i));
				if(!aux.trim().matches("Puerto actualizado con exito")) {
					res+="Problema en el puerto: "+listports.getPorts().get(i).getPort()+" error: "+aux+"\n";
				}
			}
			if(res.matches("")) {
				res="Todos los puertos han sido cargados exitosamente";
			}
			return res;
		}else {
			return "El usuario no tiene permiso para realizar esta acción";
		}
	}

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

    @Transactional
    public ArrayList<SendInfoPlane> GetApproved(String email,String user){
    	if(!Check(email, 2)) {
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
    				System.out.println(usu);
    			}
    			if(pxuList.size()>0) {
    				Plane plane;
    	    		SendInfoPlane sip;
    	    		for (int j = 0; j < pxuList.size(); j++) {
    					plane= new Plane();
    					sip= new SendInfoPlane();
    					plane=planeDAO.findById(pxuList.get(j).getPlane_Id()).get();
    					if(plane.getState()==4||plane.getState()==3) {
    						sip.setStatus(true);
    						sip.setName(plane.getName());
    						sip.setDescription(plane.getDescription());
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
	
    @Transactional
    public ArrayList<SendInfoPlane> GetRejected(String email,String user){
    	if(!Check(email, 2)) {
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
    				System.out.println(usu);
    			}
    			if(pxuList.size()>0) {
    				Plane plane;
    	    		SendInfoPlane sip;
    	    		for (int j = 0; j < pxuList.size(); j++) {
    					plane= new Plane();
    					sip= new SendInfoPlane();
    					plane=planeDAO.findById(pxuList.get(j).getPlane_Id()).get();
    					if(plane.getState()==2) {
    						sip.setStatus(false);
    						sip.setName(plane.getName());
    						sip.setDescription(plane.getDescription());
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
    
    @Transactional
    public ArrayList<SendInfoPlane> GetAllPlanes(String email,String user){
    	if(!Check(email, 2)) {
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
    				System.out.println(usu);
    			}
    			if(pxuList.size()>0) {
    				Plane plane;
    	    		SendInfoPlane sip;
    	    		for (int j = 0; j < pxuList.size(); j++) {
    					plane= new Plane();
    					sip= new SendInfoPlane();
    					plane=planeDAO.findById(pxuList.get(j).getPlane_Id()).get();
    					if(plane.getState()==4||plane.getState()==3) {
    						sip.setStatus(true);
    					}else if(plane.getState()==2) {
    						sip.setStatus(false);
    					}else {
    						sip.setStatus(null);
    					}
    					sip.setName(plane.getName());
						sip.setDescription(plane.getDescription());
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

	private void CreateFolderBasement(int number, int j) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number+"/sotano "+j);
		direc.mkdir();
		CreateFolderApprovedReview("C:/Users/javier/Desktop/planos/Edificio "+number+"/sotano "+j);
	}

    private void copyPlane(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }
	
	private void CreateFolderFloor(int number, int i) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number+"/piso "+i);
		direc.mkdir();
		CreateFolderApprovedReview("C:/Users/javier/Desktop/planos/Edificio "+number+"/piso "+i);
		
	}

	private void CreateFolderApprovedReview(String ruta) {
		File direc= new File(ruta+"/aprobado");
		direc.mkdir();
		direc=new File(ruta+"/revisión");
		direc.mkdir();
	}

	private boolean CreateFolder(int number) {
			File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number);
			 return direc.mkdir();
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
	
	private boolean Check(String email) {
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
		return true;
		}else {
			return false;
		}
	}
	
	private boolean Verificar(User usuario) {
		User us = new User();
		List<User> u= new ArrayList<User>();
		Iterable<User>I;
		us.setEmail(usuario.getEmail());
		us.setName(usuario.getName());
		us.setType(null);
		us.setPassword(null);
		Example<User>userExample=Example.of(us);
		I=userDAO.findAll(userExample);
		for(User usu:I) {
			u.add(usu);
		}
		if(u.size()==0) {
			return true;
		}else
			return false;
		}

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

	private boolean ChecknamePorts(ArrayList<String> list,String name) {
		boolean exist=true;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).trim().matches(name.trim())) {
				exist=false;
			}
		}
		return exist;
	}
	
}
