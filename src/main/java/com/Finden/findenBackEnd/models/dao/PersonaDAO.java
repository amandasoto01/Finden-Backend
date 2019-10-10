package com.Finden.findenBackEnd.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.personas;

public interface PersonaDAO extends CrudRepository<personas, Integer>,QueryByExampleExecutor<personas> {

}
