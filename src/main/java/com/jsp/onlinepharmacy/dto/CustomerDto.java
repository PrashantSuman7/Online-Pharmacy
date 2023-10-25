package com.jsp.onlinepharmacy.dto;

import java.util.List;

import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private int customerId;
	private String customerName;
	private String email;
	private long phoneNumber;
	@OneToMany
	private List<AddressDto> addresses; 
	
	@OneToMany
	private List<BookingDto> bookingDtos;
}
