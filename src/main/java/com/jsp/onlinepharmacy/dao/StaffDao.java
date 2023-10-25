package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.dto.StaffDto;
import com.jsp.onlinepharmacy.entity.Staff;
import com.jsp.onlinepharmacy.repo.StaffRepo;

@Repository
public class StaffDao {
	
	@Autowired
	private StaffRepo repo;

	public Staff addStaff(Staff staff) {
		
		return repo.save(staff);
		
	}

	public Staff updateStaffById(int staffId, Staff staff) {
		Optional<Staff> optional=repo.findById(staffId);
		if(optional.isPresent())
		{
			Staff oldStaff=optional.get();
			staff.setStaffId(staffId);
			staff.setAdmin(oldStaff.getAdmin());
			staff.setMedicalStore(oldStaff.getMedicalStore());
			return repo.save(staff);
		}
		return null;
			
	}

	public Staff findStaffById(int staffId) {
		if(repo.findById(staffId).isPresent())
		{
			return repo.findById(staffId).get();
		}
		return null;
		
	}

	public Staff deleteStaffById(int staffId) {
		Optional<Staff> optional=repo.findById(staffId);
		if(optional.isPresent())
		{
			Staff staff=optional.get();
			repo.delete(staff);
			return staff;
		}
		return null;
	}

}
