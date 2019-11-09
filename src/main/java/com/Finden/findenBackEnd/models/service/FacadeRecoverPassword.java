package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Correction;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.User;

public interface FacadeRecoverPassword {
	
	public Request Send(User user);
	
	public Request CorrectPassword (Correction nuevo);

}
