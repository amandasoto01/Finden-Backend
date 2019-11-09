package com.Finden.findenBackEnd.models.service;

import com.Finden.findenBackEnd.models.entity.Request;

public interface FacadeDeletePort {
	
	public Request DeletePort(String port, String email);

}
