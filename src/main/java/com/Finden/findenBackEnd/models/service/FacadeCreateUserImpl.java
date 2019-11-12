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
/**
 * Esta clase es  la logica de negocio de crear usuarios
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@Service
public class FacadeCreateUserImpl implements FacadeCreateUser{
	
	@Autowired
	private UserDAO userDAO;
	/**
	 * Método para agregar un puerto 
	 * @param Email correo de quien esta haciendo la acción
	 * @param usuario información del usuario a agregar
	 * @return si la función funciono o no 
	 */
	@Override
	@Transactional
	public Request Create(User usuario, String email) {
		Request res= new Request();
		if(Check(email, 1)) {
			if(usuario.getEmail()==null) {
				res.setRequest(false);
				res.setRes("No se ingreso email");
				return res;
			}else if (usuario.getName()==null) {
				res.setRequest(false);
				res.setRes("No se ingreso nombre");
				return res;
			}else if(usuario.getPassword()==null) {
				res.setRequest(false);
				res.setRes("No se ingreso contraseña");
				return res;
			}else if(usuario.getType()==null) {
				res.setRequest(false);
				res.setRes("No se ingreso Correo");
				return res;
			}else {
				usuario.setId(null);
				usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
				if(Check(usuario)) {
					try {
						userDAO.save(usuario);
						res.setRequest(true);
						res.setRes("Usuario creado exitosamente");
						return res;
					} catch (Exception e) {
						res.setRequest(false);
						res.setRes(e.toString());
						return res;
					}
				}else {
					res.setRequest(false);
					res.setRes("el usuario ya exite");
					return res;
				}

			}
		}else {
			res.setRequest(false);
			res.setRes("el usuario no tiene permiso");
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
	 * Método que verifica si un usuario existe
	 * @param usuario la información de usuario a ser agregado
	 * @return Si el usuario existe o no 
	 */
	private boolean Check(User usuario) {
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
