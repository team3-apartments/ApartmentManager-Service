package com.qa.apartmentManager.apartmentapi.service;


import java.text.ParseException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;

public interface ApartmentManagerService {

	List<ApartmentManager> getApartmentManager();
	List<ApartmentManager> getCurrentApartmentManager();
	
	ApartmentManager getApartmentManager(Long id);
	
	ApartmentManager addApartmentManager(ApartmentManager apartmentmanager);
	
	ResponseEntity<Object> deleteApartmentManager(Long Id);
	
	ResponseEntity<Object> updateApartmentManager(ApartmentManager apartmentManager, Long Id);
	
	String upDateH2(List<ApartmentManager> toSave);
	
	List<ApartmentManager> getByAFilter(String value);
	List<ApartmentManager> getIsOccupied(boolean value);
	List<ApartmentManager> getIntake(String value);
	List<ApartmentManager> checkDate(String date) throws ParseException;
}
