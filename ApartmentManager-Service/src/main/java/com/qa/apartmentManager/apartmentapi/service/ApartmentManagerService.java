package com.qa.apartmentManager.apartmentapi.service;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;

public interface ApartmentManagerService {

	List<ApartmentManager> getApartmentManager();
	
	ApartmentManager getApartmentManager(Long id);
	
	ApartmentManager addApartmentManager(ApartmentManager apartmentmanager);
	
	ResponseEntity<Object> deleteApartmentManager(Long Id);
	
	ResponseEntity<Object> updateApartmentManager(ApartmentManager apartmentManager, Long Id);
	
	
	List<ApartmentManager> getByAFilter(String value);
	List<ApartmentManager> getIsOccupied(boolean value);
}
