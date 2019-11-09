package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeDeleteUser {
	
	public Request Delete(String correo, String email);

}
