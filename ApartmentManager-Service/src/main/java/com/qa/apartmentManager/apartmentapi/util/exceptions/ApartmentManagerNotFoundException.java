package com.qa.apartmentManager.apartmentapi.util.exceptions;

@SuppressWarnings("serial")
public class ApartmentManagerNotFoundException extends RuntimeException {

	public ApartmentManagerNotFoundException(String exception){
        super("Id supplied does not exist. Id: " + exception);
    }
	
}




