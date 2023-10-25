package com.jsp.onlinepharmacy.dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.MedicalStore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDto {
	private int staffId;
	private String staffName;
	private String staffEmail;
	private long phone;
	
	@ManyToOne
	private AdminDto adminDto;
	
	@OneToOne
	private MedicalStoreDto medicalStoreDto;

}
