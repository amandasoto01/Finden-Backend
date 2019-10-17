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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.PlaneDAO;
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
				if(CheckWiringCenterSwitch(add.getWiringCenter(),add.getSwitch())) {
					if(CheckNamePort(add.getType())!=0) {
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
							s.setWritingCenter_id(Wc.getId());
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
							portDAO.save(port);
						return "Puerto Creado con exito";	
					}else {
						return "El tipo del puerto esta mal escrito";
					}
				}else {
					return"El switch o el centro de cableado no existe";
				}
				
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
	//falta resto
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
					actual = new Plane();
					actual.setState(4);
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
						StringTokenizer token = new StringTokenizer(plane.getName(),"-");
						String building=token.nextToken().trim();
						String number= token.nextToken().trim();
						String pathNumber;
						if(number.contains("P")) {
							pathNumber= "piso "+number.substring(1).trim();
						}else {
							pathNumber= "sotano "+number.substring(1).trim();
						}
						File dir = new File("C:/Users/javier/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado");
						System.out.println("C:/Users/javier/Desktop/planos/"+building+"/"+pathNumber+"/aprobado");
						String [] NFiles=dir.list();
						String path="C:/Users/javier/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado/"+building+"-"+number+"-A-"+(NFiles.length+1)+".dxf";
						pathNumber=plane.getDir();
						plane.setDir(path);
						plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName());
						try {
							File file = new File(pathNumber);
							copyPlane(pathNumber, path);
							plane.setName(building+"-"+number+"-A-1.dxf");
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
						StringTokenizer token = new StringTokenizer(plane.getName(),"-");
						String building=token.nextToken().trim();
						String number= token.nextToken().trim();
						String pathNumber;
						if(number.contains("P")) {
							pathNumber= "piso "+number.substring(1).trim();
						}else {
							pathNumber= "sotano "+number.substring(1).trim();
						}
						String path="C:/Users/javier/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado/"+building+"-"+number+"-A-1.dxf";
						pathNumber=plane.getDir();
						plane.setDir(path);
						plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName());
						try {
							File file = new File(pathNumber);
							copyPlane(pathNumber, path);
							plane.setName(building+"-"+number+"-A-1.dxf");
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

    public void copyPlane(String origin, String destination) throws IOException {
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
	
	private boolean CheckWiringCenterSwitch(String wc, int switches) {
		WritingCenter Wc= wcDAO.findByName(wc);

		if(Wc!=null) {
			Switch s = new Switch();
			List<Switch> u= new ArrayList<Switch>();
			Iterable<Switch>SI;
			s.setWritingCenter_id(Wc.getId());
			s.setNumeroSwitch(switches);
			s.setIndex(null);
			s.setId(null);
			Example<Switch>switchExample=Example.of(s);
			SI=switchDAO.findAll(switchExample);
			for(Switch swit:SI) {
				u.add(swit);
			}
			if(u.size()>0) {
			return true;
			}else {
				return false;
			}
		}else {
			return false;
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
	
	public boolean Verificar(User usuario) {
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


}
