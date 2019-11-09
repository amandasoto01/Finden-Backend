package com.Finden.findenBackEnd.models.service;

import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeAddPlane {
	
	public Request AddPLane(String Emai, MultipartFile file,String description);

}
