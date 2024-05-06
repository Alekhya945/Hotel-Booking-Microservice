package com.abc.bookingservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}


}
