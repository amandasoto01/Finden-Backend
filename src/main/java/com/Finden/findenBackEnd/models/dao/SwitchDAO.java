package com.Finden.findenBackEnd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Switch;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos Switch
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface SwitchDAO extends CrudRepository<Switch, Integer>,QueryByExampleExecutor<Switch> {

	/**
	 * Este metodo busca todos los switches guardados en la base de datos.
	 * @return La lista de switches guardados en la base de datos.
	 */
	List<Switch> findAll();
	
}
