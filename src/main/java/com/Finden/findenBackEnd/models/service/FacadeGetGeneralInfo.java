package com.Finden.findenBackEnd.models.service;

import java.util.ArrayList;

import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.SendInfoBuildng;
import com.Finden.findenBackEnd.models.entity.SendInfoUser;


public interface FacadeGetGeneralInfo {
	
	
	public ArrayList<SendInfoUser>GetUsers(String email);
	
	public ArrayList<String> GetWritingCenter(String email);
	
	public ArrayList<SendInfoBuildng>GetBuildings(String email);
	
	public ArrayList<Integer>GetFloors(String email,String building);
	
	public User GetUser(String email,String user);
	
	public ArrayList<Integer> getSwitches(String email,String Wc);
	
	public String getUsername(String email);
}