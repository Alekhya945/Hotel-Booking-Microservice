package com.hotelroom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelroom.entity.RoomDetails;

public interface RoomDetailsDao extends JpaRepository<RoomDetails, Integer> {

}
