package com.Finden.findenBackEnd.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.Finden.findenBackEnd.models.entity.User;

public interface UserDAO extends CrudRepository<User, Integer>,QueryByExampleExecutor<User> {

}
