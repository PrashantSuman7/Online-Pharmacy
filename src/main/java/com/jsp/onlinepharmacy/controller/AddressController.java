package com.jsp.onlinepharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacy.Service.AddressService;
import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service; 
	@PostMapping
	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(@RequestBody Address address)
	{
		return service.saveAddress(address);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<AddressDto>> findById(@RequestParam int  addressId)
	{
		return service.findAddressById(addressId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<AddressDto>>  updateById(@RequestParam int addressId , @RequestBody Address address)
	 {
		return service.updateById(address,addressId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddressById(@RequestParam int addressId)
	{
		return service.deleteAddressById(addressId);
		
	}

}
