package com.Finden.findenBackEnd.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Plane;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos Plane
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface PlaneDAO extends CrudRepository<Plane, Integer>,QueryByExampleExecutor<Plane>{
	/**
	 * Este metodo busca un plano segun un nombre guardados en la base de datos.
	 * @param nombre del plano a buscar 
	 * @return El plano con un nombre en especifico guardados en la base de datos.
	 */
	Plane findByName(String name);
}
