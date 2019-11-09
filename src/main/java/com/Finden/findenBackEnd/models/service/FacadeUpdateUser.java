package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

public interface FacadeUpdateUser {
	
	public Request UpdateUser(User usuario, String email);

}
