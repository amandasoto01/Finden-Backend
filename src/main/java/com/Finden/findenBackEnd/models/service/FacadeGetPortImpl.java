package com.Finden.findenBackEnd.models.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.BuildingDAO;
import com.Finden.findenBackEnd.models.dao.FloorDAO;
import com.Finden.findenBackEnd.models.dao.PortDAO;
import com.Finden.findenBackEnd.models.dao.SwitchDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.dao.WritingCenterDAO;
import com.Finden.findenBackEnd.models.entity.AddPort;
import com.Finden.findenBackEnd.models.entity.AllInfoPort;
import com.Finden.findenBackEnd.models.entity.GetInfo;
import com.Finden.findenBackEnd.models.entity.Port;
import com.Finden.findenBackEnd.models.entity.Switch;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.WritingCenter;
import com.google.gson.Gson;
/**
 * Esta clase es la logica de negocio de obtener información general un puerto
 * @author Javier Marin, Juan Sebastian Bastos, Amanda Soto
 * @version 11/11/2019
 */
@SuppressWarnings("deprecation")
@Service
public class FacadeGetPortImpl implements FacadeGetPort{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PortDAO portDAO;
	
	@Autowired
	private WritingCenterDAO wcDAO;
	
	@Autowired
	private SwitchDAO switchDAO;
	
	@Autowired
	private BuildingDAO buildingDAO;
	
	@Autowired
	private FloorDAO floorDAO;
	/**
	 * Método para obtener toda la información un puerto con la integración a HPeIMC
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a buscar
	 * @return Toda la información de un usuario
	 */
	@Override
	@Transactional
	public AllInfoPort FindPort(String email, String port) {
		if(Check(email, 1)||Check(email, 2)) {
			Port findPort= portDAO.findByName(port);
			if(findPort!=null) {
				if(findPort.getPortInSwitch()!=null) {
					WritingCenter wc= new WritingCenter();
					wc=wcDAO.findById(findPort.getSwitch_WritingCenter_id()).get();
					Switch s=new Switch();
					s=switchDAO.findById(findPort.getSwitch_id()).get();
					try {
						GetInfo info=FindHpeIMC(wc.getIdWirtingCenter(),findPort.getPortInSwitch()+s.getIndex()-1);
						AllInfoPort infoPort= new AllInfoPort();
						if(info==null) {
							infoPort.setMac("sin conexión a HPeIMC por favor ingrese usuario y contraseña en el servidor");
							infoPort.setSpeed("sin conexión a HPeIMC por favor ingrese usuario y contraseña en el servidor");
							infoPort.setState("sin conexión a HPeIMC por f avor ingrese usuario y contraseña en el servidor");
						}else {
							if(info.getAdminStatusDesc().equals("Down")) {
								infoPort.setMac("Sin conexión");
								infoPort.setSpeed("Sin conexión");
							}else {
								infoPort.setMac(info.getPhyAddress());
								infoPort.setSpeed(Parse(info.getIfspeed().toString()));
							}
							infoPort.setState(info.getAdminStatusDesc());
						}
						
						
						String parameters="Edificio: "+buildingDAO.findById(findPort.getFloor_Building_Id()).get().getName();
						parameters+=" N°: "+buildingDAO.findById(findPort.getFloor_Building_Id()).get().getNumber();
						infoPort.setBuilding(parameters);
						infoPort.setPortInSwitch(findPort.getPortInSwitch().toString());
						infoPort.setSwitchs(s.getNumeroSwitch().toString());
						if(floorDAO.findById(findPort.getFloor_Id()).get().getNumber()>0) {
							parameters="Piso - "+floorDAO.findById(findPort.getFloor_Id()).get().getNumber();
						}else {
							parameters="Sotano "+floorDAO.findById(findPort.getFloor_Id()).get().getNumber();
						}
						infoPort.setFloor(parameters);
						infoPort.setWiringCenter(wc.getName()+" - Switch: "+s.getNumeroSwitch()+" - Puerto: "+findPort.getPortInSwitch());
						return infoPort;
					} catch (IOException e) {
						return null;
					}
				}else {
					return null;
				}
			}else {
				return null;
			}
		}else {
			return null;
		}
		
	}
	/**
	 * Método para obtener toda la información un puerto en el sistema
	 * @param Email correo de quien esta haciendo la acción
	 * @param port nombre del puerto a buscar
	 * @return Toda la información de un usuario
	 */
	@Transactional
	public AddPort getPort(String email,String port) {
		AddPort send= new AddPort();
		if(Check(email, 1)) {
			Port p= portDAO.findByName(port);
			if(p!=null) {
				send.setName(p.getName());
				send.setBuilding(buildingDAO.findById(p.getFloor_Building_Id()).get().getNumber());
				send.setFloor(floorDAO.findById(p.getFloor_Id()).get().getNumber());
				send.setNPortSwitch(p.getPortInSwitch());
				if(p.getType()==1) {
					send.setType("V");
				}else if(p.getType()==2) {
					send.setType("D");
				}else if(p.getType()==3){
					send.setType("VD");
				}
				if(p.getSwitch_WritingCenter_id()!=null) {
					send.setWiringCenter(wcDAO.findById(p.getSwitch_WritingCenter_id()).get().getName());	
				}
				if(p.getSwitch_id()!=null) {
					send.setSwitch(switchDAO.findById(p.getSwitch_id()).get().getNumeroSwitch());
				}
				return send;
				
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	 /**
		 * Método que verifica si un usuario es un tipo en especifico
		 * @param Email- correo de quien esta haciendo la acción 
		 * @param i - El tipo de usuario donde 1 es DTI, 2 es mesa de servicio, 3 es contratista
		 * @return Si el usuario es de un tipo o no
		 */
	private boolean Check(String email, int i) {
		User us = new User();
		List<User> u= new ArrayList<User>();
		Iterable<User>I;
		us.setEmail(email);
		us.setName(null);
		us.setType(null);
		us.setPassword(null);
		Example<User>userExample=Example.of(us);
		I=userDAO.findAll(userExample);
		for(User usu:I) {
			u.add(usu);
		}
		if(u.size()>0) {
			if(u.get(0).getType()==i) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	 /**
		 * Método para la integración con HPeIMC(este metodo para que funcione se debe agregar usuario y contraseña)
		 * @param wc id del centro de cableado
		 * @param port id del puerto en switch
		 * @return estado, mac y velocidad del puerto
		 */
	private GetInfo FindHpeIMC(int wc,int port) throws ClientProtocolException, IOException {
        @SuppressWarnings("resource")
        String user="";
        String password="";
        if(!user.equals("")) {
        	DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(
                new AuthScope("10.26.1.103", 8080, "iMC RESTful Web Services"),
                new UsernamePasswordCredentials(user, password));
            HttpGet get = new HttpGet("http://10.26.1.103:8080/imcrs/plat/res/device/"+wc+"/interface/"+port);
            get.addHeader("accept", "application/json");
            HttpResponse response = client.execute(get);
            Gson gson= new Gson();
            GetInfo info=gson.fromJson(EntityUtils.toString(response.getEntity()), GetInfo.class);
            return info;	
        }else {
        	return null;
        }
   
	}
	/**
	 * Método para hacer mas legible la velocidad de conexión
	 * @param number velocidad de conexión en numero
	 * @return velocidad de conexicón en Kb, Mb o Gb
	 */
	private String Parse(String number) {
		String res= new String();
		int n= Integer.parseInt(number);
		int aux=1,aux1=0;
		while(aux!=n) {
			aux1++;
			aux=aux*10;
		}
		switch(aux1) {
		  case 1:
		    res=number;
		    break;
		  case 2:
		    res="10";
		    break;
		  case 3:
			  res="100";
			break;
		  case 4:
			  res="1Kb";
			  break;
		  case 5:
			  res="10Kb";
				break;
		  case 6:
			  res="100Kb";
				break;
		  case 7:
			  res="1Mb";
				break;
		  case 8:
			  res="10Mb";
				break;
		  case 9:
			  res="100Mb";
				break;
		  case 10:
			  res="1Gb";
				break;
		  case 11:
			  res="10Gb";
				break;
		  case 12:
			  res="100Gb";
				break;
		  default:
			  res=number;
		}

		return res;
	}
}
