package com.Finden.findenBackEnd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.Building;

public interface BuildingDAO extends CrudRepository<Building, Integer>,QueryByExampleExecutor<Building>{

	List<Building> findAll();
}
