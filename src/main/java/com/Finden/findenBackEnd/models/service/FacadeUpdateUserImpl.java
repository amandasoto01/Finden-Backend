package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

@Service
public class FacadeUpdateUserImpl implements FacadeUpdateUser{
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public Request UpdateUser(User usuario, String email) {
		Request res = new Request();
		if(Check(email,0)) {
			if(usuario.getEmail()==null) {
				res.setRequest(false);
				res.setRes("No se enivo correo");
				return res;
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
					if(usuario.getPassword()==null||usuario.getPassword()==u.get(0).getPassword()) {
						usuario.setPassword(u.get(0).getPassword());	
					}else {
						
						usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));	
					}
					if(usuario.getType()==null) {
						usuario.setType(u.get(0).getType());
					}
					usuario.setId(u.get(0).getId());
					userDAO.save(usuario);
					res.setRequest(false);
					res.setRes("usuario modificado exitosamente");
					return res;
				}else {
					res.setRequest(false);
					res.setRes("El correo no existe");
					return res;
				}
			}
		}else {
			res.setRequest(false);
			res.setRes("El usuario existe");
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
		if(i!=0) {
			us.setType(i);
		}
		Example<User>userExample=Example.of(us);
		I=userDAO.findAll(userExample);
		for(User usu:I) {
			u.add(usu);
		}
		if(u.size()>0) {
			if(i==0) {
				return true;
			}else if(u.get(0).getType()==i) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

}
