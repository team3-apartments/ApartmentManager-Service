package com.qa.apartmentManager.apartmentapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;
import com.qa.apartmentManager.apartmentapi.persistence.repository.ApartmentManagerRepository;
import com.qa.apartmentManager.apartmentapi.util.exceptions.ApartmentManagerNotFoundException;

@Service
public class ApartmentManagerServiceImpl implements ApartmentManagerService {

	@Autowired
	private ApartmentManagerRepository repo;

	public List<ApartmentManager> getApartmentManager() {
		return repo.findAll();
	}

	public ApartmentManager getApartmentManager(Long id) {
		Optional<ApartmentManager> apartmentManager = repo.findById(id);
		return apartmentManager.orElseThrow(() -> new ApartmentManagerNotFoundException(id.toString())); 
	}

	public ApartmentManager addApartmentManager(ApartmentManager apartmentmanager) {
		return repo.save(apartmentmanager); 
	}

	public ResponseEntity<Object> deleteApartmentManager(Long id) {
		 if(apartmentManagerExists(id)){
	            repo.deleteById(id);
	            return ResponseEntity.ok().build();
	        }
	        return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Object> updateApartmentManager(ApartmentManager apartmentManager, Long id) {
		 if(apartmentManagerExists(id)){
	            apartmentManager.setId(id);
	            repo.save(apartmentManager);
	            return ResponseEntity.ok().build();
	        }
	        return ResponseEntity.notFound().build();
	}
	
	
	 private boolean apartmentManagerExists(Long id){
	        Optional<ApartmentManager> apartmentManagerOptional = repo.findById(id);
	        return apartmentManagerOptional.isPresent();
	    }
	
	
}
