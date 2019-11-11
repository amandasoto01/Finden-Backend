package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.PlaneXUser;;
/**
 * Esta clase es el enlace de la Base de datos con el servidor de los objetos PlanexUser
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
public interface PlaneXUserDAO extends CrudRepository<PlaneXUser, Integer>,QueryByExampleExecutor<PlaneXUser>{

}
