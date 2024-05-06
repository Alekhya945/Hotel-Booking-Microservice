package com.hotelroom.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelroom.entity.Hotel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

public class RoomDetailsPayload {	

		private int hotelId;

		private String roomNo;

		private String roomType;

		private double ratePerDay;

		private boolean isAvailable;

		public int getHotelId() {
			return hotelId;
		}

		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
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

	}
