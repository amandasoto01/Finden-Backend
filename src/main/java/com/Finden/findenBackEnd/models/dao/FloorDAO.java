package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Floor;

public interface FloorDAO extends CrudRepository<Floor, Integer>,QueryByExampleExecutor<Floor>{

}
