package com.abc.bookingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.bookingservice.dao.BookingDetailsDao;
import com.abc.bookingservice.entity.BookingDetails;
import com.abc.bookingservice.exception.ResourceNotFoundException;
import com.abc.bookingservice.payload.BookingPayloadDetails;
import com.abc.bookingservice.payload.Hotel;
import com.abc.bookingservice.payload.RoomDetails;
import com.abc.bookingservice.payload.User;

import java.util.List;
import java.util.Optional;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

    @Autowired
    private BookingDetailsDao bookingDetailsDao;
    @Autowired
    private HotelServiceConsumer hotelService;
    @Autowired
    private UserServiceConsumer userService;

    @Override
    public BookingDetails addBookingDetails(BookingDetails bookingDetails) {
        return bookingDetailsDao.save(bookingDetails);
    }

    @Override
    public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {
        return bookingDetailsDao.save(bookingDetails);
    }

    @Override
    public void removeBookingDetails(int bookingId) {
    	bookingDetailsDao.deleteById(bookingId);
    }

    @Override
    public List<BookingDetails> getAllBookingDetails() {
        return bookingDetailsDao.findAll();
    }

    @Override
    public BookingDetails getBookingDetailsById(int bookingId) {
    	
     Optional<BookingDetails> optionalBookingDetails = bookingDetailsDao.findById(bookingId);
        return optionalBookingDetails.orElse(null);
       
   }  


@Override
public BookingPayloadDetails ViewBookingDetailsById(int bookingId) {

	 Optional<BookingDetails> optionalbooking = bookingDetailsDao.findById(bookingId);
	  
	  if(optionalbooking.isEmpty()) {
		  throw new ResourceNotFoundException("Booking details with id " +bookingId +" Not Found");
	  }
	  
	  BookingDetails booking = optionalbooking.get();
	  
	  	User user = userService.getUserById(booking.getUserId());
		Hotel  hotel=hotelService.getHotelById(booking.getHotelId());
		RoomDetails room=hotelService.getRoomDetailsById(booking.getRoomId());
		
		BookingPayloadDetails details = new BookingPayloadDetails();
		
		
		details.setHotelName(hotel.getHotelName());
		details.setCity(hotel.getCity());
		details.setAddress(hotel.getAddress());
		details.setPhone1(hotel.getPhone1());
		details.setUserName(user.getUserName());
		details.setMobile(user.getMobile());
		details.setRoomNo(room.getRoomNo());
		
		
		return details;


	
}


}	
  
    
    

