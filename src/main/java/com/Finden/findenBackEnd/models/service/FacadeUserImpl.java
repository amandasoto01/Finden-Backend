package com.Finden.findenBackEnd.models.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.Finden.findenBackEnd.WS.entities.CreateUserWebServiceEntity;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.entity.User;

public class FacadeUserImpl implements FacadeUser {
	
	//Servicio que se comunica con la base de datos
	//Permite guardar objetos, actualizar y hacer queries
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public String createUser(CreateUserWebServiceEntity persona) {
		// TODO Auto-generated method stub
		System.out.println("Voy a crear un usuario");
		System.out.println(persona.getEmail());
		
		//Crear una entidad de la tabla
		User newUser = new User();
		newUser.setName(persona.getName());
		newUser.setPassword(persona.getPassword());
		newUser.setType(Integer.valueOf(persona.getType()));
		
		
		try {
			//Guardar e base de datos
			userDAO.save(newUser);
		}catch(Exception e) {
			return "Database error ";
		}
		
		return "newUser created :)";
	}
	
	
}
