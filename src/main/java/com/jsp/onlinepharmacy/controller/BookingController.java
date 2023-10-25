package com.jsp.onlinepharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacy.Service.BookingService;
import com.jsp.onlinepharmacy.dto.BookingDto;
import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking
	(@RequestParam int customerId,@RequestParam int medicineId , @RequestBody BookingDto bookingDto)
	{
		return service.saveBooking(customerId,medicineId, bookingDto);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>> cancleBooking(@RequestParam int bookingId)
	{
		return service.cancleBooking(bookingId);
	}

}
