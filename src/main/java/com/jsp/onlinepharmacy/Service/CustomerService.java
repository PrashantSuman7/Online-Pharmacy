package com.jsp.onlinepharmacy.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dao.CustomerDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.BookingDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.exception.AddressAlreadyAddedInAnotherCustomer;
import com.jsp.onlinepharmacy.exception.AddressAlredyMappedToMedicalStore;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.exception.EmailIdNotFoundException;
import com.jsp.onlinepharmacy.exception.PasswordMissMatchException;
import com.jsp.onlinepharmacy.exception.PhoneNumberNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> addCustomer(int addressId, Customer customer) {
		Address dbAddress = addressDao.findAddressById(addressId);
		if (dbAddress != null) {
			if (dbAddress.getMedicalStore() != null) {
				throw new AddressAlredyMappedToMedicalStore(
						"Sorry ! Change Address Id This Id already Mapped to MedicalStore");
			}
			if (dbAddress.getCustomer() != null) {
				throw new AddressAlreadyAddedInAnotherCustomer("Sorry ! For this Address Already Customer Present");
			}
			// checking AddressID is Present or not.
			dbAddress.setCustomer(customer);

			// if AddressId is present
			List<Address> addresses = new ArrayList<>();
			addresses.add(dbAddress);
			customer.setAddresses(addresses);
			// now Customer is Having address Also;
			Customer dbCustomer = customerDao.addCustomer(customer);
			// dbcustomer is having its own attributes then relationship attributes that is
			// list address and
			// List of Booking but its null
			// but list of address is Present.
			// copy customer to CustomerDto;
			// both this customer dto is having List of addressDto and Booking dto but still
			// it is null
			// copy list of address to addressDto
			CustomerDto customerDto = this.mapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for (Address address : dbCustomer.getAddresses()) {

				AddressDto addressDto = this.mapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}

			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
			structure.setMessage("Customer Added Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new AddressIdNotFoundException("Sorry ! Please Check Address Id.");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {

		Customer dbcustomer = customerDao.updateCustomer(customerId, customer);
		if (dbcustomer != null) {
			CustomerDto customerDto = this.mapper.map(dbcustomer, CustomerDto.class);
			List<AddressDto> addresseDtos;
			for (Address address : dbcustomer.getAddresses()) {
				AddressDto addressDto = this.mapper.map(address, AddressDto.class);
				addresseDtos = new ArrayList<>();
				addresseDtos.add(addressDto);
				customerDto.setAddresses(addresseDtos);
			}
			ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
			structure.setMessage("Customer Updated SucssesFully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);

		} else {
			throw new CustomerIdNotFoundException("Sorry ! Customer Id Not Present ");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> findCustomerById(int customerId) {
		Customer dbCustomer = customerDao.findCustomerById(customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.mapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addresseDtos;
			for (Address address : dbCustomer.getAddresses()) {
				AddressDto addressDto = this.mapper.map(address, AddressDto.class);
				addresseDtos = new ArrayList<>();
				addresseDtos.add(addressDto);
				customerDto.setAddresses(addresseDtos);
			}

			ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
			structure.setMessage("Customer Featch SucssesFully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		} else {
			throw new CustomerIdNotFoundException("Please, Enter Valid Customer Id");
		}

	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(int customerId) {

		Customer dbCustomer = customerDao.deleteCustomerById(customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.mapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addresseDtos;
			for (Address address : dbCustomer.getAddresses()) {
				AddressDto addressDto = this.mapper.map(address, AddressDto.class);
				addresseDtos = new ArrayList<>();
				addresseDtos.add(addressDto);
				customerDto.setAddresses(addresseDtos);
			}
			ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
			structure.setMessage("Customer Deleted SucssesFully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.GONE);
		} else {
			throw new CustomerIdNotFoundException("Please, Enter Valid Customer Id");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
		Customer customer = customerDao.findCustomerByEmail(email);
		if (customer != null) {
			if (customer.getPassword().equals(password)) {
				Customer dbCustomer = customerDao.addCustomer(customer);
				CustomerDto customerDto = this.mapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
				for (Address address : dbCustomer.getAddresses()) {

					AddressDto addressDto = this.mapper.map(address, AddressDto.class);
					addressDtos = new ArrayList<AddressDto>();
					addressDtos.add(addressDto);
					customerDto.setAddresses(addressDtos);
				}

				List<BookingDto> bookingDtos;
				for (Booking booking : dbCustomer.getBookings()) {
					BookingDto bookingDto = this.mapper.map(booking, BookingDto.class);
					bookingDtos = new ArrayList<>();
					bookingDtos.add(bookingDto);
					customerDto.setBookingDtos(bookingDtos);
				}
				ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
				structure.setMessage("Customer LogIn Successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);

			} else {
				throw new PasswordMissMatchException("Invalid Password ! Write Currectly.");
			}

		} else {
			throw new CustomerIdNotFoundException("Sorry ! for this email not Customer Present in database");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(String email, long phoneNumber,
			String password) {
		Customer dbcustomer = customerDao.findCustomerByEmail(email);
		if (dbcustomer != null) {
			if (dbcustomer.getPhoneNumber() == phoneNumber) {
				dbcustomer.setPassword(password);
				Customer dbCustomer = customerDao.updateCustomer(dbcustomer.getCustomerId(), dbcustomer);
				CustomerDto dbcustomerDto = this.mapper.map(dbCustomer, CustomerDto.class);
				ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
				structure.setMessage("Customer Password Reset Successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbcustomerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);

			} else {
				throw new PhoneNumberNotFoundException("Please Enterd Valid Phone Number...");
			}

		} else {
			throw new EmailIdNotFoundException("Please Enterd a Valid Email");
		}

	}
}
