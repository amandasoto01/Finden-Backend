package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Port;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos Port
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface PortDAO extends CrudRepository<Port, Integer>,QueryByExampleExecutor<Port>{
	/**
	 * Este metodo busca un puerto segun un nombre guardados en la base de datos.
	 * @param: nombre del puerto a buscar 
	 * @return: El puerto con un nombre en especifico guardados en la base de datos.
	 */
	Port findByName(String name);
}
