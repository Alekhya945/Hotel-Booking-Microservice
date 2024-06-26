package com.hotelroom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelroom.entity.Hotel;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.payload.RoomDetailsPayload;
import com.hotelroom.service.RoomDetailsService;

@RestController
@RequestMapping("/rooms")
public class RoomDetailsController {

  @Autowired
  private RoomDetailsService roomDetailsService;

  @GetMapping("/{Id}")
  public ResponseEntity<RoomDetails> getRoomDetailsById(@PathVariable("Id") int roomId) {
    RoomDetails roomDetails = roomDetailsService.getRoomDetailsById(roomId);
    
      return new ResponseEntity<>(roomDetails,HttpStatus.OK);
    }


  @PostMapping("/save")
  public ResponseEntity<RoomDetails> createRoomDetails(@RequestBody RoomDetailsPayload roomDetails) {
	 roomDetailsService.createRoomDetails(roomDetails);
	 ResponseEntity<RoomDetails> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
	 return responseEntity;
  }
  
  @DeleteMapping("/{Id}")  
  public ResponseEntity<Void> deleteRoomDetails(@PathVariable("Id") int roomId){
	  roomDetailsService.deleteRoomDetails(roomId);
	  ResponseEntity<Void> repsonseEntity = new ResponseEntity<>(HttpStatus.OK);
	  return repsonseEntity;
  }

  @GetMapping("/all")
	public List<RoomDetails> fetchAllHotels() {

		List<RoomDetails> roomdetails = roomDetailsService.getAllRoomDetails();
		return roomdetails;
	}
}
