package com.hotelroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelroom.dao.HotelDao;
import com.hotelroom.dao.RoomDetailsDao;
import com.hotelroom.entity.Hotel;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.exception.ResourceNotFoundException;
import com.hotelroom.payload.RoomDetailsPayload;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {

	@Autowired
	private RoomDetailsDao roomDetailsRepository;
	
	@Autowired
	private HotelDao hotelrepository;
	
	@Override
	public RoomDetails getRoomDetailsById(int roomId) {
		Optional<RoomDetails> optionalRoomDetails = roomDetailsRepository.findById(roomId);
		
		if(optionalRoomDetails.isEmpty()) {
			throw new ResourceNotFoundException("Room Details not found"+roomId);
		}
		RoomDetails roomDetails = optionalRoomDetails.get();
		
		return roomDetails;
	}

	
	@Override
	public void deleteRoomDetails(int roomId) {
		Optional<RoomDetails> optionalRoomDetails = roomDetailsRepository.findById(roomId);
		
		if(optionalRoomDetails.isEmpty()) {
			throw new ResourceNotFoundException("Room Details not found"+roomId);
		}
		RoomDetails roomDetails = optionalRoomDetails.get();
		roomDetailsRepository.delete(roomDetails);
		
	}

	@Override
	public List<RoomDetails> getAllRoomDetails() {
		List<RoomDetails> roomDetails = roomDetailsRepository.findAll();
		return roomDetails;
	}


	@Override
	public RoomDetails createRoomDetails(RoomDetailsPayload roomDetails) {
		Optional<Hotel> hotel = hotelrepository.findById(roomDetails.getHotelId());
		if(hotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel not found with Id"+roomDetails.getHotelId());			
		}
		
		RoomDetails rooms =new RoomDetails();
		
		rooms.setHotel(hotel.get());
		rooms.setRoomNo(roomDetails.getRoomNo());
		rooms.setRoomType(roomDetails.getRoomType());
		rooms.setRatePerDay(roomDetails.getRatePerDay());
		rooms.setAvailable(roomDetails.isAvailable());
		
		return roomDetailsRepository.save(rooms);
		
	}

	

	
}
