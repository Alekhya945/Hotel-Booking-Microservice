package com.abc.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.bookingservice.payload.User;


@FeignClient(name = "USER-SERVICE")
public interface UserServiceConsumer {
	
	@GetMapping("/users/{userId}")
	User getUserById(@PathVariable int userId);
}
