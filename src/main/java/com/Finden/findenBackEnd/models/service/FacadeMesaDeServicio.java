package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.SendPort;

public interface FacadeMesaDeServicio {
	
	public SendPort FindPort(String email,String port);

}
