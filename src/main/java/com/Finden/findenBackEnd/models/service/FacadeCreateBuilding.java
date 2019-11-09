package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeCreateBuilding {

	public Request CreateBuilding(String correo, AddBuilding add);
	
}
