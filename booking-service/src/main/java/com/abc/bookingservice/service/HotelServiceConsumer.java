package com.abc.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.bookingservice.payload.Hotel;
import com.abc.bookingservice.payload.RoomDetails;

@FeignClient(name="HOTELROOM-SERVICE")
public interface HotelServiceConsumer {
	@GetMapping("/hotels/{Id}")
	Hotel getHotelById(@PathVariable("Id") int hotelId);
	
	 @GetMapping("/rooms/{Id}")
	RoomDetails getRoomDetailsById(@PathVariable("Id") int roomId);

}
