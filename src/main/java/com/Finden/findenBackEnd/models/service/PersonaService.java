package com.Finden.findenBackEnd.models.service;

import java.util.List;

import com.Finden.findenBackEnd.models.entity.personas;;

public interface PersonaService {
	
	public List<personas> getPersona();

	public void savePersona(personas theCustomer);

	public personas getPersona(Integer theId);

	public void deletePersona(Integer theId);
	
	public Iterable<personas> findbyName(personas persona);
}
