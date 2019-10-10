package com.Finden.findenBackEnd.models.service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
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
							if(!problem) {
								floor= new Floor();
								floor.setBuilding_Id(b.get(0).getId());
								floor.setNumber(i);
								floorDAO.save(floor);
								problem=!CreateFolderFloor(building.getNumber(),i);
							}
						}
						if(!problem) {
							for (int j = 1; j <= add.getNBasement(); j++) {
								if(!problem) {
									floor= new Floor();
									floor.setBuilding_Id(b.get(0).getId());
									floor.setNumber(j*-1);
									floorDAO.save(floor);
									problem=!CreateFolderBasement(building.getNumber(),j);
								}
							}
							if(!problem) {
							return"Edificio agregado con exito";
							}else {
								return "La ruta para guardar los planos de los sotanos tienen problemas";
							}
						}else {
							return "La ruta para guardar los planos de los pisos tienen problemas";
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
					System.out.println(wc.toString());
					wcDAO.save(wc);
					return "Centro de cableado Creado";	
				}else {
					return "el edificio o el piso no existe";
				}
			}
		}else {
			return"El usuario no tiene los persmisos para realizar esta operación";
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

	private boolean CreateFolderBasement(int number, int j) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number+"/sotano "+j);
		 return direc.mkdir();
	}

	private boolean CreateFolderFloor(int number, int i) {
		File direc= new File("C:/Users/javier/Desktop/planos/Edificio "+number+"/piso "+i);
		 return direc.mkdir();
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
