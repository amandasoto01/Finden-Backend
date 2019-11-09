package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.UpdatePort;

public interface FacadeUpdatePort {
	
	public Request UpdatePort(String email,UpdatePort updatePort);
	
	public String Switches(String email,ListPorts listports);

}
