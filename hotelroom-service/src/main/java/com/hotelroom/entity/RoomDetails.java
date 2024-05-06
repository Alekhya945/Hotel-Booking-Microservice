package com.hotelroom.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;


@Entity
@Table(name="ROOM_DETAILS")
public class RoomDetails {
	
	  @Id
	  @Column(name="room_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int roomId;

	  @ManyToOne
	  @JoinColumn(name = "hotel_id")
	  @JsonIgnore
	  private Hotel hotel;

	  @Column(name="room_no")
	  private String roomNo;

	  @Column(name="room_type")
	  private String roomType;

	  @Column(name="rate_per_day")
	  private double ratePerDay;

	  private boolean isAvailable;

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getRatePerDay() {
		return ratePerDay;
	}

	public void setRatePerDay(double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

//	  @Lob
//	  private byte[] photo;

//	  @OneToMany(mappedBy = "roomDetails")
//	  private List<BookingDetails> bookingDetails;


}
