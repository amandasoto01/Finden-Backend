package com.Finden.findenBackEnd.models.service;
import com.Finden.findenBackEnd.models.entity.Request;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.PlaneDAO;
import com.Finden.findenBackEnd.models.dao.PlaneXUserDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.Plane;
import com.Finden.findenBackEnd.models.entity.PlaneXUser;

@Service
public class FacadeDeletePlaneImpl implements FacadeDeletePlane {

	@Autowired
	private UserDAO userDAO;
		
	@Autowired
	private PlaneDAO planeDAO;
	
	@Autowired
	private PlaneXUserDAO pxuDAO;
	
	
	@Transactional
	public Request DeletePlane(String Email, String NamePlane) {
		Request res= new Request();
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
							res.setRequest(true);
							res.setRes("El plano se elimino exitosamente");
							return res;
						}else {
							res.setRequest(false);
							res.setRes("Problema inesperado por favor comuniquese con la DTI javeriana");
							return res;	
						}
					}else {
						res.setRequest(false);
						res.setRes("Problema inesperado por favor comuniquese con la DTI javeriana");
						return res;
					}
				}else {
					res.setRequest(false);
					res.setRes("El plano no puede ser eliminado");
					return res;
				}
				
			}else {
				res.setRequest(false);
				res.setRes("El plano no existe");
				return res;
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene los permisos necesarios");
			return res;
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
