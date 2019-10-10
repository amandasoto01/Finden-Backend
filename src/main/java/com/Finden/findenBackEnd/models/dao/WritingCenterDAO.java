package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.WritingCenter;

public interface WritingCenterDAO extends CrudRepository<WritingCenter, Integer>,QueryByExampleExecutor<WritingCenter>{

}
