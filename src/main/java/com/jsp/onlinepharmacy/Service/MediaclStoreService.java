package com.jsp.onlinepharmacy.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.exception.AddressAlreadyMappedtoCustomer;
import com.jsp.onlinepharmacy.exception.AddressAlredyMappedToMedicalStore;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class MediaclStoreService {

	@Autowired
	private MedicalStoreDao storeDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ModelMapper mapper;
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int addressId,
			MedicalStoreDto medicalStoreDto) {
		MedicalStore medicalStore=this.mapper.map(medicalStoreDto, MedicalStore.class);
		Admin dbAdmin=adminDao.findAdminById(adminId);
		if(dbAdmin!=null)
		{
			medicalStore.setAdmin(dbAdmin);
			Address dbAddress=addressDao.findAddressById(addressId);
			if(dbAddress!=null)
			{
				if(dbAddress.getCustomer()!=null)
				{
					throw new AddressAlreadyMappedtoCustomer("Sorry ! This Address Mapped to Customer Please select Another");
				}
				
				if(dbAddress.getMedicalStore()!=null)
				{
					throw new AddressAlredyMappedToMedicalStore("Sorry This Address Already mapped to another MedicalStore ");
				}
				
				medicalStore.setAddress(dbAddress);
				//updating address in database
				  dbAddress.setMedicalStore(medicalStore);
				MedicalStore dbStore=storeDao.saveMedicalStore(medicalStore);
				Address address=dbStore.getAddress();
				AddressDto addressDto=this.mapper.map(address, AddressDto.class);
				MedicalStoreDto dto=this.mapper.map(dbStore, MedicalStoreDto.class);
				
				dto.setAddressDto(addressDto);
				dto.setAdminDto(this.mapper.map(dbStore.getAdmin(), AdminDto.class));
				
				
				
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("MedicalStore Created Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.CREATED);
				
				
			}else
			{
				throw new AddressIdNotFoundException("Sorry Failed to Save MedicalStore");
			}
			
				
		}else
		{
			throw new AdminIdNotFoundException("Sorry failed To Add MedicalStore");
		}
		
		
		
	}
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore=storeDao.updateMedicalStore(storeId, medicalStore);
		if(dbMedicalStore!=null)
		{
			MedicalStoreDto storeDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class));	
		
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore Updated SuccessFully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
		return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
	}
		else
		{
			throw new MedicalStoreIdNotFoundException("Sorry We can't Update MedicalStore");
		}
	}
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStoreById(int storeId) {
		MedicalStore dbMedicalStore=storeDao.findMedicalStoreById(storeId);
		if(dbMedicalStore!=null)
		{
			MedicalStoreDto storeDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class));	
		
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore Founded SuccessFully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
		return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
	}
		else
		{
			throw new MedicalStoreIdNotFoundException("Sorry We can't Update MedicalStore");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStoreById(int storeId) {
		MedicalStore dbMedicalStore=storeDao.deleteMedicalStoreById(storeId);
		if(dbMedicalStore!=null)
		{
			MedicalStoreDto storeDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class));	
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore Deleted SuccessFully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(storeDto);
		return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.GONE);
	}
		else
		{
			throw new MedicalStoreIdNotFoundException("Sorry !  MedicalStore ID Not Founded");
		}
	}
}