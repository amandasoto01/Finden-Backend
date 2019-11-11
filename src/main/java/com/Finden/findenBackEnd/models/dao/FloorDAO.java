
/**
 * Paquete que es el que genera la comunicación con la base de datos
 */
package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Floor;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos Piso
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface FloorDAO extends CrudRepository<Floor, Integer>,QueryByExampleExecutor<Floor>{

}
