package com.qa.apartmentManager.apartmentapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;
import com.qa.apartmentManager.apartmentapi.persistence.repository.ApartmentManagerRepository;
import com.qa.apartmentManager.apartmentapi.util.exceptions.ApartmentManagerNotFoundException;
import com.qa.apartmentManager.util.DateCalculator;

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
		if (apartmentManagerExists(id)) {
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Object> updateApartmentManager(ApartmentManager apartmentManager, Long id) {
		if (apartmentManagerExists(id)) {
			apartmentManager.setId(id);
			repo.save(apartmentManager);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	private boolean apartmentManagerExists(Long id) {
		Optional<ApartmentManager> apartmentManagerOptional = repo.findById(id);
		return apartmentManagerOptional.isPresent();
	}

	@Override
	public List<ApartmentManager> getByAFilter(String value) {
		return repo.findByCleanStatus(value);
	}

	@Override
	public List<ApartmentManager> getIsOccupied(boolean value) {
		return repo.findIsOccupied(value);
	}

	@Override
	public List<ApartmentManager> getIntake(String value) {
		return repo.findIntake(value);
	}

	@Override
	public List<ApartmentManager> checkDate(String date) {
		List<ApartmentManager> all = repo.findAll();
		String[] dateBreakdown = date.split("-");
		List<ApartmentManager> toFind = DateCalculator.verifyDates(all, dateBreakdown);
		repo.saveAll(toFind);
		return repo.findIsOccupied(false);
	}

	@Override
	public List<ApartmentManager> getCurrentApartmentManager() {
		List<ApartmentManager> all = repo.findAll();
		List<ApartmentManager> current = DateCalculator.checkForMultipleIntakes(all, all);
		repo.saveAll(current);
		return repo.findIsOccupied(false);
	}

	@Override
	public String upDateH2(List<ApartmentManager> toSave) {
		repo.saveAll(toSave);
		return "{\"message\": \"H2 updated\"}";
	}

}
