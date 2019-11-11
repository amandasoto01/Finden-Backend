package com.Finden.findenBackEnd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.WritingCenter;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos WiringCenter
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface WritingCenterDAO extends CrudRepository<WritingCenter, Integer>,QueryByExampleExecutor<WritingCenter>{

	/**
	 * Este metodo busca todos los centro de cableado guardados en la base de datos.
	 * @return La lista de centro de cableado guardados en la base de datos.
	 */
	List<WritingCenter> findAll();
	/**
	 * Este metodo busca un centros de cableado segun un nombre guardados en la base de datos.
	 * @param nombre del centro de cableado a buscar 
	 * @return El centro de cableado con un nombre en especifico guardados en la base de datos.
	 */
	WritingCenter findByName(String name);
	
	
}