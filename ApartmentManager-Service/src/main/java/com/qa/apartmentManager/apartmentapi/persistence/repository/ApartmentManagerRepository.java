package com.qa.apartmentManager.apartmentapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.apartmentManager.apartmentapi.persistence.domain.ApartmentManager;


@Repository
public interface ApartmentManagerRepository extends JpaRepository<ApartmentManager, Long> {

}
