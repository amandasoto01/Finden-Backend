package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

public interface FacadeLogin {
	
	public Request Login(User usuario);
	
}
