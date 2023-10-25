package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerIdNotFoundException extends RuntimeException {
	
	private String message;
	

}
