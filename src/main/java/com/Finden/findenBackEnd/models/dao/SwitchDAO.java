package com.Finden.findenBackEnd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Switch;

public interface SwitchDAO extends CrudRepository<Switch, Integer>,QueryByExampleExecutor<Switch> {
	
	List<Switch> findAll();
	
}
