/**
 * Paquete que es el que genera la comunicación con la base de datos
 */
package com.Finden.findenBackEnd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Building;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos Building
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface BuildingDAO extends CrudRepository<Building, Integer>,QueryByExampleExecutor<Building>{

	/**
	 * Este metodo busca todos los edificios guardados en la base de datos.
	 * @return La lista de edificios guardados en la base de datos.
	 */
	List<Building> findAll();
}
