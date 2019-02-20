package com.qa.apartmentManager.apartmentapi.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;


@Repository
public interface ApartmentManagerRepository extends JpaRepository<ApartmentManager, Long> {

	@Query ("SELECT u FROM ApartmentManager u WHERE u.cleanStatus = :value")
	public List<ApartmentManager> findByCleanStatus(@Param ("value") String value);
	
	@Query ("SELECT u FROM ApartmentManager u WHERE u.occupied = :value")
	public List<ApartmentManager> findIsOccupied(@Param ("value") boolean value);
	
	@Query ("SELECT u FROM ApartmentManager u WHERE u.intake = :value")
	public List<ApartmentManager> findIntake(@Param ("value") String value);
}
