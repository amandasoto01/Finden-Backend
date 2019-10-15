package com.Finden.findenBackEnd.models.service;

import org.springframework.web.multipart.MultipartFile;

public interface FacadeContratista {
	
	public String AddPLane(String Email, MultipartFile file);
	
	public String CheckPlane(String Email, MultipartFile file);

}
