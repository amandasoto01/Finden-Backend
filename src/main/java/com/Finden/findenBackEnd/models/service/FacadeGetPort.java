package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.AllInfoPort;

public interface FacadeGetPort {
	
	public AllInfoPort FindPort(String email,String port);
	
	public AddPort getPort(String email,String port);

}
