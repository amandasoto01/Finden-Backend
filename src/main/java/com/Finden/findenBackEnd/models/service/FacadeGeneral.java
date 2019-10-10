package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.*;

public interface FacadeGeneral {
		
	public String Login(User usuario);
	
	public String Enviar(String correo);
	
	public String Correguir (Correction nuevo);

}
