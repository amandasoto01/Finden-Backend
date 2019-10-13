package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.UpdatePort;;

public interface FacadeDTI {

	public String Create(User usuario, String email);
	
	public String UpdateUser(User usuario, String email);
	
	public String Delete(String correo, String email);
	
	public String CreateBuilding(String correo, AddBuilding add);
	
	public String CreateWiringCenter(String correo, Addwritingcenter add);
	
	public String CreatePort(String correo, AddPort add);
	
	public String DeletePort(String port, String email);
	
	public String UpdatePort(String email,UpdatePort updatePort);

}