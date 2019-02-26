package com.qa.apartmentManager.apartmentapi.service;


import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;

public interface ApartmentManagerService {

	ApartmentManager getApartmentManager(Long id);
	
	ApartmentManager addApartmentManager(ApartmentManager apartmentmanager);
	
	ResponseEntity<Object> deleteApartmentManager(Long Id);
	
	ResponseEntity<Object> updateApartmentManager(ApartmentManager apartmentManager, Long Id);
	
	String upDateH2(List<ApartmentManager> toSave);
	
	List<ApartmentManager> getApartmentManager();
	
	List<ApartmentManager> getCurrentApartmentManager();
	
	List<ApartmentManager> getApartmentByDetails(String apartmentBuilding, int apartmentNumber);
	
	List<ApartmentManager> getByACleanStatus(String value);
	
	List<ApartmentManager> getIsOccupied(boolean value);
	
	List<ApartmentManager> getIntake(String value);
	
	List<ApartmentManager> checkDate(String date) throws ParseException;
}
