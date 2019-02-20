package com.qa.apartmentManager.apartmentapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;
import com.qa.apartmentManager.apartmentapi.service.ApartmentManagerService;

@CrossOrigin
@RequestMapping("${path.base}")
@RestController
public class ApartmentManagerQueriesRest {

	@Autowired
	private ApartmentManagerService service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@GetMapping("${path.getByAFilter}")
	public List<ApartmentManager> getByAFilter(@PathVariable String value) {
		return service.getByAFilter(value);
	}
	
	@GetMapping("${path.getIsOccupied}")
	public List<ApartmentManager> getIsOcciped(@PathVariable boolean value) {
		return service.getIsOccupied(value);
	}
	
	@GetMapping("${path.getIntake}")
	public List<ApartmentManager> getIntake(@PathVariable String value) {
		return service.getIntake(value);
	}
}
