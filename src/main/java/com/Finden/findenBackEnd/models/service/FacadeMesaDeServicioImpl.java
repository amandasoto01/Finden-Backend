package com.Finden.findenBackEnd.models.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.Wire;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.PortDAO;
import com.Finden.findenBackEnd.models.dao.SwitchDAO;
import com.Finden.findenBackEnd.models.dao.UserDAO;
import com.Finden.findenBackEnd.models.dao.WritingCenterDAO;
import com.Finden.findenBackEnd.models.entity.GetInfo;
import com.Finden.findenBackEnd.models.entity.Port;
import com.Finden.findenBackEnd.models.entity.SendPort;
import com.Finden.findenBackEnd.models.entity.Switch;
import com.Finden.findenBackEnd.models.entity.User;
import com.Finden.findenBackEnd.models.entity.WritingCenter;
import com.google.gson.Gson;

@Service
public class FacadeMesaDeServicioImpl implements FacadeMesaDeServicio{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PortDAO portDAO;
	
	@Autowired
	private WritingCenterDAO wcDAO;
	
	@Autowired
	private SwitchDAO switchDAO;
	
	@Override
	@Transactional
	public SendPort FindPort(String email, String port) {
		if(Check(email, 1)||Check(email, 2)) {
			Port findPort= portDAO.findByName(port);
			if(findPort!=null) {
				WritingCenter wc= new WritingCenter();
				wc=wcDAO.findById(findPort.getSwitch_WritingCenter_id()).get();
				Switch s=new Switch();
				s=switchDAO.findById(findPort.getSwitch_id()).get();
				try {
					System.out.println("puerto en el switch "+findPort.getPortInSwitch());
					System.out.println("index: "+s.getIndex());
					GetInfo info=FindHpeIMC(wc.getIdWirtingCenter(),findPort.getPortInSwitch()+s.getIndex());
					SendPort newPort= new SendPort();
					newPort.setDescription(info.getIfDescription());
					newPort.setMac(info.getPhyAddress());
					newPort.setSpeed(info.getIfspeed().toString());
					newPort.setState(info.getAdminStatusDesc());
					return newPort;
				} catch (IOException e) {
					System.out.println(e.toString());
					return null;
				}
			}else {
				return null;
			}
		}else {
			return null;
		}
		
	}

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
	
	private GetInfo FindHpeIMC(int wc,int port) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getCredentialsProvider().setCredentials(
            new AuthScope("10.26.1.103", 8080, "iMC RESTful Web Services"),
            new UsernamePasswordCredentials("puj_finden", "javeriana2019*"));
        HttpGet get = new HttpGet("http://10.26.1.103:8080/imcrs/plat/res/device/"+wc+"/interface/"+port);
        get.addHeader("accept", "application/json");
        HttpResponse response = client.execute(get);
        Gson gson= new Gson();
        GetInfo info=gson.fromJson(EntityUtils.toString(response.getEntity()), GetInfo.class);
        return info;
   
	}
}
