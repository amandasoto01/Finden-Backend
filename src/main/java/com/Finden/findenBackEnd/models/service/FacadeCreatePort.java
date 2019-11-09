package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.GetPlane;
import com.Finden.findenBackEnd.models.entity.PortList;
import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeCreatePort {
	
	public Request CreatePort(String correo, AddPort add);
	
	public ArrayList<PortList> GetPlanePorts(String email,GetPlane plane);
	
	public ArrayList<PortList> getPortsFloor(String email,Integer edificio, Integer piso);

}
