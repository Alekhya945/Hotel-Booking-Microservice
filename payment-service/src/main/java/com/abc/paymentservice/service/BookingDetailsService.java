package com.abc.paymentservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.paymentservice.model.BookingDetails;



@FeignClient(name="BOOKING-SERVICE")
public interface BookingDetailsService {
    @GetMapping("/bookings/{bookingId}")
	 BookingDetails getBookingDetailsById(@PathVariable("bookingId")int bookingId);

}
