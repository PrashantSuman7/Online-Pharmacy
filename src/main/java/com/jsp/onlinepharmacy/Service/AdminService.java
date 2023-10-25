package com.jsp.onlinepharmacy.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.EmailIdNotFoundException;
import com.jsp.onlinepharmacy.exception.PasswordMissMatchException;
import com.jsp.onlinepharmacy.exception.PhoneNumberNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class AdminService {
	@Autowired
	private AdminDao admindao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {
		Admin dbAdmin = admindao.saveAdmin(admin);
		AdminDto dto = this.modelMapper.map(dbAdmin, AdminDto.class);
		ResponseStructure<AdminDto> structure = new ResponseStructure<>();
		structure.setMessage("Admin Data Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int adminId, Admin admin) {
		Admin dbAdmin = admindao.updateAdmin(adminId, admin);
		if (dbAdmin != null) {
			AdminDto dto = this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("Admin Data Updated Saved Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		}

		else {
			throw new AdminIdNotFoundException(" Check your Admin Id , no Admin Present in DB");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(int adminId) {
		
		Admin dbAdmin=admindao.findAdminById(adminId);
		if (dbAdmin != null) {
			AdminDto dto = this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("Admin Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		}

		else {
			throw new AdminIdNotFoundException(" Check your Admin Id , no Admin Present in DB");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(int adminId) {
		Admin dbAdmin=admindao.deleteAdminById(adminId);
		if(dbAdmin!=null)
		{
			AdminDto dto = this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("Admin Found Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.GONE);
		}
		else
		{
		throw new AdminIdNotFoundException(" Check your Admin Id , no Admin Present in DB");
	}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> logInAdmin(String adminEmail, String password) {
		Admin dbAdmin=admindao.findByEmail(adminEmail);
		if(dbAdmin!=null)
		{
			if(dbAdmin.getPassword().equals(password))
			{
				Admin admin = admindao.saveAdmin(dbAdmin);
				AdminDto adminDto = this.modelMapper.map(admin, AdminDto.class);
				ResponseStructure<AdminDto> structure = new ResponseStructure<>();
				structure.setMessage("Admin LogIn Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(adminDto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);	
			}
			else
			{
				throw new PasswordMissMatchException("Worng Password ! Please Check Your Password");
			}
			
		}
		else
		{
			throw new EmailIdNotFoundException("Sorry No ADmin Present with this Email");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updatePassword(String adminEmail, long phone, String password) {
		Admin admin=admindao.findByEmail(adminEmail);
		if(admin!=null)
		{
			if(admin.getPhone()==phone)
			{
				admin.setPassword(password);
				AdminDto adminDto = this.modelMapper.map(admin, AdminDto.class);
				ResponseStructure<AdminDto> structure = new ResponseStructure<>();
				structure.setMessage("Password Updated Successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(adminDto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
				
			}
			else
			{
				throw new PhoneNumberNotFoundException("Please Enter Currect Phone Number");
			}
		}
		else
		{
			throw new EmailIdNotFoundException("Email Id not Found please Check it again");
		}
	}

}
