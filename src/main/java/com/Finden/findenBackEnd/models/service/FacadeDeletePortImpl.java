package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.PortDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.Port;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

@Service
public class FacadeDeletePortImpl implements FacadeDeletePort{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PortDAO  portDAO;
	
	@Transactional
	public Request DeletePort(String port, String email) {
		Request res= new Request();
		if(Check(email, 1)) {
			if(port==null||port=="") {
				res.setRequest(false);
				res.setRes("no se envio el nombre del puerto");
				return res;
			}else {
				Port p=portDAO.findByName(port);
				if(p==null) {
					res.setRequest(false);
					res.setRes("El puerto no existe recuerde que el nombre del puerto debe ser: nombre del centro de cableado “-” número de identificación del puerto en el patchpanel. ");
					return res;
				}else {
					portDAO.delete(p);
					res.setRequest(true);
					res.setRes("Puerto eliminado exitosamente");
					return res;
				}
				
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene permiso para realizar esta acción");
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
