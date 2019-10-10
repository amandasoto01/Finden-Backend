package com.Finden.findenBackEnd.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Finden.findenBackEnd.models.dao.PersonaDAO;
import com.Finden.findenBackEnd.models.entity.personas;

@Service
public class PersonaServiceImpl implements PersonaService {

	// need to inject persona DAO
	@Autowired
	private PersonaDAO personaDAO;
	

	@Override
	@Transactional(readOnly=true)
	public List<personas> getPersona() {
		return (List<personas>) personaDAO.findAll();
	}

	@Override
	@Transactional
	public void savePersona(personas persona) {

		personaDAO.save(persona);
	}

	@Override
	@Transactional(readOnly=true)
	public personas getPersona(Integer theId) {

		return personaDAO.findById(theId).get();
	}

	@Override
	@Transactional
	public void deletePersona(Integer theId) {

		personaDAO.deleteById(theId);
	}

	@Override
	@Transactional
	public Iterable<personas> findbyName(personas persona) {
		Example<personas> employeeExample = Example.of(persona);
		return personaDAO.findAll(employeeExample);
	}


}
