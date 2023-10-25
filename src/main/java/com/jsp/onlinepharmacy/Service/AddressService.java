package com.jsp.onlinepharmacy.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {
		Address dbAddress = addressDao.saveAddress(address);
		if (dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());
			dto.setPincode(dbAddress.getPincode());

			ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
			structure.setMessage("Address Save Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.CREATED);
		}
		return null; 
	}

	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(int addressId) {
		Address dbAddress = addressDao.findAddressById(addressId);
		if (dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());
			dto.setPincode(dbAddress.getPincode());

			ResponseStructure<AddressDto> structure = new ResponseStructure<>();
			structure.setMessage("Address Present on this Id Number");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);

			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Failed to fetch the data");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> updateById(Address address, int addressId) {
		Address dbAddress = addressDao.updateById(address, addressId);
		if (dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());
			dto.setPincode(dbAddress.getPincode());

			ResponseStructure<AddressDto> structure = new ResponseStructure<>();
			structure.setMessage("Address Upadated Successfully ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.OK);

		}
		else
		{
			throw new AddressIdNotFoundException("Sorry Failed to fetch the data");

		}
		
	}

	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddressById(int addressId) {
		Address dbAddress = addressDao.deleteAddressById(addressId);
		if (dbAddress != null) {
			AddressDto dto = this.modelMapper.map(dbAddress, AddressDto.class);
			ResponseStructure<AddressDto> structure = new ResponseStructure<>();
			structure.setMessage("Address Deleted Successfully ");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.GONE);

		}
		
		return null;

	}

}
