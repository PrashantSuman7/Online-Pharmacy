package com.jsp.onlinepharmacy.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.enums.BookingStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private LocalDate orderDate;
	private int quntity;
	private String paymentMode;
	private LocalDate expectedDate;
	private BookingStatus bookingStatus;            
	@ManyToMany(fetch = FetchType.EAGER)
     List<Medicine> medicines;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Customer customer;
	

}
