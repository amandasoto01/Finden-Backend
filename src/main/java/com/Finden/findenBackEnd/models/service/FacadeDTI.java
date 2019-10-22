package com.Finden.findenBackEnd.models.service;

import java.io.File;
import java.util.ArrayList;


import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.UpdatePort;
import com.Finden.findenBackEnd.models.entity.ApprovePlane;
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.HistorialPlane;
import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.PortList;
import com.Finden.findenBackEnd.models.entity.SendInfoBuildng;
import com.Finden.findenBackEnd.models.entity.SendInfoPlane;
import com.Finden.findenBackEnd.models.entity.SendInfoUser;


public interface FacadeDTI {

	public String Create(User usuario, String email);
	
	public String UpdateUser(User usuario, String email);
	
	public String Delete(String correo, String email);
	
	public String CreateBuilding(String correo, AddBuilding add);
	
	public String CreateWiringCenter(String correo, Addwritingcenter add);
	
	public String CreatePort(String correo, AddPort add);
	
	public String DeletePort(String port, String email);
	
	public String UpdatePort(String email,UpdatePort updatePort);
	
	public String ApprovePlane(String email,ApprovePlane approvePlane);
	
	public ArrayList<SendInfoUser>GetUsers(String email);
	
	public ArrayList<SendInfoBuildng>GetBuildings(String email);
	
	public File GetPlane(String email,GetPlane plane);
	
	public ArrayList<PortList> GetPlanePorts(String email,GetPlane plane);

	public String Switches(String email,ListPorts listports);
	
	public ArrayList<HistorialPlane> Historial(String email,String plane);
	
	public ArrayList<SendInfoPlane> GetApproved(String email,String user);
	
	public ArrayList<SendInfoPlane> GetRejected(String email,String user);
	
	public ArrayList<SendInfoPlane> GetAllPlanes(String email,String user);
}