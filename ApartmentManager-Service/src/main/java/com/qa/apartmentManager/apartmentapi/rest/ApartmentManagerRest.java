package com.qa.apartmentManager.apartmentapi.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.qa.apartmentManager.util.JSONUtil;

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

	@Value("${url.mongoUpdate}")
	private String mongoUpdate;
	
	@Value("${url.mongoDelete}")
	private String mongoDelete;
	
	@Value("${url.mongoUrl}")
	private String mongoUrl;

	@Value("${url.mongoService}")
	private String mongoService;

	@Value("${url.verifyAccountService}")
	private String verifyAccountService;

	@Value("${url.verifyUrl}")
	private String verifyUrl;

	//done
	@GetMapping("${path.checkPassword}")
	public String checkPassword(@PathVariable String password) {
		return verifyPassword(password);
	}

	//done
	@GetMapping("${path.getAllFromMongo}")
	public String getAllFromMongo() {
		return getMongoData();
	}

	//done
	@GetMapping("${path.getCurrentApartmentManager}")
	public List<ApartmentManager> getCurrentApartmentManager() {
		return service.getCurrentApartmentManager();
	}

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
		mongoDelete(id);
		return service.deleteApartmentManager(id);
	}

	@PutMapping("${path.updateApartmentManager}")
	public ResponseEntity<Object> updateApartmentManager(@RequestBody ApartmentManager apartmentmanager,
			@PathVariable Long id) {
		mongoUpdate(id, apartmentmanager);
		return service.updateApartmentManager(apartmentmanager, id);
	}

	@PostMapping("${path.createApartmentManager}")
	public ApartmentManager createApartmentManager(@RequestBody ApartmentManager apartmentManager) {
		sendToQueueForCreate(apartmentManager);
		return service.addApartmentManager(apartmentManager);
	}

	private void sendToQueueForCreate(ApartmentManager apartmentManager) {
		 SentApartmentManager apartmentManagerToStore = new
		 SentApartmentManager(apartmentManager);
		 jmsTemplate.convertAndSend("ApartmentManagerQueue", apartmentManagerToStore);
	}
	
	private void mongoDelete(Long id) {
		restTemplate.delete(mongoService+ mongoDelete + id);
	}
	
	private void mongoUpdate(Long id, ApartmentManager apartmentManager) {
		restTemplate.put(mongoService + mongoUpdate + id, apartmentManager);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getMongoData() {
		List<SentApartmentManager> mongo = restTemplate.getForObject(mongoService + mongoUrl, List.class);
		
		Object[] SAMArray = mongo.toArray();
		List<ApartmentManager> toConvert = new ArrayList<>();

		for (Object obj : SAMArray) {
			LinkedHashMap map = (LinkedHashMap) obj;
			int id = (int) map.get("apartmentId");
			String some = JSONUtil.getJSONForObject(obj);
			ApartmentManager toPrint = JSONUtil.getObjectForJSON(some, ApartmentManager.class);
			toPrint.setApartmentId((long) id);
			toConvert.add(toPrint);
		}

		return service.upDateH2((toConvert));
	}

	private String verifyPassword(String password) {
		return restTemplate.getForObject(verifyAccountService + verifyUrl + password, String.class);
	}
}
