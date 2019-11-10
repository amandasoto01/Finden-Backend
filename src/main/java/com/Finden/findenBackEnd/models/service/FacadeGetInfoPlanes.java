package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.HistorialPlane;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.SendInfoPlane;

public interface FacadeGetInfoPlanes {
	
	public Request CheckPlane(String Email, MultipartFile file);
	
	public ResponseEntity<Resource> GetPlane(String email,GetPlane plane);
	
	public ArrayList<HistorialPlane> Historial(String email,String plane);

	public ArrayList<SendInfoPlane> GetApproved(String email,String user);

	public ArrayList<SendInfoPlane> GetRejected(String email,String user);
	
	public ArrayList<SendInfoPlane> GetAllPlanes(String email,String user);

	public ArrayList<SendInfoPlane>GetAllPlanesDTI(String email,SendInfoPlane user);

	public ArrayList<SendInfoPlane> getAllPlanesActual(String email,SendInfoPlane user);
	
	public Integer planesToApprove(String email);
	
	public ArrayList<SendInfoPlane> getDTIPlanes (String email);
}
