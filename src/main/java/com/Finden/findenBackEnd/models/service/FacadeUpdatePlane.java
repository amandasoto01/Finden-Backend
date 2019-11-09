package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.ApprovePlane;
import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeUpdatePlane {
	
	public Request ApprovePlane(String email,ApprovePlane approvePlane);

}
