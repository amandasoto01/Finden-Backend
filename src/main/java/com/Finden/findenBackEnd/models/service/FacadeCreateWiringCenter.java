package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeCreateWiringCenter {
	
	public Request CreateWiringCenter(String correo, Addwritingcenter add);

}
