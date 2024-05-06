package com.abc.bookingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.bookingservice.entity.BookingDetails;

public interface BookingDetailsDao extends JpaRepository<BookingDetails, Integer> {

}
	