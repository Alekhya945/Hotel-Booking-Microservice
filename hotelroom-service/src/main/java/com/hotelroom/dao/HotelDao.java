package com.hotelroom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelroom.entity.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Integer> {

}
