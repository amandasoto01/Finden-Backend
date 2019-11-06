package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.Finden.findenBackEnd.models.entity.AddBuilding;
import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.Addwritingcenter;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.UpdatePort;
import com.Finden.findenBackEnd.models.entity.ApprovePlane;
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.HistorialPlane;
import com.Finden.findenBackEnd.models.entity.ListPorts;
import com.Finden.findenBackEnd.models.entity.Port;
import com.Finden.findenBackEnd.models.entity.PortList;
import com.Finden.findenBackEnd.models.entity.Request;
import com.Finden.findenBackEnd.models.entity.SendInfoBuildng;
import com.Finden.findenBackEnd.models.entity.SendInfoPlane;
import com.Finden.findenBackEnd.models.entity.SendInfoUser;


public interface FacadeDTI {

	public Request Create(User usuario, String email);
	
	public Request UpdateUser(User usuario, String email);
	
	public Request Delete(String correo, String email);
	
	public Request CreateBuilding(String correo, AddBuilding add);
	
	public Request CreateWiringCenter(String correo, Addwritingcenter add);
	
	public Request CreatePort(String correo, AddPort add);
	
	public Request DeletePort(String port, String email);
	
	public Request UpdatePort(String email,UpdatePort updatePort);
	
	public Request ApprovePlane(String email,ApprovePlane approvePlane);
	
	public ArrayList<SendInfoUser>GetUsers(String email);
	
	public ArrayList<String> GetWritingCenter(String email);
	
	public ArrayList<SendInfoBuildng>GetBuildings(String email);
	
	public ArrayList<Integer>GetFloors(String email,String building);
	
<<<<<<< HEAD
	public ResponseEntity<Resource> GetPlane(String email,GetPlane plane);
=======
	public File GetPlane(String email,GetPlane plane);
>>>>>>> f1b2a4b7441b257f12768a62b335fa22317ea764
	
	public ArrayList<PortList> GetPlanePorts(String email,GetPlane plane);

	public String Switches(String email,ListPorts listports);
	
	public ArrayList<HistorialPlane> Historial(String email,String plane);
	
	public ArrayList<SendInfoPlane> GetApproved(String email,String user);
	
	public ArrayList<SendInfoPlane> GetRejected(String email,String user);
	
	public ArrayList<SendInfoPlane> GetAllPlanes(String email,String user);
	
<<<<<<< HEAD
	public ArrayList<SendInfoPlane>GetAllPlanesDTI(String email,SendInfoPlane user);
	
	public User GetUser(String email,String user);
	
	public AddPort getPort(String email,String port);
=======
	public User GetUser(String email,String user);
	
	public Port getPort(String email,String port);
>>>>>>> f1b2a4b7441b257f12768a62b335fa22317ea764
	
	public ArrayList<Integer> getSwitches(String email,String Wc);
}