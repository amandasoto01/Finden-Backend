package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

public interface FacadeCreateUser {
	
	public Request Create(User usuario, String email);

}
