package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.WS.entities.CreateUserWebServiceEntity;

public interface FacadeUser {
	
	public String createUser(CreateUserWebServiceEntity user);
	
}
