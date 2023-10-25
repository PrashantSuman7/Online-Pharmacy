package com.jsp.onlinepharmacy.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dao.StaffDao;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.dto.StaffDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Staff;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.exception.StaffIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
	private StaffDao staffDao;
	@Autowired 
	private AdminDao adminDao;
	@Autowired
	private MedicalStoreDao storeDao;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<StaffDto>> addStaff
	(int adminId, int storeId, Staff staff) {
		
		MedicalStore dbMedicalStore=storeDao.findMedicalStoreById(storeId);
		if(dbMedicalStore!=null)
		{
			staff.setMedicalStore(dbMedicalStore);
			Admin dbAdmin=adminDao.findAdminById(adminId);
			if(dbAdmin!=null)
			{
				staff.setAdmin(dbAdmin);
				Staff dbStaff=staffDao.addStaff(staff);
				StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
				staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
				staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
				
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("Staff Added Successfully !");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);	
			}
			else
			{
				throw new AdminIdNotFoundException("Admin Not Found !!");
			}	
		}
		else
		{
			throw new MedicalStoreIdNotFoundException("Medical Store Id Not Found !!");
		}
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaffById(int staffId, Staff staff) {
		Staff dbStaff=staffDao.updateStaffById(staffId, staff);
		if(dbStaff!=null)
		{
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
		}else
		{
			throw new StaffIdNotFoundException("Sorry ! Staff Id Not Present In DataBase.");
		}
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> findStaffById(int staffId) {
		Staff dbStaff=staffDao.findStaffById(staffId);
		if(dbStaff!=null)
		{
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
		
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff Founded Successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new StaffIdNotFoundException("Sorry ! We can't Found Staff Id.");
		}
		
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaffById(int staffId) {
		Staff dbStaff=staffDao.deleteStaffById(staffId);
		if(dbStaff!=null)
		{
			
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff Deleted  Successfully !");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.GONE);
		}
		else
		{
			throw new StaffIdNotFoundException("Sorry We Can't Found Staff Id");
		}
		
	}

}
