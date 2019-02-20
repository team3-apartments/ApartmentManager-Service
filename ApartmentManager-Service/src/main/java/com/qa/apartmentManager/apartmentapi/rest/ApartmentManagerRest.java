package com.qa.apartmentManager.apartmentapi.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;
import com.qa.apartmentManager.apartmentapi.persistence.domain.SentApartmentManager;
import com.qa.apartmentManager.apartmentapi.service.ApartmentManagerService;

@CrossOrigin
@RequestMapping("${path.base}")
@RestController
public class ApartmentManagerRest {

	@Autowired
	private ApartmentManagerService service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	

	
	@GetMapping("${path.getApartmentManager}")
	public List<ApartmentManager> getApartmentManager() {
		return service.getApartmentManager();
	}

	@GetMapping("${path.getApartmentManagerById}")
    public ApartmentManager getApartmentManager(@PathVariable Long id) {
        return service.getApartmentManager(id);
    }

	@DeleteMapping("${path.deleteApartmentManager}")
    public ResponseEntity<Object> deleteApartmentManager(@PathVariable Long id) {
        return service.deleteApartmentManager(id);
    }
	
	 @PutMapping("${path.updateApartmentManager}")
	    public ResponseEntity<Object> updateApartmentManager(@RequestBody ApartmentManager apartmentmanager, @PathVariable Long id) {
	        return service.updateApartmentManager(apartmentmanager, id);
	    }
	
	 @PostMapping("${path.createApartmentManager}")
	    public ApartmentManager createApartmentManager(@RequestBody ApartmentManager apartmentManager) {
	        sendToQueue(apartmentManager);
	    	return service.addApartmentManager(apartmentManager);
	    }
	
	 private void sendToQueue(ApartmentManager apartmentManager){
	        SentApartmentManager apartmentManagerToStore =  new SentApartmentManager(apartmentManager);
	        jmsTemplate.convertAndSend("ApartmentManagerQueue", apartmentManagerToStore);
	    }
}
