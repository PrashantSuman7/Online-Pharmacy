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

import com.jsp.onlinepharmacy.Service.AdminService;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	private AdminService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin)
	{
		return service.saveAdmin(admin);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@RequestParam int adminId,@RequestBody Admin admin)
	{
		return service.updateAdmin(adminId, admin);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(@RequestParam int adminId)
	{
		return service.findAdminById(adminId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(@RequestParam int adminId)
	{
		return service.deleteAdminById(adminId);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDto>> logInAdmin(@RequestParam String adminEmail, @RequestParam String password)
	{
		return service.logInAdmin(adminEmail, password);
	}
	
	@PutMapping("/forgot")
	public ResponseEntity<ResponseStructure<AdminDto>> updatePassword(@RequestParam String adminEmail, @RequestParam long phone , @RequestParam String password)
	{
		return service.updatePassword(adminEmail, phone,password);
	}

}
