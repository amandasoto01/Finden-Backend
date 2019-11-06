package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.*;

public interface FacadeGeneral {
		
	public Request Login(User usuario);
	
	public Request Enviar(User user);
	
	public Request Correguir (Correction nuevo);

}
