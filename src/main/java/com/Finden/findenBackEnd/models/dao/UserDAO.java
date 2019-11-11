package com.Finden.findenBackEnd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.User;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos User
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface UserDAO extends CrudRepository<User, Integer>,QueryByExampleExecutor<User> {
	/**
	 * Este metodo busca todos los usuarios guardados en la base de datos.
	 * @return La lista de usuarios guardados en la base de datos.
	 */
	List<User> findAll();
	

}
