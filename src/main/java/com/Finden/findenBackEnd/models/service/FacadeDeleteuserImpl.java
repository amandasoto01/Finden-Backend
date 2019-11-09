package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

@Service
public class FacadeDeleteuserImpl implements FacadeDeleteUser{
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public Request Delete(String correo, String email) {
		Request res = new Request();
		if(Check(email, 1)) {
			if(correo==null) {
				res.setRequest(false);
				res.setRes("No se enivo email");
				return res;
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
				res.setRequest(true);
				res.setRes("Usuario eliminado exitosamente");
				return res;
				}else {
					res.setRequest(false);
					res.setRes("el email no se encuentra en el sistema");
					return res;
				}
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario no tiene permisos");
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
