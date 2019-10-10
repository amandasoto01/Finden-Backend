package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Port;

public interface PortDAO extends CrudRepository<Port, Integer>,QueryByExampleExecutor<Port>{

}
