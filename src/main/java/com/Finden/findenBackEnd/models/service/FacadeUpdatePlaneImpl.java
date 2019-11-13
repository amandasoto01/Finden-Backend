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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.PlaneDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.ApprovePlane;
import com.Finden.findenBackEnd.models.entity.Plane;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es la logica de negocio de modificar un plano
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeUpdatePlaneImpl implements FacadeUpdatePlane{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PlaneDAO planeDAO;
	/**
	 * Método para aprovar o rechazar un plano
	 * @param Email correo de quien esta haciendo la acción
	 * @param approvePlane nombre del plano y si se recazo o aprovo
	 * @return si el servicio funciono o no 
	 */
	@Transactional
	public Request ApprovePlane(String email, ApprovePlane approvePlane) {
		Request res= new Request();
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
						File dir = new File("C:/Users/finden/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado");
						String [] NFiles=dir.list();
						String path="C:/Users/finden/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado/"+building+"-"+number+"-A-"+(NFiles.length+1)+".dxf";
						pathNumber=plane.getDir();
						plane.setDir(path);
						if(approvePlane.getDescription()!=null) {
							plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName()+"-Observación:"+approvePlane.getDescription());
						}else {
							plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName());
						}
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
						}
						res.setRequest(true);
						res.setRes("se aprobo exitosamente el plano");
						return res;
					}else {
						plane.setState(4);
						Date date= new Date();
						plane.setDateApproval(date);
						String path="C:/Users/finden/Desktop/planos/Edificio "+building+"/"+pathNumber+"/aprobado/"+building+"-"+number+"-A-1.dxf";
						pathNumber=plane.getDir();
						plane.setDir(path);
						if(approvePlane.getDescription()!=null) {
							plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName()+"-Observación:"+approvePlane.getDescription());
						}else {
							plane.setObservation("Se aprobo por el usuario: "+u.get(0).getName());
						}
						plane.setVersion(1);
						try {
							File file = new File(pathNumber);
							copyPlane(pathNumber, path);
							plane.setName(building+"-"+number+".dxf");
							file.delete();
							planeDAO.save(plane);
						} catch (IOException e) {
							res.setRequest(false);
							res.setRes("error: "+e.toString());
							return res;
						}
						res.setRequest(true);
						res.setRes("se aprobo exitosamente el plano");
						return res;
					}
				}else {
					if(plane.getState()==1) {
						plane.setState(2);
						if(approvePlane.getDescription()!=null) {
							plane.setObservation("El plano a sido rechazado por el usuario:" +u.get(0).getName()+"-Observación:"+approvePlane.getDescription());
						}else {
							plane.setObservation("El plano a sido rechazado por el usuario:" +u.get(0).getName());
						}
						planeDAO.save(plane);
						res.setRequest(true);
						res.setRes("Se rechazo el plano exitosamente");
						return res;
					}else {
						res.setRequest(false);
						res.setRes("Este plano ya a sido aprobado anteriormente");
						return res;
					}
				}
			}else {
				res.setRequest(false);
				res.setRes("No existe plano con ese nombre");
				return res;
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene permisos para esto");
			return res;
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
	 * Método para cambiar la ruta de un archivo 
	 * @param origin donde se encuentra el archivo
	 * @param destination lugar donde se va guardar el archivo
	 */
    private void copyPlane(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }
}
