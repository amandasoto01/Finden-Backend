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
public class FacadeLoginImpl implements FacadeLogin{

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional(readOnly=true)
	public Request Login(User usuario) {
		Request res= new Request();
		List<User> u= new ArrayList<User>();
		if(usuario.getEmail()==null) {
			res.setRequest(false);
			return res;
		}else {
			if(usuario.getPassword()==null) {
				res.setRequest(false);
				return res;
			}
			else {
				Iterable<User>I;
				usuario.setId(null);
				usuario.setName(null);
				usuario.setType(null);
				usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
				Example<User>userExample=Example.of(usuario);
				I=userDAO.findAll(userExample);
				for(User us:I) {
					u.add(us);
				}
				if(u.size()>0) {
					if(u.get(0).getType()==1 && usuario.getEmail().equals(u.get(0).getEmail())) {
						res.setRequest(true);
						res.setRes("DTI");
						return res;
					}else if(u.get(0).getType()==2 && usuario.getEmail().equals(u.get(0).getEmail())) {
						res.setRequest(true);
						res.setRes("mesa de servicios");
						return res;
					}
					else {
						res.setRequest(true);
						res.setRes("contratista");
						return res;
					}
				}else {
					res.setRequest(false);
					return res;
				}
			}
		}

	}
}
