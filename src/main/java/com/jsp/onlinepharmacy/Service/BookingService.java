package com.jsp.onlinepharmacy.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.BookingDao;
import com.jsp.onlinepharmacy.dao.CustomerDao;
import com.jsp.onlinepharmacy.dao.MedicineDao;
import com.jsp.onlinepharmacy.dto.BookingDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.enums.BookingStatus;
import com.jsp.onlinepharmacy.exception.BookingAlreadyCancelled;
import com.jsp.onlinepharmacy.exception.BookingAlreadyDeliverdException;
import com.jsp.onlinepharmacy.exception.BookingIdNotFoundException;
import com.jsp.onlinepharmacy.exception.BookingcantCancelled;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicineIdNotFounded;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class BookingService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MedicineDao medicineDao;

	public ResponseEntity<ResponseStructure<Booking>> saveBooking(int customerId, int medicineId,
			BookingDto bookingDto) {

		Booking booking = this.mapper.map(bookingDto, Booking.class);
		Customer dbCustomer = customerDao.findCustomerById(customerId);
		if (dbCustomer != null) {
			Medicine dbMedicine = medicineDao.getMedicine(medicineId);
			if (dbMedicine != null) {
				List<Medicine> medicines = new ArrayList<>();
				medicines.add(dbMedicine);
				booking.setCustomer(dbCustomer);
				booking.setMedicines(medicines);

				// update Customer also
				List<Booking> bookings = new ArrayList<>();
				bookings.add(booking);
				dbCustomer.setBookings(bookings);
				customerDao.updateCustomer(customerId, dbCustomer);
				// updating Stock Quantity
				
				dbMedicine.setStockQuantity(dbMedicine.getStockQuantity() - booking.getQuntity());
				booking.setBookingStatus(BookingStatus.ACTIVE);
				Booking dbbooking = bookingDao.saveBooking(booking);

				ResponseStructure<Booking> structure = new ResponseStructure<>();
				structure.setMessage("Booking added Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbbooking);

				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.CREATED);
			} else {
				throw new MedicineIdNotFounded("Sorry We not find Any Medicine on this Id");
			}

		} else {
			throw new CustomerIdNotFoundException("Sorry We Not Found any Customer on this Id");
		}
	}

	public ResponseEntity<ResponseStructure<Booking>> cancleBooking(int bookingId) {
		Booking dbBooking=bookingDao.findBookingById(bookingId);
	if (dbBooking != null) { 
			
			if(dbBooking.getBookingStatus().equals(BookingStatus.CANCELLED))
			{
				throw new BookingAlreadyCancelled("Sorry ! Booking Already Cancelled");
			}
			else if(dbBooking.getBookingStatus().equals(BookingStatus.DELIVERED))
			{
				throw new BookingAlreadyDeliverdException("Sorry we not able to Cancelled Deliverd Medicine");
			}
			else if(LocalDate.now().equals(dbBooking.getExpectedDate().minusDays(2))||LocalDate.now().isAfter(dbBooking.getExpectedDate().minusDays(2)))
			{
				throw new BookingcantCancelled("Sorry Booking can't be Cancelled...");
			}
				
			else
			{
				Booking cancleBooking=bookingDao.cancleBooking(bookingId);
				ResponseStructure<Booking> structure=new ResponseStructure<>();
				structure.setMessage("Booking Cancelled SuccessFully .");
				structure.setStatus(HttpStatus.GONE.value());
				structure.setData(cancleBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.GONE);
			}
			
		}
		throw new BookingIdNotFoundException("Sorry WE Can't Find Any Booking On this Id");
	}

}
