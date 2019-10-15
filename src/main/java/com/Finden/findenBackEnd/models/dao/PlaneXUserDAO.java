package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.PlaneXUser;;

public interface PlaneXUserDAO extends CrudRepository<PlaneXUser, Integer>,QueryByExampleExecutor<PlaneXUser>{

}
