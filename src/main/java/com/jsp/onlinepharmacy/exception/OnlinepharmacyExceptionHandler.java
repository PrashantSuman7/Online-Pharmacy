package com.jsp.onlinepharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.onlinepharmacy.util.ResponseStructure;
@RestControllerAdvice
public class OnlinepharmacyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNotFoundException(AddressIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Address ID not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminIdNotFoundException(AdminIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Admin ID not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicalStoreIdNotFoundException(MedicalStoreIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry MedicalStroe ID not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(CustomerIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Please Check Your Customer ID ");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry Staff ID not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>medicineIdNotFounded(MedicineIdNotFounded ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry Medicine ID not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>bookingIdNotFounded(BookingIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry Medicine ID not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>phonwNumberNotFounded(PhoneNumberNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry Invalid Phone Number Please check It Once");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>passwordMissMatchException(PasswordMissMatchException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Please Write Password Currectly . Password Not Match");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>emailIdNotFoundException(EmailIdNotFoundException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("For this Email No Customer Founded In Database..");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>addressIdAlready(AddressAlreadyAddedInAnotherCustomer ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry ! We Can't Add This Address To You");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>addressIdAlready(AddressAlreadyMappedtoCustomer ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry ! We Can't Add This Address To MedicalStore");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>bookingAlreadyCancelledException(BookingAlreadyCancelled ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry ! Booking Already Cancelled ");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>bookingAlreadydeliverdException(BookingAlreadyDeliverdException ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry ! Booking Already Delivered so We Can't Cancel It... ");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>bookingcantBeCancelled(BookingcantCancelled ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry ! Booking Already near to Delivered so We Can't Cancel It... ");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>>addressIdAlready(AddressAlredyMappedToMedicalStore ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Sorry ! We Can't Add This Address To Customer ");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
