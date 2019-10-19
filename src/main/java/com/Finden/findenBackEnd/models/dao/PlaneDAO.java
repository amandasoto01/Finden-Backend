package com.Finden.findenBackEnd.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Plane;

public interface PlaneDAO extends CrudRepository<Plane, Integer>,QueryByExampleExecutor<Plane>{

	Plane findByName(String name);
}