package com.Finden.findenBackEnd.WS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Finden.findenBackEnd.models.entity.personas;
import com.Finden.findenBackEnd.models.service.PersonaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/finden")
public class PersonaRestController {

	// injection dependency
	@Autowired
	private PersonaService personaService;

	@GetMapping("/Getpersonas")
	public List<personas> getCustomers() {
		return personaService.getPersona();
	}

	
	@GetMapping("/Getpersona/{personaId}")
	public personas getCustomer(@PathVariable int personaId) {

		personas persona = personaService.getPersona(personaId);

		return persona;
	}

	// add mapping for POST /customers - add new customer

	@PostMapping("/CreatePersona")
	@ResponseStatus(HttpStatus.CREATED)
	public personas addCustomer(@RequestBody personas persona) {

		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update

		persona.setId(null);

		personaService.savePersona(persona);

		return persona;
	}
	
	// add mapping for PUT /customers - update existing customer
	
	@PutMapping("/Updatepersonas")
	@ResponseStatus(HttpStatus.CREATED)
	public personas updateCustomer(@RequestBody personas customer) {
		
		personaService.savePersona(customer);
		
		return customer;
	}
	
	
	//add mapping for DELETE /customers/{customerId} - delete existing customer
	@DeleteMapping("/Deletepersonas/{personaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable int personaId) {
		System.out.println(personaId);
		personaService.deletePersona(personaId);
	}
	
	@GetMapping("/PersonaByName")
	public List<personas> FindByName(@RequestBody personas persona) {
		List<personas> p= new ArrayList<personas>();
		Iterable<personas>I;
		persona.setId(null);
		I=personaService.findbyName(persona);
		for(personas per:I) {
			p.add(per);
		}
		return p;
	}

}