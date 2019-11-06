package com.Finden.findenBackEnd.models.service;

import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeContratista {
	
	public Request AddPLane(String Emai, MultipartFile file,String description);
	
	public Request CheckPlane(String Email, MultipartFile file);
	
	public Request DeletePlane(String Email,String NamePlane);

}
