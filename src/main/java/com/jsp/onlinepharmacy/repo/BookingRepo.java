package com.jsp.onlinepharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacy.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
